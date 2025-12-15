package com.example.repository;

import com.example.entity.SelectedTransporterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectedTransporterRepository
        extends JpaRepository<SelectedTransporterEntity, Long> {
}
