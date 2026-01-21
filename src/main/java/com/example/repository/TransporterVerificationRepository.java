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
            "tv.verification_id AS verificationId, " +
            "tfs.completion_status AS completionStatus " +  // ✅ include completion status
            "FROM yfs_transporter_verification tv " +
            "JOIN yfs_transporter_details td " +
            "ON tv.transporter_registration_id = td.transporter_registration_id " +
            "LEFT JOIN yfs_transporter_final_submission tfs " +
            "ON tv.transporter_registration_id = tfs.transporter_registration_id " +
            "AND tv.verification_id = tfs.verification_id " +
            "WHERE tv.final_status = 'APPROVED' " +
            "AND (tfs.completion_status IS NULL OR tfs.completion_status <> 'COMPLETED')", 
            nativeQuery = true)
    List<Map<String, Object>> findApprovedTransporters();
}
