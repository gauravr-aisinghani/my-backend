package com.example.repository;

import com.example.entity.TransporterFinalSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransporterFinalSubmissionRepository
        extends JpaRepository<TransporterFinalSubmission, Long> {

    Optional<TransporterFinalSubmission>
    findByGdcRegistrationNumber(String gdcRegistrationNumber);
}
