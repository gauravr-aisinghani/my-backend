package com.example.repository;

import com.example.dto.CurrentPostingDto;
import com.example.dto.IdealDriverDto;
import com.example.entity.DriverAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverAssignmentRepository
        extends JpaRepository<DriverAssignment, Long> {

    boolean existsByAssignedDriverRegistrationId(Long driverRegistrationId);

    boolean existsByRequestId(Long requestId);

    // ✅ CURRENT POSTINGS (NATIVE QUERY)
    @Query(value = """
        SELECT
            da.assignment_id        AS assignmentId,
            d.full_name             AS driverName,
            t.transport_company_name AS transporterName,
            da.assignment_status    AS assignmentStatus,
            da.assigned_at          AS assignedAt
        FROM yfs_driver_assignments da
        JOIN yfs_driver_details d
            ON d.driver_registration_id = da.assigned_driver_registration_id
        JOIN yfs_transporter_details t
            ON t.transporter_registration_id = da.transporter_registration_id
        WHERE da.assignment_status = 'ASSIGNED'
        ORDER BY da.assigned_at DESC
    """, nativeQuery = true)
    List<Object[]> findCurrentPostingsRaw();
    
    
    @Query(value = """
    	    SELECT
    	        d.driver_registration_id,
    	        d.full_name,
    	        d.mobile_number,
    	        fs.gdc_registration_number,
    	        MAX(p.created_at) AS paymentDate,
    	        COALESCE(MAX(da.released_at), MAX(p.created_at)) AS idleSince
    	    FROM yfs_driver_final_submission fs
    	    JOIN yfs_driver_details d
    	        ON d.driver_registration_id = fs.driver_registration_id
    	    JOIN yfs_payments p
    	        ON p.gdc_number = fs.gdc_registration_number
    	       AND p.status = 'PAID'
    	       AND p.purpose = 'DRIVER_REGISTRATION'
    	    LEFT JOIN yfs_driver_assignments da
    	        ON da.assigned_driver_registration_id = d.driver_registration_id
    	       AND da.assignment_status IN ('COMPLETED', 'CANCELLED')
    	    WHERE fs.completion_status = 'COMPLETED'
    	      AND NOT EXISTS (
    	            SELECT 1
    	            FROM yfs_driver_assignments x
    	            WHERE x.assigned_driver_registration_id = d.driver_registration_id
    	              AND x.assignment_status = 'ASSIGNED'
    	      )
    	    GROUP BY
    	        d.driver_registration_id,
    	        d.full_name,
    	        d.mobile_number,
    	        fs.gdc_registration_number
    	    ORDER BY idleSince DESC
    	""", nativeQuery = true)
    	List<Object[]> findIdealDriversRaw();




}
