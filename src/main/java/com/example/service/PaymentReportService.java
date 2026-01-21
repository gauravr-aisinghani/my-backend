package com.example.service;

import com.example.dto.PaymentReportResponseDto;

public interface PaymentReportService {
    PaymentReportResponseDto getPaymentReport(String paymentType, String status);
}
