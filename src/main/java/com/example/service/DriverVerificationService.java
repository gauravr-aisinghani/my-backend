package com.example.service;

import com.example.dto.ApproveRequestDto;
import com.example.dto.PendingDriverDto;

import java.util.Map;
import java.util.List;

public interface DriverVerificationService {

    List<PendingDriverDto> getPendingDrivers();

    Map<String, String> getDriverDocuments(Long driverRegistrationId);

    void approveDriver(ApproveRequestDto request);

    void rejectDriver(ApproveRequestDto request);

    // 🔥 NEW METHOD TO FETCH APPROVED DRIVERS
    List<Map<String, Object>> getApprovedDrivers();
}
