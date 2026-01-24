package com.example.service;

import com.example.dto.TransporterDriverRequestDTO;
import com.example.entity.TransporterDriverRequest;

import java.util.List;

/**
 * Service interface for Transporter Driver Requests
 */
public interface TransporterDriverRequestService {

    /**
     * Create a new driver request
     */
    TransporterDriverRequest createRequest(TransporterDriverRequestDTO dto) throws Exception;

    /**
     * Fetch all driver requests
     */
    List<TransporterDriverRequest> getAllRequests();

    /**
     * Fetch request by ID
     */
    TransporterDriverRequest getRequestById(Long requestId) throws Exception;
}
