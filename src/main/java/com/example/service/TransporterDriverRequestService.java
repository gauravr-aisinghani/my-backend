package com.example.service;

import com.example.dto.TransporterDriverRequestDTO;
import com.example.entity.TransporterDriverRequest;

import java.util.List;

public interface TransporterDriverRequestService {

    TransporterDriverRequest createRequest(TransporterDriverRequestDTO dto) throws Exception;

    List<TransporterDriverRequest> getAllRequests();

    TransporterDriverRequest getRequestById(Long requestId) throws Exception;

    TransporterDriverRequest acceptDriverRequest(Long requestId) throws Exception;

    // ✅ ADD THIS
    List<TransporterDriverRequest> getEligibleRequestsForAssignment();
}
