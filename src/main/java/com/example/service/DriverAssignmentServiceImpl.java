package com.example.service;

import com.example.dto.AssignDriverRequestDto;
import com.example.dto.AssignDriverResponseDto;
import com.example.dto.CurrentPostingDto;
import com.example.entity.*;
import com.example.repository.*;
import com.example.service.DriverAssignmentService;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class DriverAssignmentServiceImpl implements DriverAssignmentService {

    private final TransporterDriverRequestRepository requestRepo;
    private final DriverAssignmentRepository assignmentRepo;
    private final DriverFinalSubmissionRepository driverRepo;

    public DriverAssignmentServiceImpl(
            TransporterDriverRequestRepository requestRepo,
            DriverAssignmentRepository assignmentRepo,
            DriverFinalSubmissionRepository driverRepo
    ) {
        this.requestRepo = requestRepo;
        this.assignmentRepo = assignmentRepo;
        this.driverRepo = driverRepo;
    }

    @Override
    public List<TransporterDriverRequest> getAdvancePaidRequests() {
        return requestRepo.findEligibleRequestsForAssignment();
    }

    @Override
    public List<DriverFinalSubmission> getAvailableDrivers() {
        return driverRepo.findAvailableDriversForAssignment();
    }

    @Override
    public AssignDriverResponseDto assignDriver(AssignDriverRequestDto dto) {

        TransporterDriverRequest request =
                requestRepo.findById(dto.getRequest_id())
                        .orElseThrow(() ->
                                new RuntimeException("Invalid request id"));

        if (assignmentRepo.existsByRequestId(dto.getRequest_id())) {
            throw new RuntimeException("Driver already assigned for this request");
        }

        if (assignmentRepo.existsByAssignedDriverRegistrationId(
                dto.getAssigned_driver_registration_id())) {
            throw new RuntimeException("Driver already assigned");
        }

        // 1️⃣ save assignment
        DriverAssignment assignment = new DriverAssignment();
        assignment.setRequestId(dto.getRequest_id());
        assignment.setAssignedDriverRegistrationId(
                dto.getAssigned_driver_registration_id());
        assignment.setTransporterRegistrationId(
                request.getTransporterRegistrationId());
        assignment.setTransporterPhone(request.getTransporterPhone());
        assignment.setAssignmentStatus("ASSIGNED");
        assignment.setRemarks(dto.getRemarks());

        assignmentRepo.save(assignment);

        // 2️⃣ update transporter request
        request.setAssignedDriverId(
                dto.getAssigned_driver_registration_id());
        request.setStatus(
                TransporterDriverRequest.Status.ASSIGNED);
        request.setCompletionStatus(
                TransporterDriverRequest.CompletionStatus.COMPLETED);

        requestRepo.save(request);

        return new AssignDriverResponseDto(
                assignment.getAssignmentId(),
                "Driver assigned successfully"
        );
    }
    
    
    @Override
    public List<CurrentPostingDto> getCurrentPostings() {
        return assignmentRepo.findCurrentPostings();
    }
}

