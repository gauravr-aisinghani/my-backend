package com.example.repository;

import com.example.entity.TransporterDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransporterDocumentsRepository
        extends JpaRepository<TransporterDocuments, Long> {

    TransporterDocuments findByTransporterRegistrationId(String transporterRegistrationId);

    @Query("select distinct d.transporterRegistrationId from TransporterDocuments d")
    List<String> findDistinctRegistrationIds();
}
