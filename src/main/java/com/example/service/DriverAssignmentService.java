package com.example.service;

import com.example.dto.AssignDriverRequestDto;
import com.example.dto.AssignDriverResponseDto;

public interface DriverAssignmentService {

    AssignDriverResponseDto assignDriver(AssignDriverRequestDto dto);
}
