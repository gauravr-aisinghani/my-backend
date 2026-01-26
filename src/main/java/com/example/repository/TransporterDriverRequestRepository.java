package com.example.repository;

import com.example.entity.TransporterDriverRequest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Transporter Driver Requests
 */
@Repository
public interface TransporterDriverRequestRepository
extends JpaRepository<TransporterDriverRequest,Long>{

List<TransporterDriverRequest> findByGdcNumberAndAdvancePaidFalse(String gdc);

List<TransporterDriverRequest> findByGdcNumberAndSettlementPaidFalse(String gdc);
}

