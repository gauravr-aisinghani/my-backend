package com.example.repository;

import com.example.entity.TransporterDriverRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Transporter Driver Requests
 */
@Repository
public interface TransporterDriverRequestRepository extends JpaRepository<TransporterDriverRequest, Long> {

}
