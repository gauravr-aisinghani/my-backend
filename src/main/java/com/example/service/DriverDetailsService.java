package com.example.service;

import com.example.dto.DriverDetailsDTO;
import com.example.entity.DriverDetails;

import java.util.List;

public interface DriverDetailsService {

    DriverDetails createDriver(DriverDetailsDTO dto);

    DriverDetails getDriverById(Long driverId);

    List<DriverDetails> getAllDrivers();

    DriverDetails updateDriver(Long driverId, DriverDetailsDTO dto);

    void deleteDriver(Long driverId);

    // OTP
    String sendOtp(String mobileNumber);

    boolean verifyOtp(String mobileNumber, String otp);

    // Duplicates
    boolean existsByMobile(String mobileNumber);

    boolean existsByAadhar(String aadharNo);
}
