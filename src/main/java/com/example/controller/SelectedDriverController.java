package com.example.controller;

import com.example.dto.SelectedDriverDTO;
import com.example.service.SelectedDriverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/selected-driver")
@CrossOrigin(origins = "*")
public class SelectedDriverController {

    private final SelectedDriverService service;

    public SelectedDriverController(SelectedDriverService service) {
        this.service = service;
    }

    @PostMapping
    public SelectedDriverDTO save(@RequestBody SelectedDriverDTO dto) {
        return service.saveSelectedDriver(dto);
    }

    @GetMapping("/{id}")
    public SelectedDriverDTO getOne(@PathVariable Long id) {
        return service.getSelectedDriver(id);
    }

    @GetMapping
    public List<SelectedDriverDTO> getAll() {
        return service.getAllSelectedDrivers();
    }

    @PutMapping("/{id}")
    public SelectedDriverDTO update(@PathVariable Long id, @RequestBody SelectedDriverDTO dto) {
        return service.updateSelectedDriver(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteSelectedDriver(id);
    }
}
