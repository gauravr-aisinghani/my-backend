package com.example.service;

import com.example.dto.FinalGdcRequestDto;
import com.example.dto.FinalGdcResponseDto;

public interface FinalGdcService {
    FinalGdcResponseDto generateGdc(FinalGdcRequestDto request);
}
