package com.example.service;

import com.example.dto.*;
import com.example.repository.DriverReportRepository;
import com.example.service.DriverReportService;
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
    public DriverReportResponseDto getDriverReport() {

        DriverSummaryDto summary = new DriverSummaryDto();
        summary.setVisitors(repository.countVisitors());
        summary.setSelectedVisitors(repository.countSelectedVisitors());
        summary.setRegisteredDrivers(repository.countRegisteredDrivers());
        summary.setDocumentsUploaded(repository.countDocumentsUploaded());
        summary.setVerificationPending(repository.countVerificationPending());
        summary.setGdcGenerated(repository.countGdcGenerated());

        List<Object[]> rows = repository.fetchDriverReportRows();
        List<DriverReportRowDto> drivers = new ArrayList<>();

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
