package com.example.repository;

import com.example.entity.YfsTransporterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransporterReportRepository
        extends JpaRepository<YfsTransporterDetails, String> {

    /* ================= SUMMARY ================= */

    @Query(value = "SELECT COUNT(*) FROM yfs_transporter_details", nativeQuery = true)
    Long countRegisteredTransporters();

    @Query(value = """
        SELECT COUNT(*)
        FROM yfs_transporter_details t
        LEFT JOIN yfs_transporter_verification v
          ON t.transporter_registration_id = v.transporter_registration_id
        WHERE v.transporter_registration_id IS NULL
    """, nativeQuery = true)
    Long countVerificationPending();

    @Query(value = """
        SELECT COUNT(*)
        FROM yfs_transporter_verification
        WHERE final_status = 'APPROVED'
    """, nativeQuery = true)
    Long countVerifiedTransporters();

    @Query(value = """
        SELECT COUNT(*)
        FROM yfs_transporter_final_submission
        WHERE gdc_registration_number IS NOT NULL
    """, nativeQuery = true)
    Long countGdcGenerated();

    /* ================= TABLE ================= */

    @Query(value = """
        SELECT
          t.transporter_registration_id,
          t.transport_company_name,
          t.owner_mobile_number,
          CASE
            WHEN f.final_id IS NOT NULL THEN 'GDC_GENERATED'
            WHEN v.final_status = 'APPROVED' THEN 'VERIFIED'
            ELSE 'REGISTERED'
          END AS stage,
          v.final_status,
          f.gdc_registration_number
        FROM yfs_transporter_details t
        LEFT JOIN yfs_transporter_verification v
            ON t.transporter_registration_id = v.transporter_registration_id
        LEFT JOIN yfs_transporter_final_submission f
            ON t.transporter_registration_id = f.transporter_registration_id
        WHERE
          (:stage = 'REGISTERED' AND v.transporter_registration_id IS NULL)
          OR (:stage = 'VERIFIED' AND v.final_status = 'APPROVED')
          OR (:stage = 'GDC_GENERATED' AND f.gdc_registration_number IS NOT NULL)
        ORDER BY t.created_at DESC
    """, nativeQuery = true)
    List<Object[]> fetchTransporterReportRowsByStage(String stage);
}
