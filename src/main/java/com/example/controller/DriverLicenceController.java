package com.example.controller;

import com.example.dto.DriverLicenceDTO;
import com.example.entity.DriverLicenceDetails;
import com.example.service.DriverLicenceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/drivers/licences")
public class DriverLicenceController {

    private final DriverLicenceService service;

    @Autowired
    public DriverLicenceController(DriverLicenceService service) {
        this.service = service;
    }

    // Create licence (payload must contain driver_registration_id)
    @PostMapping
    public ResponseEntity<DriverLicenceDetails> create(@RequestBody DriverLicenceDTO dto) {
        DriverLicenceDetails created = service.createLicence(dto);
        return ResponseEntity.created(URI.create("/api/drivers/licences/" + created.getDriverLicenceId()))
                .body(created);
    }

    // Helper: create via driver id path
    @PostMapping("/driver/{driverId}")
    public ResponseEntity<DriverLicenceDetails> createForDriver(
            @PathVariable("driverId") Long driverId,
            @RequestBody DriverLicenceDTO dto) {

        dto.setDriverRegistrationId(driverId);
        DriverLicenceDetails created = service.createLicence(dto);
        return ResponseEntity.created(URI.create("/api/drivers/licences/" + created.getDriverLicenceId()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverLicenceDetails> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getLicenceById(id));
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<DriverLicenceDetails> getByDriverId(@PathVariable("driverId") Long driverId) {
        return ResponseEntity.ok(service.getLicenceByDriverRegistrationId(driverId));
    }

    @GetMapping
    public ResponseEntity<List<DriverLicenceDetails>> getAll() {
        return ResponseEntity.ok(service.getAllLicences());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverLicenceDetails> update(@PathVariable("id") Long id, @RequestBody DriverLicenceDTO dto) {
        return ResponseEntity.ok(service.updateLicence(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.deleteLicence(id);
        return ResponseEntity.noContent().build();
    }
}
