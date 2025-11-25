package com.example.controller;

import com.example.dto.FinalGdcRequestDto;
import com.example.dto.FinalGdcResponseDto;
import com.example.service.FinalGdcService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/gdc")
@CrossOrigin
public class FinalGdcController {

    private final FinalGdcService service;

    public FinalGdcController(FinalGdcService service) {
        this.service = service;
    }

    @PostMapping("/generate")
    public FinalGdcResponseDto generate(@RequestBody FinalGdcRequestDto dto) {
        return service.generateGdc(dto);
    }
}
