package com.example.controller;

import com.example.dto.DriverDetailsDTO;
import com.example.entity.DriverDetails;
import com.example.service.DriverDetailsService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverDetailsController {

    private final DriverDetailsService service;

    @Autowired
    public DriverDetailsController(DriverDetailsService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DriverDetails> createDriver(@RequestBody DriverDetailsDTO dto) {
        DriverDetails driver = service.createDriver(dto);
        return ResponseEntity.ok(driver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverDetails> getDriver(@PathVariable Long id) {
        DriverDetails driver = service.getDriverById(id);
        return ResponseEntity.ok(driver);
    }

    @GetMapping
    public ResponseEntity<List<DriverDetails>> getAllDrivers() {
        List<DriverDetails> drivers = service.getAllDrivers();
        return ResponseEntity.ok(drivers);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverDetails> updateDriver(@PathVariable Long id, @RequestBody DriverDetailsDTO dto) {
        DriverDetails driver = service.updateDriver(id, dto);
        return ResponseEntity.ok(driver);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
        service.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }
}
