package com.example.repository;

import com.example.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByGdcNumberAndPaymentType(
            String gdcNumber,
            PaymentType paymentType
    );

    boolean existsByGdcNumberAndPaymentTypeAndStatus(
            String gdcNumber,
            PaymentType paymentType,
            PaymentStatus status
    );

    // ✅ ADD THIS METHOD
    Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);
}
