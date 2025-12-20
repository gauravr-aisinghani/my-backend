package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.YfsTransporterDetails;

public interface YfsTransporterDetailsRepository
        extends JpaRepository<YfsTransporterDetails, String> {

    boolean existsByGstNumber(String gstNumber);
}
