package com.example.controller;

import com.example.dto.DriverExperienceDTO;
import com.example.entity.DriverExperience;
import com.example.service.DriverExperienceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/drivers/experience")
public class DriverExperienceController {

    private final DriverExperienceService service;

    public DriverExperienceController(DriverExperienceService service) {
        this.service = service;
    }

    // Create (DTO must contain driverRegistrationId OR use the helper path)
    @PostMapping
    public ResponseEntity<DriverExperience> create(@RequestBody DriverExperienceDTO dto) {
        DriverExperience created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/drivers/experience/" + created.getDriverExperienceId()))
                .body(created);
    }

    // helper: create using driver id path
    @PostMapping("/driver/{driverId}")
    public ResponseEntity<DriverExperience> createForDriver(
            @PathVariable("driverId") Long driverId,
            @RequestBody DriverExperienceDTO dto) {

        dto.setDriverRegistrationId(driverId);
        DriverExperience created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/drivers/experience/" + created.getDriverExperienceId()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverExperience> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<DriverExperience> getByDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(service.getByDriverRegistrationId(driverId));
    }

    @GetMapping("/driver/{driverId}/all")
    public ResponseEntity<List<DriverExperience>> getAllForDriver(@PathVariable Long driverId) {
        return ResponseEntity.ok(service.getAllByDriverRegistrationId(driverId));
    }

    @GetMapping
    public ResponseEntity<List<DriverExperience>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverExperience> update(@PathVariable Long id, @RequestBody DriverExperienceDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
