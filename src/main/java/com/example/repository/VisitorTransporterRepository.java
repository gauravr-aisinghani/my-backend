package com.example.repository;

import com.example.entity.VisitorTransporterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitorTransporterRepository
        extends JpaRepository<VisitorTransporterEntity, Long> {

    List<VisitorTransporterEntity> findByStatus(String status);
}
