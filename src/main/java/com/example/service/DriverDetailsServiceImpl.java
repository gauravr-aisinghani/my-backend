package com.example.service;

import com.example.dto.DriverDetailsDTO;
import com.example.entity.DriverDetails;
import com.example.repository.DriverDetailsRepository;
import com.example.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DriverDetailsServiceImpl implements DriverDetailsService {

    private final DriverDetailsRepository repository;
    private final Map<String, String> otpStore = new ConcurrentHashMap<>();

    @Autowired
    public DriverDetailsServiceImpl(DriverDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public DriverDetails createDriver(DriverDetailsDTO dto) {
        // duplicate checks
        repository.findByMobileNumber(dto.getMobileNumber())
                .ifPresent(d -> { throw new RuntimeException("Mobile number already registered"); });

        repository.findByAadharNo(dto.getAadharNo())
                .ifPresent(d -> { throw new RuntimeException("Aadhar already registered"); });

        DriverDetails driver = dto.toEntity();
        return repository.save(driver);
    }

    @Override
    public DriverDetails getDriverById(Long driverId) {
        return repository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + driverId));
    }

    @Override
    public List<DriverDetails> getAllDrivers() {
        return repository.findAll();
    }

    @Override
    public DriverDetails updateDriver(Long driverId, DriverDetailsDTO dto) {
        DriverDetails existing = getDriverById(driverId);
        DriverDetails updated = dto.toEntity();
        // preserve primary key so save() does update
        updated.setDriverRegistrationId(existing.getDriverRegistrationId());
        return repository.save(updated);
    }

    @Override
    public void deleteDriver(Long driverId) {
        DriverDetails existing = getDriverById(driverId);
        repository.delete(existing);
    }

    @Override
    public String sendOtp(String mobileNumber) {
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStore.put(mobileNumber, otp);
        System.out.println("Mock OTP -> " + mobileNumber + " : " + otp);
        return "OTP_SENT";
    }

    @Override
    public boolean verifyOtp(String mobileNumber, String otp) {
        String stored = otpStore.get(mobileNumber);
        if (stored != null && stored.equals(otp)) {
            otpStore.remove(mobileNumber);
            return true;
        }
        return false;
    }

    @Override
    public boolean existsByMobile(String mobileNumber) {
        return repository.findByMobileNumber(mobileNumber).isPresent();
    }

    @Override
    public boolean existsByAadhar(String aadharNo) {
        return repository.findByAadharNo(aadharNo).isPresent();
    }
}
