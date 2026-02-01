package com.example.service;

import com.example.dto.AssignDriverRequestDto;
import com.example.dto.CurrentPostingDto;
import com.example.dto.IdealDriverDto;
import com.example.dto.AssignDriverResponseDto;
import com.example.entity.*;
import com.example.repository.*;
import jakarta.transaction.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
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
                request.getTransporterRegistrationId() // now String
        );
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

        List<Object[]> rows = assignmentRepo.findCurrentPostingsRaw();
        List<CurrentPostingDto> result = new ArrayList<>();

        for (Object[] row : rows) {
            Long assignmentId = ((Number) row[0]).longValue();
            String driverName = (String) row[1];
            String transporterName = (String) row[2];
            String status = (String) row[3];
            Timestamp ts = (Timestamp) row[4];

            result.add(
                new CurrentPostingDto(
                    assignmentId,
                    driverName,
                    transporterName,
                    status,
                    ts.toLocalDateTime()
                )
            );
        }

        return result;
    }
    
    
    @Override
    public List<IdealDriverDto> getIdealDrivers() {

        List<Object[]> rows = assignmentRepo.findIdealDriversRaw();
        List<IdealDriverDto> result = new ArrayList<>();

        for (Object[] r : rows) {
            IdealDriverDto dto = new IdealDriverDto();
            dto.setDriverRegistrationId(((Number) r[0]).longValue());
            dto.setDriverName((String) r[1]);
            dto.setMobileNumber((String) r[2]);
            dto.setGdcNumber((String) r[3]);
            dto.setPaymentDate(((Timestamp) r[4]).toLocalDateTime());
            dto.setIdleSince(((Timestamp) r[5]).toLocalDateTime());
            result.add(dto);
        }

        return result;
    }


}
