package com.example.controller;

import com.example.dto.AssignDriverRequestDto;
import com.example.dto.AssignDriverResponseDto;
import com.example.service.DriverAssignmentService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.dto.CurrentPostingDto;

@RestController
@RequestMapping("/driver-assignments")
public class DriverAssignmentAdminController {

    private final DriverAssignmentService service;

    public DriverAssignmentAdminController(
            DriverAssignmentService service) {
        this.service = service;
    }

    // ✅ 1. advance paid requests
    @GetMapping("/advance-paid-requests")
    public ResponseEntity<?> getAdvancePaidRequests() {
        return ResponseEntity.ok(
                service.getAdvancePaidRequests());
    }

    // ✅ 2. available drivers
    @GetMapping("/available-drivers")
    public ResponseEntity<?> getAvailableDrivers() {
        return ResponseEntity.ok(
                service.getAvailableDrivers());
    }

    // ✅ 3. assign driver
    @PostMapping("/assign")
    public ResponseEntity<?> assignDriver(
            @RequestBody AssignDriverRequestDto dto) {
        return ResponseEntity.ok(
                service.assignDriver(dto));
    }
    
    
 // ✅ 4. current postings
    @GetMapping("/current-postings")
    public ResponseEntity<List<CurrentPostingDto>> getCurrentPostings() {
        return ResponseEntity.ok(
                service.getCurrentPostings()
        );
    }

}

