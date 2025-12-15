package com.example.controller;

import com.example.dto.VisitorTransporterDTO;
import com.example.entity.SelectedTransporterEntity;
import com.example.entity.VisitorTransporterEntity;
import com.example.service.TransporterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transporter")
@CrossOrigin(origins = "*")
public class TransporterController {

    private final TransporterService transporterService;

    public TransporterController(TransporterService transporterService) {
        this.transporterService = transporterService;
    }

    // Save visitor transporter
    @PostMapping("/visitor")
    public VisitorTransporterEntity saveVisitor(
            @RequestBody VisitorTransporterDTO dto) {
        return transporterService.saveVisitor(dto);
    }

    // Get all visitor transporters
    @GetMapping("/visitor")
    public List<VisitorTransporterEntity> getAllVisitors() {
        return transporterService.getAllVisitors();
    }

    // Add visitor to final selected transporter
    @PostMapping("/final/{visitorTransporterId}")
    public void addToFinal(
            @PathVariable Long visitorTransporterId) {
        transporterService.addToFinal(visitorTransporterId);
    }

    // Get all final selected transporters
    @GetMapping("/final")
    public List<SelectedTransporterEntity> getAllFinalTransporters() {
        return transporterService.getAllFinalTransporters();
    }
}
