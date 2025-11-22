package com.example.repository;

import com.example.entity.DriverLicenceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverLicenceRepository extends JpaRepository<DriverLicenceDetails, Long> {
    Optional<DriverLicenceDetails> findByDriverRegistrationId(Long driverRegistrationId);
}
