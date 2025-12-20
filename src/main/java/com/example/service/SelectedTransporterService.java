package com.example.service;

import com.example.dto.SelectedTransporterDTO;
import com.example.entity.SelectedTransporter;

public interface SelectedTransporterService {

    SelectedTransporter saveSelectedTransporter(SelectedTransporterDTO dto);
}
