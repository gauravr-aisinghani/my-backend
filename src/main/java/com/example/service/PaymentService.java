package com.example.service;

import com.example.dto.CreatePaymentRequest;
import com.example.dto.CreatePaymentResponse;
import com.example.dto.VerifyPaymentRequest;
import com.example.dto.VerifyPaymentResponse;
import com.example.entity.*;
import com.example.repository.*;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final RazorpayClient razorpayClient;
    private final PaymentRepository paymentRepo;
    private final PaymentTransactionRepository txnRepo;
    private final DriverFinalSubmissionRepository driverRepo;
    private final TransporterFinalSubmissionRepository transporterRepo;
    private final WalletService walletService;
    private final TransporterSettlementService settlementService;

    // 🔥 NEW
    private final NotificationService notificationService;

    @Value("${razorpay.key-id}")
    private String razorpayKeyId;

    @Value("${razorpay.key-secret}")
    private String razorpayKeySecret;

    public PaymentService(
            RazorpayClient razorpayClient,
            PaymentRepository paymentRepo,
            PaymentTransactionRepository txnRepo,
            DriverFinalSubmissionRepository driverRepo,
            TransporterFinalSubmissionRepository transporterRepo,
            WalletService walletService,
            TransporterSettlementService settlementService,
            NotificationService notificationService   // 🔥 ADD
    ){
        this.razorpayClient = razorpayClient;
        this.paymentRepo = paymentRepo;
        this.txnRepo = txnRepo;
        this.driverRepo = driverRepo;
        this.transporterRepo = transporterRepo;
        this.walletService = walletService;
        this.settlementService = settlementService;
        this.notificationService = notificationService; // 🔥 ADD
    }

    // ================= CREATE ORDER =================

    public CreatePaymentResponse createOrder(CreatePaymentRequest req){

        if(req.getType()==PaymentType.DRIVER){
            driverRepo.findByGdcRegistrationNumber(req.getGdcNumber())
                    .orElseThrow(() -> new RuntimeException("Invalid Driver"));
        }

        if(req.getType()==PaymentType.TRANSPORTER){
            transporterRepo.findByGdcRegistrationNumber(req.getGdcNumber())
                    .orElseThrow(() -> new RuntimeException("Invalid Transporter"));
        }

        Double amount = resolveAmount(req);

        try{

            JSONObject options = new JSONObject();
            options.put("amount", amount * 100);
            options.put("currency","INR");
            options.put("receipt","rcpt_"+System.currentTimeMillis());

            Order order = razorpayClient.orders.create(options);

            Payment payment = new Payment();
            payment.setGdcNumber(req.getGdcNumber());
            payment.setPaymentType(req.getType());
            payment.setPurpose(req.getPurpose());
            payment.setAmount(amount);
            payment.setCurrency("INR");
            payment.setRazorpayOrderId(order.get("id"));
            payment.setStatus(PaymentStatus.CREATED);

            paymentRepo.save(payment);

            PaymentTransaction txn = new PaymentTransaction();
            txn.setPayment(payment);
            txn.setEventType(PaymentEventType.ORDER_CREATED);
            txn.setRazorpayOrderId(order.get("id"));
            txn.setAmount(amount);
            txn.setCurrency("INR");

            txnRepo.save(txn);

            CreatePaymentResponse res = new CreatePaymentResponse();
            res.setOrderId(order.get("id"));
            res.setAmount((int)(amount*100));
            res.setCurrency("INR");
            res.setKey(razorpayKeyId);

            return res;

        }catch(Exception e){
            throw new RuntimeException("Order failed",e);
        }
    }

    // ================= AMOUNT =================

    private Double resolveAmount(CreatePaymentRequest req){

        switch(req.getPurpose()){

            case DRIVER_REGISTRATION:
                return 865.0;

            case TRANSPORTER_REGISTRATION:
                return 1865.0;

            case TRANSPORTER_ADVANCE:
                return settlementService.calculateAdvance(req.getGdcNumber());

            case MONTHLY_SETTLEMENT:
                return settlementService.calculateMonthlySettlement(req.getGdcNumber());

            case DRIVER_TOPUP:
            case MANUAL_TOPUP:
                return req.getAmount();

            default:
                throw new RuntimeException("Invalid purpose");
        }
    }

    // ================= VERIFY PAYMENT =================

    public VerifyPaymentResponse verifyPayment(VerifyPaymentRequest req){

        Payment payment = paymentRepo.findByRazorpayOrderId(req.getRazorpayOrderId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        String data = req.getRazorpayOrderId()+"|"+req.getRazorpayPaymentId();
        String generated = generateSignature(data, razorpayKeySecret);

        if(!generated.equals(req.getRazorpaySignature())){

            payment.setStatus(PaymentStatus.FAILED);
            paymentRepo.save(payment);

            saveTxn(payment,req,PaymentEventType.PAYMENT_FAILED);

            return new VerifyPaymentResponse("FAILED","Signature mismatch");
        }

        // ===== SUCCESS =====

        payment.setRazorpayPaymentId(req.getRazorpayPaymentId());
        payment.setRazorpaySignature(req.getRazorpaySignature());
        payment.setStatus(PaymentStatus.PAID);
        paymentRepo.save(payment);

        saveTxn(payment,req,PaymentEventType.PAYMENT_SUCCESS);

        // ===== BUSINESS FLAGS =====

        if(payment.getPurpose()==PaymentPurpose.TRANSPORTER_ADVANCE){

            settlementService.markAdvancePaid(payment.getGdcNumber());

            // 🔔 ADMIN NOTIFICATION
            notificationService.notifyAdmins(
                    "Driver Advance Received",
                    "Transporter " + payment.getGdcNumber() +
                            " added driver advance ₹" + payment.getAmount(),
                    "TRANSPORTER_ADVANCE",
                    payment.getId()
            );
        }

        if(payment.getPurpose()==PaymentPurpose.MONTHLY_SETTLEMENT){
            settlementService.markSettlementPaid(payment.getGdcNumber());
        }

        // ===== WALLET CREDIT =====

        if(payment.getPurpose()!=PaymentPurpose.DRIVER_REGISTRATION &&
           payment.getPurpose()!=PaymentPurpose.TRANSPORTER_REGISTRATION){

            walletService.credit(payment);
        }

        return new VerifyPaymentResponse("SUCCESS","Payment success");
    }

    private void saveTxn(Payment p,VerifyPaymentRequest r,PaymentEventType e){

        PaymentTransaction t = new PaymentTransaction();
        t.setPayment(p);
        t.setRazorpayOrderId(r.getRazorpayOrderId());
        t.setRazorpayPaymentId(r.getRazorpayPaymentId());
        t.setRazorpaySignature(r.getRazorpaySignature());
        t.setEventType(e);
        t.setAmount(p.getAmount());
        t.setCurrency(p.getCurrency());

        txnRepo.save(t);
    }

    private String generateSignature(String data,String secret){

        try{
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(),"HmacSHA256"));
            return Hex.encodeHexString(mac.doFinal(data.getBytes()));
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
