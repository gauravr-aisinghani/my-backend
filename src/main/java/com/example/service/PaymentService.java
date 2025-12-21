package com.example.service;

import com.example.dto.CreatePaymentRequest;
import com.example.dto.CreatePaymentResponse;
import com.example.entity.*;
import com.example.repository.DriverFinalSubmissionRepository;
import com.example.repository.PaymentRepository;
import com.example.repository.PaymentTransactionRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final RazorpayClient razorpayClient;
    private final PaymentRepository paymentRepo;
    private final PaymentTransactionRepository txnRepo;
    private final DriverFinalSubmissionRepository driverRepo;

    @Value("${razorpay.key-id}")
    private String razorpayKeyId;

    public PaymentService(
            RazorpayClient razorpayClient,
            PaymentRepository paymentRepo,
            PaymentTransactionRepository txnRepo,
            DriverFinalSubmissionRepository driverRepo
    ) {
        this.razorpayClient = razorpayClient;
        this.paymentRepo = paymentRepo;
        this.txnRepo = txnRepo;
        this.driverRepo = driverRepo;
    }

    public CreatePaymentResponse createOrder(CreatePaymentRequest req) {

        // ===============================
        // 1️⃣ Validate GDC (Driver only for now)
        // ===============================
        if (req.getType() == PaymentType.DRIVER) {
            driverRepo.findByGdcRegistrationNumber(req.getGdcNumber())
                    .orElseThrow(() ->
                            new RuntimeException("Driver GDC not found"));
        }

        // ===============================
        // 2️⃣ Prevent double payment
        // ===============================
        boolean alreadyPaid =
                paymentRepo.existsByGdcNumberAndPaymentTypeAndStatus(
                        req.getGdcNumber(),
                        req.getType(),
                        PaymentStatus.PAID
                );

        if (alreadyPaid) {
            throw new RuntimeException("Payment already completed for this GDC");
        }

        try {
            // ===============================
            // 3️⃣ Create Razorpay Order
            // ===============================
            JSONObject options = new JSONObject();
            options.put("amount", req.getAmount() * 100); // paise
            options.put("currency", "INR");
            options.put("receipt", "rcpt_" + System.currentTimeMillis());

            Order order = razorpayClient.orders.create(options);

            // ===============================
            // 4️⃣ Save Payment (MASTER)
            // ===============================
            Payment payment = new Payment();
            payment.setGdcNumber(req.getGdcNumber());
            payment.setPaymentType(req.getType());
            payment.setAmount(req.getAmount().doubleValue());
            payment.setCurrency("INR");
            payment.setRazorpayOrderId(order.get("id"));
            payment.setStatus(PaymentStatus.CREATED);

            paymentRepo.save(payment);

            // ===============================
            // 5️⃣ Save Transaction History
            // ===============================
            PaymentTransaction txn = new PaymentTransaction();
            txn.setPayment(payment);
            txn.setEventType(PaymentEventType.ORDER_CREATED);
            txn.setRazorpayOrderId(order.get("id"));
            txn.setAmount(payment.getAmount());
            txn.setCurrency("INR");

            txnRepo.save(txn);

            // ===============================
            // 6️⃣ Build Response for Frontend
            // ===============================
            CreatePaymentResponse res = new CreatePaymentResponse();
            res.setOrderId(order.get("id"));
            res.setAmount(req.getAmount() * 100);
            res.setCurrency("INR");
            res.setKey(razorpayKeyId);

            return res;

        } catch (Exception e) {
            throw new RuntimeException("Failed to create Razorpay order", e);
        }
    }
}
