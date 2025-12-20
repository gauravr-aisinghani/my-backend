package com.example.service;

import java.util.List;

import com.example.dto.SelectedTransporterDTO;
import com.example.entity.SelectedTransporter;

public interface SelectedTransporterService {

    SelectedTransporter saveSelectedTransporter(SelectedTransporterDTO dto);

    List<SelectedTransporter> getAllSelectedTransporters();

    SelectedTransporter getSelectedTransporterById(Long id);
}