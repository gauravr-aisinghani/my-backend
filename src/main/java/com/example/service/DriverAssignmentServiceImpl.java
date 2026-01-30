package com.example.service;

import com.example.dto.AssignDriverRequestDto;
import com.example.dto.AssignDriverResponseDto;
import com.example.entity.*;
import com.example.repository.*;
import com.example.service.DriverAssignmentService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DriverAssignmentServiceImpl implements DriverAssignmentService {

    private final YfsDriverAssignmentRepository assignmentRepo;
    private final TransporterDriverRequestRepository requestRepo;
    private final DriverFinalSubmissionRepository driverFinalRepo;
    private final PaymentRepository paymentRepo;

    public DriverAssignmentServiceImpl(
            YfsDriverAssignmentRepository assignmentRepo,
            TransporterDriverRequestRepository requestRepo,
            DriverFinalSubmissionRepository driverFinalRepo,
            PaymentRepository paymentRepo
    ) {
        this.assignmentRepo = assignmentRepo;
        this.requestRepo = requestRepo;
        this.driverFinalRepo = driverFinalRepo;
        this.paymentRepo = paymentRepo;
    }

    @Override
    @Transactional
    public AssignDriverResponseDto assignDriver(AssignDriverRequestDto dto) {

        // 1️⃣ Request exists
        TransporterDriverRequest request =
                requestRepo.findById(dto.getRequestId())
                        .orElseThrow(() ->
                                new RuntimeException("Driver request not found"));

        // 2️⃣ Advance payment check
        boolean advancePaid =
                paymentRepo.existsByRequestIdAndPurposeAndStatus(
                        dto.getRequestId(),
                        PaymentPurpose.TRANSPORTER_ADVANCE,
                        PaymentStatus.PAID
                );

        if (!advancePaid)
            throw new RuntimeException("Advance payment not done");

        // 3️⃣ Driver final submission check
        DriverFinalSubmission driver =
                driverFinalRepo.findByDriverRegistrationId(
                        dto.getDriverRegistrationId())
                        .orElseThrow(() ->
                                new RuntimeException("Driver not completed verification"));

        if (!"COMPLETED".equalsIgnoreCase(driver.getCompletionStatus()))
            throw new RuntimeException("Driver profile not completed");

        // 4️⃣ Driver already assigned?
        if (assignmentRepo.existsByAssignedDriverRegistrationId(
                dto.getDriverRegistrationId()))
            throw new RuntimeException("Driver already assigned");

        // 5️⃣ Request already fulfilled?
        if (assignmentRepo.existsByRequestId(dto.getRequestId()))
            throw new RuntimeException("Request already fulfilled");

        // 6️⃣ INSERT assignment
        YfsDriverAssignment assignment = new YfsDriverAssignment();
        assignment.setRequestId(dto.getRequestId());
        assignment.setAssignedDriverRegistrationId(dto.getDriverRegistrationId());
        assignment.setTransporterRegistrationId(
                request.getTransporterRegistrationId() == null
                        ? null
                        : request.getTransporterRegistrationId().getMostSignificantBits()
        );
        assignment.setTransporterPhone(request.getTransporterPhone());
        assignment.setAssignedBy(dto.getAdminId());
        assignment.setAssignmentStatus("ASSIGNED");
        assignment.setRemarks(dto.getRemarks());

        assignmentRepo.save(assignment);

        // 7️⃣ UPDATE request table
        request.setAssignedDriverId(dto.getDriverRegistrationId());
        request.setCompletionStatus(
                TransporterDriverRequest.CompletionStatus.COMPLETED);
        request.setStatus(
                TransporterDriverRequest.Status.ASSIGNED);

        requestRepo.save(request);

        return new AssignDriverResponseDto(
                "Driver assigned successfully",
                assignment.getAssignmentId()
        );
    }
}
