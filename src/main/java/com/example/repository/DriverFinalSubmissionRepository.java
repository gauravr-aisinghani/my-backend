package com.example.repository;

import com.example.dto.FinalDriverProfileDTO;
import com.example.entity.DriverFinalSubmission;
import com.example.entity.DriverDetails;
import com.example.entity.DriverDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
}
