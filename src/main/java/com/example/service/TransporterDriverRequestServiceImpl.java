package com.example.service;

import com.example.dto.TransporterDriverRequestDTO;
import com.example.entity.TransporterDriverRequest;
import com.example.repository.TransporterDriverRequestRepository;
import com.example.service.TransporterDriverRequestService;
import com.example.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for Transporter Driver Requests
 */
@Service
public class TransporterDriverRequestServiceImpl implements TransporterDriverRequestService {

    private final TransporterDriverRequestRepository repository;
    private final NotificationService notificationService;

    public TransporterDriverRequestServiceImpl(TransporterDriverRequestRepository repository,
                                               NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    @Override
    public TransporterDriverRequest createRequest(TransporterDriverRequestDTO dto) throws Exception {

        // map DTO to entity
        TransporterDriverRequest request = new TransporterDriverRequest();
        request.setTransporterRegistrationId(dto.getTransporterRegistrationId());
        request.setTransporterPhone(dto.getTransporterPhone());
        request.setGdcNumber(dto.getGdcNumber());
        request.setVehicleNumber(dto.getVehicleNumber());
        request.setRoute(dto.getRoute());
        request.setMonthlySalary(dto.getMonthlySalary());
        request.setRemarks(dto.getRemarks());

        try {
        	// save request to DB
        	TransporterDriverRequest savedRequest = repository.save(request);

        	// 🔔 ADMIN notify (DB + WS)
        	notificationService.notifyAdmins(
        	        "New Driver Request",
        	        "Transporter " + dto.getTransporterPhone()
        	        + " raised a new driver request (Request ID: "
        	        + savedRequest.getRequestId() + ")"
        	);

        	return savedRequest;


        } catch (Exception e) {
            throw new Exception("Failed to create driver request: " + e.getMessage());
        }
    }

    @Override
    public List<TransporterDriverRequest> getAllRequests() {
        return repository.findAll();
    }

    @Override
    public TransporterDriverRequest getRequestById(Long requestId) throws Exception {
        Optional<TransporterDriverRequest> optional = repository.findById(requestId);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new Exception("Driver request not found with ID: " + requestId);
        }
    }
}
