package com.example.controller;

import com.example.dto.DriverDetailsDTO;
import com.example.entity.DriverDetails;
import com.example.service.DriverDetailsService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverDetailsController {

    private final DriverDetailsService service;

    @Autowired
    public DriverDetailsController(DriverDetailsService service) {
        this.service = service;
    }

    // Send OTP
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String mobileNumber) {
        String res = service.sendOtp(mobileNumber);
        return ResponseEntity.ok(res);
    }
    
    
 // 🔍 Check Aadhaar Unique
 


    // Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<Boolean> verifyOtp(@RequestParam String mobileNumber, @RequestParam String otp) {
        boolean ok = service.verifyOtp(mobileNumber, otp);
        return ResponseEntity.ok(ok);
    }

    // Create
    @PostMapping
    public ResponseEntity<DriverDetails> createDriver(@RequestBody DriverDetailsDTO dto) {
        DriverDetails created = service.createDriver(dto);
        return ResponseEntity.created(URI.create("/api/drivers" + created.getDriverRegistrationId()))
                .body(created);
    }

    // Get by id
    @GetMapping("/{id}")
    public ResponseEntity<DriverDetails> getById(@PathVariable("id") Long id) {
        DriverDetails d = service.getDriverById(id);
        return ResponseEntity.ok(d);
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<DriverDetails>> getAll() {
        return ResponseEntity.ok(service.getAllDrivers());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<DriverDetails> update(@PathVariable("id") Long id, @RequestBody DriverDetailsDTO dto) {
        DriverDetails updated = service.updateDriver(id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }
}
