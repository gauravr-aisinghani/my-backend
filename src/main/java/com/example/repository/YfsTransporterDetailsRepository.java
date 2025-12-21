package com.example.repository;

import com.example.entity.YfsTransporterDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface YfsTransporterDetailsRepository
        extends JpaRepository<YfsTransporterDetails, String> {

    boolean existsByGstNumber(String gstNumber);

    Optional<YfsTransporterDetails> findByTransporterRegistrationId(String transporterRegistrationId);
}
