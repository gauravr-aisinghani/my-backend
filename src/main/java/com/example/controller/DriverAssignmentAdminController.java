package com.example.controller;

import com.example.dto.AssignDriverRequestDto;
import com.example.dto.AssignDriverResponseDto;
import com.example.service.DriverAssignmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/driver-assignments")
public class DriverAssignmentAdminController {

    private final DriverAssignmentService service;

    public DriverAssignmentAdminController(DriverAssignmentService service) {
        this.service = service;
    }

    @PostMapping("/assign")
    public ResponseEntity<AssignDriverResponseDto>
    assignDriver(@RequestBody AssignDriverRequestDto dto) {

        return ResponseEntity.ok(service.assignDriver(dto));
    }
}
