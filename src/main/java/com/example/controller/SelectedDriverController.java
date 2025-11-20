package com.example.controller;

import com.example.dto.SelectedDriverDTO;
import com.example.dto.VisitorDriverDTO;
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
    public SelectedDriverDTO addSelectedDriver(@RequestBody VisitorDriverDTO visitor) {
        return service.addSelectedDriver(visitor);
    }

    @GetMapping
    public List<SelectedDriverDTO> getAllSelectedDrivers() {
        return service.getAllSelectedDrivers();
    }
    
   

}
