package com.example.repository;

import com.example.entity.DriverDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverDocumentsRepository extends JpaRepository<DriverDocuments, Long> {

    DriverDocuments findByDriverRegistrationId(Long driverRegistrationId);

    // 🔥 New: get unique driver_registration_id list
    @Query("SELECT DISTINCT d.driverRegistrationId FROM DriverDocuments d")
    List<Long> findDistinctRegistrationIds();
}
