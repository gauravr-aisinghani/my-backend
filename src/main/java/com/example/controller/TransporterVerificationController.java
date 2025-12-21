package com.example.controller;

import com.example.dto.ApproveRequestTransporterDto;
import com.example.dto.PendingTransporterDto;
import com.example.service.TransporterVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transporter-verification")
@CrossOrigin("*")
public class TransporterVerificationController {

    private final TransporterVerificationService service;

    public TransporterVerificationController(TransporterVerificationService service) {
        this.service = service;
    }

    @GetMapping("/pending")
    public ResponseEntity<List<PendingTransporterDto>> pending() {
        return ResponseEntity.ok(service.getPendingTransporters());
    }

    @GetMapping("/documents/{registrationId}")
    public ResponseEntity<Map<String, String>> documents(
            @PathVariable String registrationId
    ) {
        return ResponseEntity.ok(service.getTransporterDocuments(registrationId));
    }

    @PostMapping("/approve")
    public ResponseEntity<String> approve(
            @RequestBody ApproveRequestTransporterDto request
    ) {
        service.approveTransporter(request);
        return ResponseEntity.ok("Transporter approved successfully");
    }

    @PostMapping("/reject")
    public ResponseEntity<String> reject(
            @RequestBody ApproveRequestTransporterDto request
    ) {
        service.rejectTransporter(request);
        return ResponseEntity.ok("Transporter rejected successfully");
    }

    @GetMapping("/approved")
    public ResponseEntity<List<Map<String, Object>>> approved() {
        return ResponseEntity.ok(service.getApprovedTransporters());
    }
}
