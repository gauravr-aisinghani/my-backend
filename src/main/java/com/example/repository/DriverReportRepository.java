package com.example.repository;

import com.example.entity.DriverDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriverReportRepository extends JpaRepository<DriverDetails, Long> {

    /* ================= SUMMARY ================= */

    @Query(value = "SELECT COUNT(*) FROM yfs_visitor_driver", nativeQuery = true)
    Long countVisitors();

    @Query(value = "SELECT COUNT(*) FROM yfs_selected_driver", nativeQuery = true)
    Long countSelectedVisitors();

    @Query(value = "SELECT COUNT(*) FROM yfs_driver_details", nativeQuery = true)
    Long countRegisteredDrivers();

    @Query(value = """
        SELECT COUNT(DISTINCT doc.driver_registration_id)
        FROM yfs_driver_documents doc
        LEFT JOIN yfs_driver_verification v
          ON doc.driver_registration_id = v.driver_registration_id
        WHERE v.driver_registration_id IS NULL
    """, nativeQuery = true)
    Long countVerificationPending();

    @Query(value = """
        SELECT COUNT(DISTINCT driver_registration_id)
        FROM yfs_driver_final_submission
        WHERE gdc_registration_number IS NOT NULL
    """, nativeQuery = true)
    Long countGdcGenerated();

    /* ================= TABLE (STAGE BASED ONLY) ================= */

    @Query(value = """
        SELECT
          d.driver_registration_id,
          d.full_name,
          d.mobile_number,
          CASE
            WHEN f.final_id IS NOT NULL THEN 'GDC_GENERATED'
            WHEN v.final_status = 'VERIFIED' THEN 'VERIFIED'
            ELSE 'REGISTERED'
          END AS stage,
          v.final_status,
          f.gdc_registration_number
        FROM yfs_driver_details d
        LEFT JOIN yfs_driver_verification v
            ON d.driver_registration_id = v.driver_registration_id
        LEFT JOIN yfs_driver_final_submission f
            ON d.driver_registration_id = f.driver_registration_id
        WHERE
          (:stage = 'REGISTERED' AND v.driver_registration_id IS NULL AND f.driver_registration_id IS NULL)
          OR (:stage = 'VERIFIED' AND v.final_status = 'VERIFIED')
          OR (:stage = 'GDC_GENERATED' AND f.gdc_registration_number IS NOT NULL)
        ORDER BY d.created_at DESC
    """, nativeQuery = true)
    List<Object[]> fetchDriverReportRowsByStage(String stage);
}
