package com.example.repository;

import com.example.entity.FinalGdc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalGdcRepository extends JpaRepository<FinalGdc, Long> {

    boolean existsByVerificationId(Long verificationId);

    FinalGdc findByVerificationId(Long verificationId);
}
