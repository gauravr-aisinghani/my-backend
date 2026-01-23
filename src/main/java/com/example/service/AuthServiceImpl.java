package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.LoginRequestDto;
import com.example.dto.LoginResponseDto;
import com.example.entity.DriverDetails;
import com.example.entity.DriverFinalSubmission;
import com.example.entity.YfsTransporterDetails;
import com.example.entity.TransporterFinalSubmission;
import com.example.repository.DriverDetailsRepository;
import com.example.repository.DriverFinalSubmissionRepository;
import com.example.repository.YfsTransporterDetailsRepository;
import com.example.repository.TransporterFinalSubmissionRepository;
import com.example.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private YfsTransporterDetailsRepository transporterDetailsRepository;

    @Autowired
    private TransporterFinalSubmissionRepository transporterFinalSubmissionRepository;

    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Autowired
    private DriverFinalSubmissionRepository driverFinalSubmissionRepository;

    @Override
    public LoginResponseDto login(LoginRequestDto request) {

        if ("TRANSPORTER".equalsIgnoreCase(request.getRole())) {
            return transporterLogin(request.getMobile());
        }

        if ("DRIVER".equalsIgnoreCase(request.getRole())) {
            return driverLogin(request.getMobile());
        }

        return new LoginResponseDto(false, "Invalid role");
    }

    // ================= TRANSPORTER LOGIN =================
    private LoginResponseDto transporterLogin(String mobile) {

        Optional<YfsTransporterDetails> transporterOpt =
                transporterDetailsRepository.findByOwnerMobileNumber(mobile);

        if (transporterOpt.isEmpty()) {
            return new LoginResponseDto(false, "Transporter not registered");
        }

        String transporterRegId =
                transporterOpt.get().getTransporterRegistrationId();

        Optional<TransporterFinalSubmission> finalOpt =
                transporterFinalSubmissionRepository
                        .findByTransporterRegistrationId(transporterRegId);

        if (finalOpt.isEmpty()) {
            return new LoginResponseDto(false, "Transporter GDC not submitted");
        }

        if (!"COMPLETED".equalsIgnoreCase(
                finalOpt.get().getCompletionStatus())) {
            return new LoginResponseDto(false, "Transporter not approved yet");
        }

        return new LoginResponseDto(true, "Transporter login successful");
    }

    // ================= DRIVER LOGIN =================
    private LoginResponseDto driverLogin(String mobile) {

        Optional<DriverDetails> driverOpt =
                driverDetailsRepository.findByMobileNumber(mobile);

        if (driverOpt.isEmpty()) {
            return new LoginResponseDto(false, "Driver not registered");
        }

        Long driverRegId =
                driverOpt.get().getDriverRegistrationId();

        Optional<DriverFinalSubmission> finalOpt =
                driverFinalSubmissionRepository
                        .findByDriverRegistrationId(driverRegId);

        if (finalOpt.isEmpty()) {
            return new LoginResponseDto(false, "Driver GDC not submitted");
        }

        if (!"COMPLETED".equalsIgnoreCase(
                finalOpt.get().getCompletionStatus())) {
            return new LoginResponseDto(false, "Driver not approved yet");
        }

        return new LoginResponseDto(true, "Driver login successful");
    }
}
