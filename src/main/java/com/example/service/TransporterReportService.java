package com.example.service;

import com.example.dto.TransporterReportResponseDto;

public interface TransporterReportService {

    TransporterReportResponseDto getTransporterReport(String stage);
}
