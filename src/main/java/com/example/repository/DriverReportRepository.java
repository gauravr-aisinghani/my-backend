package com.example.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Repository as Repo;

import java.util.List;

@Repo
public interface DriverReportRepository extends Repository<Object, Long> {

    /* Visitor funnel */
    @Query(value = "SELECT COUNT(*) FROM yfs_visitor_driver", nativeQuery = true)
    Long countVisitors();

    @Query(value = "SELECT COUNT(*) FROM yfs_selected_driver", nativeQuery = true)
    Long countSelectedVisitors();

    /* Registration funnel */
    @Query(value = "SELECT COUNT(*) FROM yfs_driver_details", nativeQuery = true)
    Long countRegisteredDrivers();

    @Query(value = "SELECT COUNT(*) FROM yfs_driver_documents", nativeQuery = true)
    Long countDocumentsUploaded();

    @Query(value = """
        SELECT COUNT(*) FROM yfs_driver_verification
        WHERE final_status IS NULL OR final_status != 'VERIFIED'
    """, nativeQuery = true)
    Long countVerificationPending();

    @Query(value = """
        SELECT COUNT(*) FROM yfs_driver_verification
        WHERE final_status = 'VERIFIED'
    """, nativeQuery = true)
    Long countVerifiedDrivers();

    @Query(value = """
        SELECT COUNT(*) FROM yfs_driver_final_submission
        WHERE gdc_registration_number IS NOT NULL
    """, nativeQuery = true)
    Long countGdcGenerated();

    /* Main report list */
    @Query(value = """
        SELECT
          d.driver_registration_id,
          d.full_name,
          d.mobile_number,
          CASE
            WHEN f.final_id IS NOT NULL THEN 'GDC_GENERATED'
            WHEN v.final_status = 'VERIFIED' THEN 'VERIFIED'
            WHEN doc.driver_document_id IS NOT NULL THEN 'DOCUMENTS_UPLOADED'
            ELSE 'REGISTERED'
          END AS stage,
          v.final_status,
          f.gdc_registration_number
        FROM yfs_driver_details d
        LEFT JOIN yfs_driver_documents doc
            ON d.driver_registration_id = doc.driver_registration_id
        LEFT JOIN yfs_driver_verification v
            ON d.driver_registration_id = v.driver_registration_id
        LEFT JOIN yfs_driver_final_submission f
            ON d.driver_registration_id = f.driver_registration_id
        ORDER BY d.created_at DESC
    """, nativeQuery = true)
    List<Object[]> fetchDriverReportRows();
}
