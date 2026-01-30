package com.example.repository;
import com.example.dto.LedgerSummaryDto;
import com.example.entity.PaymentType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.DriverDetails;

import java.util.List;
import java.util.Optional;

public interface DriverDetailsRepository extends JpaRepository<DriverDetails, Long> {

    Optional<DriverDetails> findByMobileNumber(String mobileNumber);
    Optional<DriverDetails> findByAadharNo(String aadharNo);
    boolean existsByMobileNumber(String mobileNumber);
    boolean existsByAadharNo(String aadharNo);


    // 🔥 New: fetch driver details using driver_registration_id
    Optional<DriverDetails> findByDriverRegistrationId(Long driverRegistrationId);
    
    List<DriverDetails>
    findByFullNameContainingIgnoreCase(String fullName);
    
    @Query("""
    		SELECT new com.example.dto.LedgerSummaryDto(
    		    CAST(d.driverRegistrationId AS string),
    		    d.fullName,
    		    f.gdcRegistrationNumber,
    		    w.balance,
    		    w.status.name()
    		)
    		FROM DriverDetails d
    		JOIN DriverFinalSubmission f
    		  ON f.driverRegistrationId = d.driverRegistrationId
    		JOIN Wallet w
    		  ON w.gdcNumber = f.gdcRegistrationNumber
    		 AND w.userType = com.example.entity.PaymentType.DRIVER
    		WHERE (:search IS NULL
    		    OR LOWER(d.fullName) LIKE LOWER(CONCAT('%', :search, '%')))
    		""")
    		List<LedgerSummaryDto> fetchDriverLedgerSummary(
    		        @Param("search") String search
    		);


}
