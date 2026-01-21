package com.example.controller;

import com.example.dto.TransporterReportResponseDto;
import com.example.service.TransporterReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports/transporters")
public class TransporterReportController {

    private final TransporterReportService service;

    public TransporterReportController(TransporterReportService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<TransporterReportResponseDto> getTransporterReport(
            @RequestParam(required = false) String stage
    ) {
        return ResponseEntity.ok(
                service.getTransporterReport(stage)
        );
    }
}
