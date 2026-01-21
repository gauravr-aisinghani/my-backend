package com.example.controller;

import com.example.dto.TransporterFinalSubmissionRequestDto;
import com.example.dto.TransporterFinalSubmissionResponseDto;
import com.example.service.TransporterFinalSubmissionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transporter-gdc")
public class TransporterFinalSubmissionController {

    private final TransporterFinalSubmissionService service;

    public TransporterFinalSubmissionController(
            TransporterFinalSubmissionService service
    ) {
        this.service = service;
    }

    @PostMapping("/generate")
    public ResponseEntity<TransporterFinalSubmissionResponseDto>
    generate(@RequestBody TransporterFinalSubmissionRequestDto dto)
            throws Exception {

        return ResponseEntity.ok(service.generate(dto));
    }
}
