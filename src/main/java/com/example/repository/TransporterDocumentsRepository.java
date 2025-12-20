package com.example.repository;

import com.example.entity.TransporterDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransporterDocumentsRepository
        extends JpaRepository<TransporterDocuments, Long> {

    TransporterDocuments findByTransporterRegistrationId(String transporterRegistrationId);
}
