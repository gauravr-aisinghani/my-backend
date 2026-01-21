package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.Payment;

import java.util.List;

public interface PaymentReportRepository extends JpaRepository<Payment, Long> {

    /* ===== SUMMARY ===== */

    @Query(value = "SELECT COUNT(*) FROM yfs_payments", nativeQuery = true)
    Long countAll();

    @Query(value = "SELECT IFNULL(SUM(amount),0) FROM yfs_payments", nativeQuery = true)
    Double sumAllAmount();

    @Query(value = "SELECT COUNT(*) FROM yfs_payments WHERE status = ?1", nativeQuery = true)
    Long countByStatus(String status);

    @Query(value = "SELECT COUNT(*) FROM yfs_payments WHERE payment_type = ?1", nativeQuery = true)
    Long countByType(String type);

    /* ===== TABLE ===== */

    @Query(value = """
        SELECT 
          id,
          gdc_number,
          payment_type,
          amount,
          status,
          razorpay_payment_id,
          created_at
        FROM yfs_payments
        WHERE (:paymentType IS NULL OR payment_type = :paymentType)
          AND (:status IS NULL OR status = :status)
        ORDER BY created_at DESC
    """, nativeQuery = true)
    List<Object[]> fetchPayments(String paymentType, String status);
}
