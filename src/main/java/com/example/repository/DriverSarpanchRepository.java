package com.example.repository;

import com.example.entity.DriverSarpanchDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DriverSarpanchRepository extends JpaRepository<DriverSarpanchDetails, Long> {

    Optional<DriverSarpanchDetails> findByDriverRegistrationId(Long driverRegistrationId);

}
