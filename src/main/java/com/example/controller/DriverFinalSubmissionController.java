package com.example.controller;



import com.example.dto.FinalSubmissionRequestDto;

import com.example.dto.FinalSubmissionResponseDto;
import com.example.service.DriverFinalSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/gdc")
public class DriverFinalSubmissionController {


private final DriverFinalSubmissionService service;


public DriverFinalSubmissionController(DriverFinalSubmissionService service) {
this.service = service;
}


@PostMapping("/generate")
public ResponseEntity<FinalSubmissionResponseDto> generate(@RequestBody FinalSubmissionRequestDto dto) throws Exception {
FinalSubmissionResponseDto res = service.generateFinalSubmission(dto);
return ResponseEntity.ok(res);
}
}