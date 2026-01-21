package com.example.controller;

import com.example.dto.PaymentReportResponseDto;
import com.example.service.PaymentReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports/payments")
public class PaymentReportController {

    private final PaymentReportService service;

    public PaymentReportController(PaymentReportService service) {
        this.service = service;
    }

    @GetMapping
    public PaymentReportResponseDto getPaymentReport(
            @RequestParam(required = false) String paymentType,
            @RequestParam(required = false) String status
    ) {
        return service.getPaymentReport(paymentType, status);
    }
}
