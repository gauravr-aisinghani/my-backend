package com.example.controller;

import com.example.dto.SelectedTransporterDTO;
import com.example.entity.SelectedTransporter;
import com.example.service.SelectedTransporterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/selected-transporter")
@CrossOrigin("*")
public class SelectedTransporterController {

    private final SelectedTransporterService service;

    public SelectedTransporterController(SelectedTransporterService service) {
        this.service = service;
    }

    // ✅ SAVE selected transporter
    @PostMapping("/save")
    public ResponseEntity<SelectedTransporter> save(
            @RequestBody SelectedTransporterDTO dto) {

        return ResponseEntity.ok(service.saveSelectedTransporter(dto));
    }

    // ✅ GET ALL selected transporters
    @GetMapping
    public ResponseEntity<List<SelectedTransporter>> getAll() {
        return ResponseEntity.ok(service.getAllSelectedTransporters());
    }

    // ✅ GET selected transporter by ID (optional)
    @GetMapping("/{id}")
    public ResponseEntity<SelectedTransporter> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSelectedTransporterById(id));
    }
}
