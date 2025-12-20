package com.example.service;

import com.example.dto.TransporterVehicleDTO;
import com.example.entity.TransporterVehicle;

public interface TransporterVehicleService {
    TransporterVehicle saveVehicle(TransporterVehicleDTO dto);
}
