package com.example.controller;

import com.example.dto.TransporterVehicleDTO;
import com.example.entity.TransporterVehicle;
import com.example.service.TransporterVehicleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transporter-vehicle")
@CrossOrigin("*")
public class TransporterVehicleController {

    private final TransporterVehicleService service;

    public TransporterVehicleController(TransporterVehicleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TransporterVehicle> saveVehicle(
            @RequestBody TransporterVehicleDTO dto) {

        return ResponseEntity.ok(service.saveVehicle(dto));
    }
}
