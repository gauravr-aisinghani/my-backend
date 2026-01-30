package com.example.service;

import java.util.List;

import com.example.dto.AssignDriverRequestDto;
import com.example.dto.AssignDriverResponseDto;
import com.example.entity.DriverFinalSubmission;
import com.example.entity.TransporterDriverRequest;

public interface DriverAssignmentService {

    List<TransporterDriverRequest> getAdvancePaidRequests();

    List<DriverFinalSubmission> getAvailableDrivers();

    AssignDriverResponseDto assignDriver(AssignDriverRequestDto dto);
}
