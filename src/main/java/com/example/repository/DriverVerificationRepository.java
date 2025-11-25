package com.example.repository;

import com.example.entity.DriverVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DriverVerificationRepository extends JpaRepository<DriverVerification, Long> {

    // 🔥 Your existing method (kept as is)
    Optional<DriverVerification> findByDriverRegistrationId(Long driverRegistrationId);

    // 🔥 NEW JOIN QUERY TO FETCH APPROVED DRIVERS
    @Query(
        value = "SELECT dv.verification_id AS verificationId, " +
                "dd.full_name AS fullName, " +
                "dd.mobile_number AS mobileNumber, " +
                "dd.village AS village, " +
                "dv.updated_at AS verifiedAt " +
                "FROM yfs_driver_verification dv " +
                "JOIN yfs_driver_details dd ON dv.driver_registration_id = dd.driver_registration_id " +
                "WHERE dv.final_status = 'APPROVED'",
        nativeQuery = true
    )
    List<Map<String, Object>> findApprovedDriversJoined();
}
