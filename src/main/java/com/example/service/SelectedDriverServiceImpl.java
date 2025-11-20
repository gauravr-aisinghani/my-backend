package com.example.service;

import com.example.dto.SelectedDriverDTO;
import com.example.dto.VisitorDriverDTO;
import com.example.entity.SelectedDriver;
import com.example.repository.SelectedDriverRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional  // ✅ Ensures all repository operations are committed
public class SelectedDriverServiceImpl implements SelectedDriverService {

    private final SelectedDriverRepository selectedDriverRepository;

    public SelectedDriverServiceImpl(SelectedDriverRepository selectedDriverRepository) {
        this.selectedDriverRepository = selectedDriverRepository;
    }

    @Override
    public SelectedDriverDTO addSelectedDriver(VisitorDriverDTO visitor) {
        SelectedDriver entity = new SelectedDriver();
        entity.setVisitorDriverId(visitor.getVisitorDriverId());
        entity.setDate(visitor.getDate());
        entity.setTime(visitor.getTime());
        entity.setDriverName(visitor.getDriverName());
        entity.setBirthPlace(visitor.getBirthPlace());
        entity.setMobileNo(visitor.getMobileNo());
        entity.setGrade(visitor.getGrade());
        entity.setVehicle(visitor.getVehicle() != null ? visitor.getVehicle() : visitor.getGaadi());
        entity.setAssignFor(visitor.getAssignFor());

        SelectedDriver saved = selectedDriverRepository.save(entity);

        SelectedDriverDTO dto = new SelectedDriverDTO();
        dto.setSelectedDriverId(saved.getSelectedDriverId());
        dto.setVisitorDriverId(saved.getVisitorDriverId());
        dto.setDate(saved.getDate());
        dto.setTime(saved.getTime());
        dto.setDriverName(saved.getDriverName());
        dto.setBirthPlace(saved.getBirthPlace());
        dto.setMobileNo(saved.getMobileNo());
        dto.setGrade(saved.getGrade());
        dto.setVehicle(saved.getVehicle());
        dto.setAssignFor(saved.getAssignFor());

        return dto;
    }

    @Override
    public List<SelectedDriverDTO> getAllSelectedDrivers() {
        return selectedDriverRepository.findAll().stream().map(entity -> {
            SelectedDriverDTO dto = new SelectedDriverDTO();
            dto.setSelectedDriverId(entity.getSelectedDriverId());
            dto.setVisitorDriverId(entity.getVisitorDriverId());
            dto.setDate(entity.getDate());
            dto.setTime(entity.getTime());
            dto.setDriverName(entity.getDriverName());
            dto.setBirthPlace(entity.getBirthPlace());
            dto.setMobileNo(entity.getMobileNo());
            dto.setGrade(entity.getGrade());
            dto.setVehicle(entity.getVehicle());
            dto.setAssignFor(entity.getAssignFor());
            return dto;
        }).collect(Collectors.toList());
    }
}
