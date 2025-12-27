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

    @Value("${razorpay.key-id}")
    private String razorpayKeyId;

    
    @Value("${razorpay.key-secret}")
    private String razorpayKeySecret;
 
    public PaymentService(
            RazorpayClient razorpayClient,
            PaymentRepository paymentRepo,
            PaymentTransactionRepository txnRepo,
            DriverFinalSubmissionRepository driverRepo,
            TransporterFinalSubmissionRepository transporterRepo
    ) {
        this.razorpayClient = razorpayClient;
        this.paymentRepo = paymentRepo;
        this.txnRepo = txnRepo;
        this.driverRepo = driverRepo;
        this.transporterRepo = transporterRepo;
    }

    public CreatePaymentResponse createOrder(CreatePaymentRequest req) {

        // ===============================
        // 1️⃣ VALIDATE BASED ON TYPE
        // ===============================
        if (req.getType() == PaymentType.DRIVER) {

            DriverFinalSubmission driver =
                    driverRepo.findByGdcRegistrationNumber(req.getGdcNumber())
                            .orElseThrow(() ->
                                    new RuntimeException("Invalid DRIVER GDC number"));

            if (!"COMPLETED".equalsIgnoreCase(driver.getCompletionStatus())) {
                throw new RuntimeException("Driver is not fully approved");
            }

        } else if (req.getType() == PaymentType.TRANSPORTER) {

            TransporterFinalSubmission transporter =
                    transporterRepo.findByGdcRegistrationNumber(req.getGdcNumber())
                            .orElseThrow(() ->
                                    new RuntimeException("Invalid TRANSPORTER GDC number"));

            if (!"COMPLETED".equalsIgnoreCase(transporter.getCompletionStatus())) {
                throw new RuntimeException("Transporter is not fully approved");
            }
        }

        // ===============================
        // 2️⃣ PREVENT DUPLICATE PAYMENT
        // ===============================
        paymentRepo.findByGdcNumberAndPaymentType(
                req.getGdcNumber(),
                req.getType()
        ).ifPresent(existing -> {
            if (existing.getStatus() == PaymentStatus.CREATED ||
                existing.getStatus() == PaymentStatus.PAID) {
                throw new RuntimeException("Payment already initiated for this GDC");
            }
        });

        // ===============================
        // 3️⃣ AMOUNT (BACKEND CONTROL)
        // ===============================
        int amount = getAmountForType(req.getType());

        try {
            // ===============================
            // 4️⃣ CREATE RAZORPAY ORDER
            // ===============================
            JSONObject options = new JSONObject();
            options.put("amount", amount * 100);
            options.put("currency", "INR");
            options.put("receipt", "rcpt_" + System.currentTimeMillis());

            Order order = razorpayClient.orders.create(options);

            // ===============================
            // 5️⃣ SAVE PAYMENT
            // ===============================
            Payment payment = new Payment();
            payment.setGdcNumber(req.getGdcNumber());
            payment.setPaymentType(req.getType());
            payment.setAmount((double) amount);
            payment.setCurrency("INR");
            payment.setRazorpayOrderId(order.get("id"));
            payment.setStatus(PaymentStatus.CREATED);
            paymentRepo.save(payment);

            // ===============================
            // 6️⃣ SAVE TRANSACTION
            // ===============================
            PaymentTransaction txn = new PaymentTransaction();
            txn.setPayment(payment);
            txn.setEventType(PaymentEventType.ORDER_CREATED);
            txn.setRazorpayOrderId(order.get("id"));
            txn.setAmount(payment.getAmount());
            txn.setCurrency("INR");
            txnRepo.save(txn);

            // ===============================
            // 7️⃣ RESPONSE
            // ===============================
            CreatePaymentResponse res = new CreatePaymentResponse();
            res.setOrderId(order.get("id"));
            res.setAmount(amount * 100);
            res.setCurrency("INR");
            res.setKey(razorpayKeyId);

            return res;

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }

    private int getAmountForType(PaymentType type) {
        if (type == PaymentType.DRIVER) {
            return 865;
        }
        if (type == PaymentType.TRANSPORTER) {
            return 1865;
        }
        throw new RuntimeException("Invalid payment type");
    }
    
    
    public VerifyPaymentResponse verifyPayment(VerifyPaymentRequest req) {

        Payment payment = paymentRepo
                .findByRazorpayOrderId(req.getRazorpayOrderId())
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        String data = req.getRazorpayOrderId() + "|" + req.getRazorpayPaymentId();

        String generatedSignature = generateSignature(data, razorpayKeySecret);

        if (!generatedSignature.equals(req.getRazorpaySignature())) {

            payment.setStatus(PaymentStatus.FAILED);
            paymentRepo.save(payment);

            saveTransaction(payment, req, PaymentEventType.PAYMENT_FAILED);

            return new VerifyPaymentResponse("FAILED", "Invalid payment signature");
        }

        // ✅ SUCCESS
        payment.setRazorpayPaymentId(req.getRazorpayPaymentId());
        payment.setRazorpaySignature(req.getRazorpaySignature());
        payment.setStatus(PaymentStatus.PAID);
        paymentRepo.save(payment);

        saveTransaction(payment, req, PaymentEventType.PAYMENT_SUCCESS);

        return new VerifyPaymentResponse("SUCCESS", "Payment verified successfully");
    }
    
    
    private String generateSignature(String data, String secret) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey =
                    new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKey);
            byte[] rawHmac = mac.doFinal(data.getBytes());
            return Hex.encodeHexString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Signature verification failed", e);
        }
    }

    
    private void saveTransaction(Payment payment,
            VerifyPaymentRequest req,
            PaymentEventType eventType) {

PaymentTransaction txn = new PaymentTransaction();
txn.setPayment(payment);
txn.setRazorpayOrderId(req.getRazorpayOrderId());
txn.setRazorpayPaymentId(req.getRazorpayPaymentId());
txn.setRazorpaySignature(req.getRazorpaySignature());
txn.setEventType(eventType);
txn.setAmount(payment.getAmount());
txn.setCurrency(payment.getCurrency());

txnRepo.save(txn);
}


}
