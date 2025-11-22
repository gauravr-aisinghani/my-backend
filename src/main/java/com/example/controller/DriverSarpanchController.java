package com.example.controller;

import com.example.dto.DriverSarpanchDTO;
import com.example.entity.DriverSarpanchDetails;
import com.example.service.DriverSarpanchService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/drivers/sarpanch")
public class DriverSarpanchController {

    private final DriverSarpanchService service;

    public DriverSarpanchController(DriverSarpanchService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DriverSarpanchDetails> create(@RequestBody DriverSarpanchDTO dto) {
        DriverSarpanchDetails created = service.create(dto);
        return ResponseEntity.created(
                URI.create("/api/drivers/sarpanch/" + created.getSarpanchDetailsId())
        ).body(created);
    }

    @PostMapping("/driver/{driverId}")
    public ResponseEntity<DriverSarpanchDetails> createForDriver(
            @PathVariable Long driverId,
            @RequestBody DriverSarpanchDTO dto) {

        dto.setDriverRegistrationId(driverId);

        DriverSarpanchDetails created = service.create(dto);

        return ResponseEntity.created(
                URI.create("/api/drivers/sarpanch/" + created.getSarpanchDetailsId())
        ).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverSarpanchDetails> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/driver/{driverId}")
    public ResponseEntity<DriverSarpanchDetails> getByDriverRegistrationId(@PathVariable Long driverId) {
        return ResponseEntity.ok(service.getByDriverRegistrationId(driverId));
    }

    @GetMapping
    public ResponseEntity<List<DriverSarpanchDetails>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverSarpanchDetails> update(
            @PathVariable Long id,
            @RequestBody DriverSarpanchDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
