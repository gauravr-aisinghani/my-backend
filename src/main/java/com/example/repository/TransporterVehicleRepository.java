package com.example.repository;

import com.example.entity.TransporterVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransporterVehicleRepository
        extends JpaRepository<TransporterVehicle, String> {
}
