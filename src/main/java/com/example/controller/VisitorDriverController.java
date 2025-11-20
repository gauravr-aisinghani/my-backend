package com.example.controller;

import com.example.dto.VisitorDriverDTO;
import com.example.service.VisitorDriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitor-driver")
@CrossOrigin(origins = "*")
public class VisitorDriverController {

    private final VisitorDriverService service;

    public VisitorDriverController(VisitorDriverService service) {
        this.service = service;
    }

    @PostMapping
    public VisitorDriverDTO save(@RequestBody VisitorDriverDTO dto) {
        return service.saveVisitorDriver(dto);
    }

    @GetMapping("/{id}")
    public VisitorDriverDTO getOne(@PathVariable Long id) {
        return service.getVisitorDriver(id);
    }

    @GetMapping
    public List<VisitorDriverDTO> getAll() {
        return service.getAllVisitorDrivers();
    }

    @PutMapping("/{id}")
    public VisitorDriverDTO update(@PathVariable Long id, @RequestBody VisitorDriverDTO dto) {
        return service.updateVisitorDriver(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteVisitorDriver(id);
    }
}
