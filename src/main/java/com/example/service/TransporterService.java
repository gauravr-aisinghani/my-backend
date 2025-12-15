package com.example.service;

import com.example.dto.VisitorTransporterDTO;
import com.example.entity.SelectedTransporterEntity;
import com.example.entity.VisitorTransporterEntity;

import java.util.List;

public interface TransporterService {

    // Save a visitor enquiry
    VisitorTransporterEntity saveVisitor(VisitorTransporterDTO dto);

    // Get all visitors with status VISITED
    List<VisitorTransporterEntity> getAllVisitors();

    // Move a visitor to final transporters
    void addToFinal(Long visitorTransporterId);

    // Get all finalized transporters
    List<SelectedTransporterEntity> getAllFinalTransporters();
}
