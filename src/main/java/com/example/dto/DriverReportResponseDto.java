package com.example.dto;

import java.util.List;

public class DriverReportResponseDto {

    private DriverSummaryDto summary;
    private List<DriverReportRowDto> drivers;

    public DriverReportResponseDto(DriverSummaryDto summary, List<DriverReportRowDto> drivers) {
        this.summary = summary;
        this.drivers = drivers;
    }

    public DriverSummaryDto getSummary() {
        return summary;
    }

    public List<DriverReportRowDto> getDrivers() {
        return drivers;
    }
}
