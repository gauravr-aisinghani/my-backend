package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.DriverDetails;
import java.util.Optional;

public interface DriverDetailsRepository extends JpaRepository<DriverDetails, Long> {

    Optional<DriverDetails> findByMobileNumber(String mobileNumber);
    Optional<DriverDetails> findByAadharNo(String aadharNo);

    // 🔥 New: fetch driver details using driver_registration_id
    Optional<DriverDetails> findByDriverRegistrationId(Long driverRegistrationId);
}
