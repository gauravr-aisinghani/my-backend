package com.example.repository;

import com.example.entity.YfsDriverPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface YfsDriverPaymentRepository extends JpaRepository<YfsDriverPayment, Long> {

    Optional<YfsDriverPayment> findTopByDriverRegistrationIdOrderByCreatedAtDesc(Long driverRegistrationId);

    Optional<YfsDriverPayment> findByGdcRegistrationNumber(String gdcRegistrationNumber);
}
