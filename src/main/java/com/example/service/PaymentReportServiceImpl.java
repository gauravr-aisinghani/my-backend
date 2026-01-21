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
    public PaymentReportResponseDto getPaymentReport(
            String paymentType,
            String status,
            String fromDate,
            String toDate
    ) {

        String start = fromDate != null ? fromDate + " 00:00:00" : null;
        String end   = toDate != null ? toDate + " 23:59:59" : null;

        // ===== SUMMARY =====
        PaymentSummaryDto summary = new PaymentSummaryDto();

        summary.setTotalPayments(
                repository.countAllFiltered(paymentType, status, start, end)
        );
        summary.setTotalAmount(
                repository.sumAmountFiltered(paymentType, status, start, end)
        );
        summary.setPaidPayments(
                repository.countByStatusFiltered("PAID", paymentType, start, end)
        );
        summary.setFailedPayments(
                repository.countByStatusFiltered("FAILED", paymentType, start, end)
        );
        summary.setDriverPayments(
                repository.countByTypeFiltered("DRIVER", status, start, end)
        );
        summary.setTransporterPayments(
                repository.countByTypeFiltered("TRANSPORTER", status, start, end)
        );

        // ===== TABLE =====
        List<Object[]> data =
                repository.fetchPayments(paymentType, status, start, end);

        List<PaymentReportRowDto> rows = new ArrayList<>();

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


