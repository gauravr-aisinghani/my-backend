package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.YfsTransporterDetailsDto;
import com.example.service.YfsTransporterDetailsService;

@RestController
@RequestMapping("/api/transporter")
public class YfsTransporterDetailsController {

    private final YfsTransporterDetailsService service;

    public YfsTransporterDetailsController(
            YfsTransporterDetailsService service) {
        this.service = service;
    }

    @PostMapping("/step1")
    public ResponseEntity<YfsTransporterDetailsDto> saveStepOne(
            @RequestBody YfsTransporterDetailsDto dto) {

        return ResponseEntity.ok(
                service.saveTransporterDetails(dto)
        );
    }

    @GetMapping("/{registrationId}")
    public ResponseEntity<YfsTransporterDetailsDto> getById(
            @PathVariable String registrationId) {

        return ResponseEntity.ok(
                service.getByRegistrationId(registrationId)
        );
    }
}
