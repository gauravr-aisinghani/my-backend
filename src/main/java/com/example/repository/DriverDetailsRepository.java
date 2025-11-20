package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.DriverDetails;
import java.util.Optional;

public interface DriverDetailsRepository extends JpaRepository<DriverDetails, Long> {
    Optional<DriverDetails> findByMobile1(String mobile1);
    Optional<DriverDetails> findByAadharNo(String aadharNo);
}
