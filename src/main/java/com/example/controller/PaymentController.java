package com.example.controller;

import com.example.dto.*;
import com.example.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-order")
    public CreatePaymentResponse createOrder(
            @RequestBody CreatePaymentRequest request) {

        return paymentService.createOrder(request);
    }
    
    
    @PostMapping("/verify")
    public VerifyPaymentResponse verifyPayment(
            @RequestBody VerifyPaymentRequest request) {

        return paymentService.verifyPayment(request);
    }


}
