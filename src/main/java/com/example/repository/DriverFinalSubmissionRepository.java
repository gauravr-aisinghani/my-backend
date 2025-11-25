package com.example.repository;


import com.example.entity.DriverFinalSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverFinalSubmissionRepository extends JpaRepository<DriverFinalSubmission, Long> {
boolean existsByVerificationId(Long verificationId);
}