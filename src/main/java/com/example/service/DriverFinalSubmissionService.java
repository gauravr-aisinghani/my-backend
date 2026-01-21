package com.example.service;


import com.example.dto.FinalSubmissionRequestDto;

import com.example.dto.FinalSubmissionResponseDto;


public interface DriverFinalSubmissionService {
FinalSubmissionResponseDto generateFinalSubmission(FinalSubmissionRequestDto dto) throws Exception;
}