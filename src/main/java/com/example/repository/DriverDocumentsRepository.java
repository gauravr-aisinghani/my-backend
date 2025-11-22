package com.example.repository;

import com.example.entity.DriverDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverDocumentsRepository extends JpaRepository<DriverDocuments, Long> {

    DriverDocuments findByDriverRegistrationId(Long driverRegistrationId);
}
