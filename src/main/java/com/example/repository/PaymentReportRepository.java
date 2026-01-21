package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.entity.Payment;

import java.util.List;

public interface PaymentReportRepository extends JpaRepository<Payment, Long> {

    /* ===== SUMMARY ===== */

    @Query(value = """
        SELECT COUNT(*)
        FROM yfs_payments
        WHERE (:paymentType IS NULL OR payment_type = :paymentType)
          AND (:status IS NULL OR status = :status)
          AND (:start IS NULL OR created_at >= :start)
          AND (:end IS NULL OR created_at <= :end)
    """, nativeQuery = true)
    Long countAllFiltered(String paymentType, String status, String start, String end);

    @Query(value = """
        SELECT IFNULL(SUM(amount),0)
        FROM yfs_payments
        WHERE (:paymentType IS NULL OR payment_type = :paymentType)
          AND (:status IS NULL OR status = :status)
          AND (:start IS NULL OR created_at >= :start)
          AND (:end IS NULL OR created_at <= :end)
    """, nativeQuery = true)
    Double sumAmountFiltered(String paymentType, String status, String start, String end);

    @Query(value = """
        SELECT COUNT(*)
        FROM yfs_payments
        WHERE status = :status
          AND (:paymentType IS NULL OR payment_type = :paymentType)
          AND (:start IS NULL OR created_at >= :start)
          AND (:end IS NULL OR created_at <= :end)
    """, nativeQuery = true)
    Long countByStatusFiltered(String status, String paymentType, String start, String end);

    @Query(value = """
        SELECT COUNT(*)
        FROM yfs_payments
        WHERE payment_type = :type
          AND (:status IS NULL OR status = :status)
          AND (:start IS NULL OR created_at >= :start)
          AND (:end IS NULL OR created_at <= :end)
    """, nativeQuery = true)
    Long countByTypeFiltered(String type, String status, String start, String end);

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
          AND (:start IS NULL OR created_at >= :start)
          AND (:end IS NULL OR created_at <= :end)
        ORDER BY created_at DESC
    """, nativeQuery = true)
    List<Object[]> fetchPayments(String paymentType, String status, String start, String end);
}


