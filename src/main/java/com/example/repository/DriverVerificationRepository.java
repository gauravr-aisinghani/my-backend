package com.example.repository;

import com.example.entity.DriverVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverVerificationRepository extends JpaRepository<DriverVerification, Long> {
    Optional<DriverVerification> findByDriverRegistrationId(Long driverRegistrationId);
}
