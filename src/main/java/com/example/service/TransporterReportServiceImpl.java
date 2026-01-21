package com.example.service;

import com.example.dto.*;
import com.example.repository.TransporterReportRepository;
import com.example.service.TransporterReportService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransporterReportServiceImpl implements TransporterReportService {

    private final TransporterReportRepository repository;

    public TransporterReportServiceImpl(TransporterReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransporterReportResponseDto getTransporterReport(String stage) {

        // -------- SUMMARY --------
        TransporterSummaryDto summary = new TransporterSummaryDto();
        summary.setRegisteredTransporters(
                repository.countRegisteredTransporters()
        );
        summary.setVerificationPending(
                repository.countVerificationPending()
        );
        summary.setVerifiedTransporters(
                repository.countVerifiedTransporters()
        );
        summary.setGdcGenerated(
                repository.countGdcGenerated()
        );

        // -------- TABLE --------
        List<TransporterReportRowDto> rows = new ArrayList<>();

        if (stage == null || stage.isBlank()) {
            return new TransporterReportResponseDto(summary, rows);
        }

        List<Object[]> result =
                repository.fetchTransporterReportRowsByStage(stage);

        for (Object[] r : result) {
            rows.add(new TransporterReportRowDto(
                    (String) r[0],
                    (String) r[1],
                    (String) r[2],
                    (String) r[3],
                    (String) r[4],
                    (String) r[5]
            ));
        }

        return new TransporterReportResponseDto(summary, rows);
    }
}
