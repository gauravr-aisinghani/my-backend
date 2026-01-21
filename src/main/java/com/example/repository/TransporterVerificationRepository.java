package com.example.repository;

import com.example.entity.TransporterVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface TransporterVerificationRepository extends JpaRepository<TransporterVerification, Long> {

    Optional<TransporterVerification> findByTransporterRegistrationId(String transporterRegistrationId);

    @Query(value = "SELECT tv.transporter_registration_id AS transporterRegistrationId, " +
            "td.transport_company_name AS companyName, " +
            "td.owner_name AS ownerName, " +
            "td.owner_mobile_number AS ownerMobile, " +
            "tv.updated_at AS verifiedAt, " +
            "tv.verification_id AS verificationId " +  // ✅ add this line
            "FROM yfs_transporter_verification tv " +
            "JOIN yfs_transporter_details td " +
            "ON tv.transporter_registration_id = td.transporter_registration_id " +
            "WHERE tv.final_status = 'APPROVED'", nativeQuery = true)
    List<Map<String, Object>> findApprovedTransporters();

}
