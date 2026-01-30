package com.example.repository;

import com.example.dto.LedgerSummaryDto;
import com.example.entity.PaymentType;
import com.example.entity.YfsTransporterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface YfsTransporterDetailsRepository
        extends JpaRepository<YfsTransporterDetails, String> {

    boolean existsByGstNumber(String gstNumber);

    Optional<YfsTransporterDetails> findByTransporterRegistrationId(String transporterRegistrationId);

    Optional<YfsTransporterDetails> findByOwnerMobileNumber(String ownerMobileNumber);

    List<YfsTransporterDetails> findByTransportCompanyNameContainingIgnoreCase(String name);

    @Query("""
        SELECT new com.example.dto.LedgerSummaryDto(
            t.transporterRegistrationId,
            t.transportCompanyName,
            f.gdcRegistrationNumber,
            w.balance,
            w.status
        )
        FROM YfsTransporterDetails t,
             TransporterFinalSubmission f,
             Wallet w
        WHERE f.transporterRegistrationId = t.transporterRegistrationId
          AND w.gdcNumber = f.gdcRegistrationNumber
          AND w.userType = :userType
          AND (:search IS NULL
               OR LOWER(t.transportCompanyName) LIKE LOWER(CONCAT('%', :search, '%')))
    """)
    List<LedgerSummaryDto> fetchTransporterLedgerSummary(
            @Param("search") String search,
            @Param("userType") PaymentType userType
    );
}
