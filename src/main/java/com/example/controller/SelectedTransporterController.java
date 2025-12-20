package com.example.controller;

import com.example.dto.SelectedTransporterDTO;
import com.example.entity.SelectedTransporter;
import com.example.service.SelectedTransporterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/selected-transporter")
@CrossOrigin("*")
public class SelectedTransporterController {

    private final SelectedTransporterService service;

    public SelectedTransporterController(SelectedTransporterService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<SelectedTransporter> save(
            @RequestBody SelectedTransporterDTO dto) {

        return ResponseEntity.ok(service.saveSelectedTransporter(dto));
    }
}
