package com.example.repository;

import com.example.entity.DriverFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverFinalRepository extends JpaRepository<DriverFinal, Long> {

    // Needed so backend can check if final submission already exists
    Optional<DriverFinal> findByDriverRegistrationId(Long driverRegistrationId);
}
