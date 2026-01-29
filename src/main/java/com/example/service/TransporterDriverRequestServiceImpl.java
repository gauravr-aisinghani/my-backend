package com.example.service;

import com.example.dto.TransporterDriverRequestDTO;
import com.example.entity.VehicleGrade;

import com.example.entity.TransporterDriverRequest;
import com.example.repository.TransporterDriverRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransporterDriverRequestServiceImpl implements TransporterDriverRequestService {

    private final TransporterDriverRequestRepository repository;
    private final NotificationService notificationService;

    public TransporterDriverRequestServiceImpl(
            TransporterDriverRequestRepository repository,
            NotificationService notificationService
    ) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    
    @Override
    public List<TransporterDriverRequest> getEligibleRequestsForAssignment() {
        return repository.findEligibleRequestsForAssignment();
    }

    @Override
    public TransporterDriverRequest createRequest(TransporterDriverRequestDTO dto) throws Exception {

        TransporterDriverRequest request = new TransporterDriverRequest();
        request.setTransporterRegistrationId(dto.getTransporterRegistrationId());
        request.setTransporterPhone(dto.getTransporterPhone());
        request.setGdcNumber(dto.getGdcNumber());
        request.setVehicleNumber(dto.getVehicleNumber());
        request.setVehicleGrade(
        	    VehicleGrade.valueOf(dto.getVehicleGrade())
        	);

        request.setRoute(dto.getRoute());
        request.setMonthlySalary(dto.getMonthlySalary());
        request.setRemarks(dto.getRemarks());

        try {
            TransporterDriverRequest savedRequest = repository.save(request);

            // 🔔 CLEAN ADMIN NOTIFICATION
            notificationService.notifyAdmins(
                    "New Driver Request",
                    "New driver request received",
                    "DRIVER_REQUEST",
                    savedRequest.getRequestId()
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
    public TransporterDriverRequest acceptDriverRequest(Long requestId) throws Exception {

        TransporterDriverRequest request = repository.findById(requestId)
                .orElseThrow(() -> new Exception("Driver request not found"));

        // ❌ already processed check
        if (request.getStatus() != TransporterDriverRequest.Status.PENDING) {
            throw new Exception("Request already processed");
        }

        // 🔢 BACKEND CALCULATION (20%)
        double salary = request.getMonthlySalary();
        double advanceAmount = salary * 0.20;

        // ✅ Update status
        request.setStatus(TransporterDriverRequest.Status.APPROVED);
        request.setUpdatedAt(java.time.LocalDateTime.now());

        TransporterDriverRequest saved = repository.save(request);

        // 🔔 TRANSPORTER NOTIFICATION (DB + WebSocket)
        notificationService.notifyTransporter(
                request.getTransporterPhone(),
                "Driver Request Accepted",
                "Your driver request has been approved. Please pay 20% advance ₹"
                        + advanceAmount
        );

        return saved;
    }


    @Override
    public TransporterDriverRequest getRequestById(Long requestId) throws Exception {
        Optional<TransporterDriverRequest> optional = repository.findById(requestId);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new Exception("Driver request not found with ID: " + requestId);
    }
}
