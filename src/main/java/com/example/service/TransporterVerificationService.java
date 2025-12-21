package com.example.service;

import com.example.dto.ApproveRequestTransporterDto;
import com.example.dto.PendingTransporterDto;

import java.util.List;
import java.util.Map;

public interface TransporterVerificationService {

    List<PendingTransporterDto> getPendingTransporters();

    Map<String, String> getTransporterDocuments(String transporterRegistrationId);

    void approveTransporter(ApproveRequestTransporterDto request);

    void rejectTransporter(ApproveRequestTransporterDto request);

    List<Map<String, Object>> getApprovedTransporters();
}
