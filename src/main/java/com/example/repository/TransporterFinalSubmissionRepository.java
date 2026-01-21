package com.example.repository;

import com.example.entity.TransporterFinalSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TransporterFinalSubmissionRepository
        extends JpaRepository<TransporterFinalSubmission, Long> {

    boolean existsByVerificationId(Long verificationId);

    Optional<TransporterFinalSubmission>
    findByGdcRegistrationNumber(String gdcRegistrationNumber);

    boolean existsByTransporterRegistrationIdAndCompletionStatus(
            String transporterRegistrationId,
            String completionStatus
    );

    // ✅ HARD FIX: Collation + Param + No TRIM
    @Query(value = """
        SELECT 
            d.transport_company_name,
            d.owner_mobile_number,
            d.address,
            doc.transporter_selfie_live_location_url
        FROM yfs_transporter_details d
        LEFT JOIN yfs_transporter_documents doc
          ON d.transporter_registration_id = doc.transporter_registration_id
        WHERE d.transporter_registration_id = :regId
        COLLATE utf8mb4_0900_ai_ci
        LIMIT 1
        """, nativeQuery = true)
    Object[] getFullTransporterProfileRaw(@Param("regId") String regId);
}
