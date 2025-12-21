package com.example.controller;

import com.example.dto.ApproveRequestDto;

import com.example.dto.PendingDriverDto;
import com.example.service.DriverVerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/driver-verification")
public class DriverVerificationController {

    private final DriverVerificationService service;

    public DriverVerificationController(DriverVerificationService service) {
        this.service = service;
    }

    @GetMapping("/pending")
    public ResponseEntity<List<PendingDriverDto>> getPending() {
        List<PendingDriverDto> list = service.getPendingDrivers();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/documents/{driverId}")
    public ResponseEntity<Map<String,String>> getDocs(@PathVariable("driverId") Long driverId) {
        Map<String,String> docs = service.getDriverDocuments(driverId);
        return ResponseEntity.ok(docs);
    }

    @PostMapping("/approve")
    public ResponseEntity<String> approve(@RequestBody ApproveRequestDto req) {
        service.approveDriver(req);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/reject")
    public ResponseEntity<String> reject(@RequestBody ApproveRequestDto req) {
        service.rejectDriver(req);
        return ResponseEntity.ok("OK");
    }

    // ✅ NEW ENDPOINT TO FETCH APPROVED DRIVERS
    @GetMapping("/approved-drivers")
    public ResponseEntity<List<Map<String, Object>>> getApprovedDrivers() {
        List<Map<String, Object>> list = service.getApprovedDrivers();
        return ResponseEntity.ok(list);
    }
}
