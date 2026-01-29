package com.example.repository;

import com.example.entity.TransporterDriverRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransporterDriverRequestRepository
        extends JpaRepository<TransporterDriverRequest, Long> {

    /**
     * ✅ Admin ke liye:
     * Approved request + usi request ka TRANSPORTER_ADVANCE payment PAID
     */
    @Query("""
        SELECT r
        FROM TransporterDriverRequest r
        WHERE r.status = 'APPROVED'
          AND r.completionStatus = 'PENDING'
          AND EXISTS (
              SELECT 1
              FROM Payment p
              WHERE p.requestId = r.id
                AND p.status = 'PAID'
                AND p.purpose = 'TRANSPORTER_ADVANCE'
          )
    """)
    List<TransporterDriverRequest> findEligibleRequestsForAssignment();
}
