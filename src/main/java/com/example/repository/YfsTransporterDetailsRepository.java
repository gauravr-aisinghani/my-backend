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

 // 👇 PURANE METHODS AS-IS REHENGE

    @Query(
        value = """
        SELECT 
            t.transporter_registration_id AS id,
            t.transport_company_name      AS name,
            f.gdc_registration_number     AS code,
            w.balance                     AS balance,
            w.status                      AS status
        FROM yfs_transporter_details t
        JOIN transporter_final_submission f
            ON f.transporter_registration_id = t.transporter_registration_id
        JOIN wallet w
            ON w.gdc_number = f.gdc_registration_number
           AND w.user_type = :userType
        WHERE (:search IS NULL
               OR LOWER(t.transport_company_name)
                  LIKE LOWER(CONCAT('%', :search, '%')))
        """,
        nativeQuery = true
    )
    List<com.example.dto.LedgerSummaryView> fetchTransporterLedgerSummaryNative(
            @Param("search") String search,
            @Param("userType") String userType
    );

}
