package com.example.service;

import com.example.dto.*;

import com.example.repository.DriverReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DriverReportServiceImpl implements DriverReportService {

    private final DriverReportRepository repository;

    public DriverReportServiceImpl(DriverReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public DriverReportResponseDto getDriverReport(String stage) {

        // ================= SUMMARY (ALWAYS) =================
        DriverSummaryDto summary = new DriverSummaryDto();
        summary.setVisitors(repository.countVisitors());
        summary.setSelectedVisitors(repository.countSelectedVisitors());
        summary.setRegisteredDrivers(repository.countRegisteredDrivers());
        summary.setVerificationPending(repository.countVerificationPending());
        summary.setGdcGenerated(repository.countGdcGenerated());

        // ================= TABLE LOGIC =================
        List<DriverReportRowDto> drivers = new ArrayList<>();

        // IMPORTANT: no stage → return EMPTY table
        if (stage == null || stage.isBlank()) {
            return new DriverReportResponseDto(summary, drivers);
        }

        List<Object[]> rows = repository.fetchDriverReportRowsByStage(stage);

        for (Object[] row : rows) {
            drivers.add(new DriverReportRowDto(
                    ((Number) row[0]).longValue(),
                    (String) row[1],
                    (String) row[2],
                    (String) row[3],
                    (String) row[4],
                    (String) row[5]
            ));
        }

        return new DriverReportResponseDto(summary, drivers);
    }
}
