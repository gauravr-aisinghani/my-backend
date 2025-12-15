package com.example.service;

import com.example.dto.VisitorTransporterDTO;
import com.example.entity.SelectedTransporterEntity;
import com.example.entity.VisitorTransporterEntity;

import java.util.List;

public interface TransporterService {

    VisitorTransporterEntity saveVisitor(VisitorTransporterDTO dto);

    List<VisitorTransporterEntity> getAllVisitors();

    void addToFinal(Long visitorTransporterId);

    List<SelectedTransporterEntity> getAllFinalTransporters();
}
