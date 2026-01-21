package com.example.service;

import com.example.dto.*;
import com.example.repository.PaymentReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaymentReportServiceImpl implements PaymentReportService {

    private final PaymentReportRepository repository;

    public PaymentReportServiceImpl(PaymentReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaymentReportResponseDto getPaymentReport(String paymentType, String status) {

        // ===== SUMMARY =====
        PaymentSummaryDto summary = new PaymentSummaryDto();
        summary.setTotalPayments(repository.countAll());
        summary.setTotalAmount(repository.sumAllAmount());
        summary.setPaidPayments(repository.countByStatus("PAID"));
        summary.setFailedPayments(repository.countByStatus("FAILED"));
        summary.setDriverPayments(repository.countByType("DRIVER"));
        summary.setTransporterPayments(repository.countByType("TRANSPORTER"));

        // ===== TABLE =====
        List<PaymentReportRowDto> rows = new ArrayList<>();

        List<Object[]> data =
                repository.fetchPayments(paymentType, status);

        for (Object[] r : data) {
            PaymentReportRowDto dto = new PaymentReportRowDto();
            dto.setId(((Number) r[0]).longValue());
            dto.setGdcNumber((String) r[1]);
            dto.setPaymentType((String) r[2]);
            dto.setAmount((Double) r[3]);
            dto.setStatus((String) r[4]);
            dto.setRazorpayPaymentId((String) r[5]);
            dto.setCreatedAt(r[6].toString());
            rows.add(dto);
        }

        return new PaymentReportResponseDto(summary, rows);
    }
}
