package com.example.repository;

import com.example.dto.FinalTransporterProfileDTO;
import com.example.entity.TransporterFinalSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TransporterFinalSubmissionRepository
        extends JpaRepository<TransporterFinalSubmission, Long> {

    boolean existsByVerificationId(Long verificationId);

    Optional<TransporterFinalSubmission>
    findByGdcRegistrationNumber(String gdcRegistrationNumber);

    @Query("""
        SELECT new com.example.dto.FinalTransporterProfileDTO(
            t.companyName,
            t.mobileNumber,
            CONCAT(
                COALESCE(t.city,''), ', ',
                COALESCE(t.state,''), ' - ',
                COALESCE(t.pincode,'')
            ),
            d.selfieUrl
        )
        FROM TransporterDetails t
        JOIN TransporterDocuments d
          ON t.transporterRegistrationId = d.transporterRegistrationId
        WHERE t.transporterRegistrationId = :regId
    """)
    FinalTransporterProfileDTO getFullTransporterProfile(String regId);
}
