package com.example.repository;

import com.example.dto.FinalDriverProfileDTO;
import com.example.entity.DriverFinalSubmission;
import com.example.entity.DriverDetails;
import com.example.entity.DriverDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverFinalSubmissionRepository
        extends JpaRepository<DriverFinalSubmission, Long> {

    boolean existsByVerificationId(Long verificationId);

    // 🔥 REQUIRED FOR LOGIN (ADDED – SAFE)
    Optional<DriverFinalSubmission>
    findByDriverRegistrationId(Long driverRegistrationId);

    // ✅ Fetch Name, Mobile, Address & Selfie
    @Query("""
        SELECT new com.example.dto.FinalDriverProfileDTO(
            d.fullName,
            d.mobileNumber,
            CONCAT(
                COALESCE(d.village, ''), ', ',
                COALESCE(d.tehsil, ''), ', ',
                COALESCE(d.district, ''), ', ',
                COALESCE(d.state, ''), ' - ',
                COALESCE(d.pincode, '')
            ),
            doc.driverSelfie
        )
        FROM DriverDetails d
        JOIN DriverDocuments doc
            ON d.driverRegistrationId = doc.driverRegistrationId
        WHERE d.driverRegistrationId = :regId
    """)
    FinalDriverProfileDTO getFullDriverProfile(Long regId);

    // ✅ find driver by GDC registration number
    Optional<DriverFinalSubmission>
    findByGdcRegistrationNumber(String gdcRegistrationNumber);
    
    
    @Query(value = """
            SELECT d.mobile_number
            FROM yfs_driver_details d
            JOIN yfs_driver_final_submission f
              ON d.driver_registration_id = f.driver_registration_id
            WHERE f.gdc_registration_number = :gdc
            LIMIT 1
        """, nativeQuery = true)
        String findDriverMobileByGdc(@Param("gdc") String gdc);
    
    
    @Query("""
    	    SELECT d
    	    FROM DriverFinalSubmission d
    	    WHERE d.completionStatus = 'COMPLETED'
    	      AND d.termsStatus = 'ACCEPT'
    	      AND EXISTS (
    	          SELECT 1 FROM Payment p
    	          WHERE p.gdcNumber = d.gdcRegistrationNumber
    	            AND p.status = 'PAID'
    	            AND p.purpose = 'DRIVER_REGISTRATION'
    	      )
    	      AND NOT EXISTS (
    	          SELECT 1 FROM DriverAssignment a
    	          WHERE a.assignedDriverRegistrationId = d.driverRegistrationId
    	      )
    	""")
    	List<DriverFinalSubmission> findAvailableDriversForAssignment();


}
