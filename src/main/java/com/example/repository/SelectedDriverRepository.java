package com.example.repository;

import com.example.entity.SelectedDriver;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedDriverRepository extends JpaRepository<SelectedDriver, Long> {

}
