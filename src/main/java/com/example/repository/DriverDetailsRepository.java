package com.example.repository;

import com.example.dto.LedgerSummaryDto;
import com.example.entity.DriverDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DriverDetailsRepository extends JpaRepository<DriverDetails, Long> {

    Optional<DriverDetails> findByMobileNumber(String mobileNumber);

    Optional<DriverDetails> findByAadharNo(String aadharNo);

    boolean existsByMobileNumber(String mobileNumber);

    boolean existsByAadharNo(String aadharNo);

    Optional<DriverDetails> findByDriverRegistrationId(Long driverRegistrationId);

    List<DriverDetails> findByFullNameContainingIgnoreCase(String fullName);

 // 👇 PURANE METHODS AS-IS REHENGE

    @Query(
        value = """
        SELECT 
            d.driver_registration_id  AS id,
            d.full_name               AS name,
            f.gdc_registration_number AS code,
            w.balance                 AS balance,
            w.status                  AS status
        FROM driver_details d
        JOIN driver_final_submission f
            ON f.driver_registration_id = d.driver_registration_id
        JOIN wallet w
            ON w.gdc_number = f.gdc_registration_number
           AND w.user_type = :userType
        WHERE (:search IS NULL
               OR LOWER(d.full_name) LIKE LOWER(CONCAT('%', :search, '%')))
        """,
        nativeQuery = true
    )
    List<com.example.dto.LedgerSummaryView> fetchDriverLedgerSummaryNative(
            @Param("search") String search,
            @Param("userType") String userType
    );

}
