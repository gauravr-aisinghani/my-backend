package com.example.controller;

import com.example.dto.VisitorTransporterDTO;
import com.example.service.VisitorTransporterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitor-transporter")
@CrossOrigin
public class VisitorTransporterController {

    private final VisitorTransporterService service;

    public VisitorTransporterController(VisitorTransporterService service) {
        this.service = service;
    }

    @PostMapping
    public VisitorTransporterDTO save(@RequestBody VisitorTransporterDTO dto) {
        return service.save(dto);
    }

    @GetMapping
    public List<VisitorTransporterDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VisitorTransporterDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public VisitorTransporterDTO update(
            @PathVariable Long id,
            @RequestBody VisitorTransporterDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
