package com.example.repository;

import com.example.entity.DriverExperience;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverExperienceRepository extends JpaRepository<DriverExperience, Long> {

    Optional<DriverExperience> findByDriverRegistrationId(Long driverRegistrationId);

    List<DriverExperience> findAllByDriverRegistrationId(Long driverRegistrationId);
}
