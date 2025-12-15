package com.example.controller;

import com.example.dto.VisitorTransporterDTO;
import com.example.entity.VisitorTransporterEntity;
import com.example.service.TransporterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transporters")
public class TransporterController {

    private final TransporterService transporterService;

    public TransporterController(TransporterService transporterService) {
        this.transporterService = transporterService;
    }

    @PostMapping
    public ResponseEntity<VisitorTransporterEntity> saveVisitor(
            @RequestBody VisitorTransporterDTO dto) {
        VisitorTransporterEntity saved = transporterService.saveVisitor(dto);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public ResponseEntity<List<VisitorTransporterEntity>> getAllVisitors() {
        return ResponseEntity.ok(transporterService.getAllVisitors());
    }
}
