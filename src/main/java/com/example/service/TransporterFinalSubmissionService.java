package com.example.service;

import com.example.dto.TransporterFinalSubmissionRequestDto;
import com.example.dto.TransporterFinalSubmissionResponseDto;

public interface TransporterFinalSubmissionService {

    TransporterFinalSubmissionResponseDto generate(
            TransporterFinalSubmissionRequestDto dto
    ) throws Exception;
}
