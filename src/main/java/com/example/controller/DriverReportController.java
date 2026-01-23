package com.example.controller;

import com.example.dto.DriverReportResponseDto;

import com.example.service.DriverReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports/drivers")
public class DriverReportController {

    private final DriverReportService service;

    public DriverReportController(DriverReportService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<DriverReportResponseDto> getDriverReport(
            @RequestParam(required = false) String stage
    ) {
        return ResponseEntity.ok(service.getDriverReport(stage));
    }
}
