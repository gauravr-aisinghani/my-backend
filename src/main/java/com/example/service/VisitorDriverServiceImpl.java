package com.example.service;

import com.example.dto.VisitorDriverDTO;
import com.example.entity.VisitorDriver;
import com.example.repository.VisitorDriverRepository;
import com.example.service.VisitorDriverService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorDriverServiceImpl implements VisitorDriverService {

    private final VisitorDriverRepository repository;

    public VisitorDriverServiceImpl(VisitorDriverRepository repository) {
        this.repository = repository;
    }

    private VisitorDriverDTO convertToDTO(VisitorDriver entity) {
        VisitorDriverDTO dto = new VisitorDriverDTO();

        dto.setVisitorDriverId(entity.getVisitorDriverId());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setDriverName(entity.getDriverName());
        dto.setLocation(entity.getLocation());
        dto.setMobileNo(entity.getMobileNo());
        dto.setOtherMobile(entity.getOtherMobile());
        dto.setRelativeName(entity.getRelativeName());
        dto.setRelativeMobile(entity.getRelativeMobile());

        dto.setGaadi(entity.getGaadi());
        dto.setUnderload(entity.getUnderload());
        dto.setOverload(entity.getOverload());
        dto.setPreferedLocation(entity.getPreferedLocation());
        dto.setPreferedMonthlySalary(entity.getPreferedMonthlySalary());
        dto.setRegularTiming(entity.getRegularTiming());
        dto.setLeaveTime(entity.getLeaveTime());
        dto.setAnyIssue(entity.getAnyIssue());
        dto.setNotes(entity.getNotes());

        dto.setBirthPlace(entity.getBirthPlace());
        dto.setGrade(entity.getGrade());
        dto.setVehicle(entity.getVehicle());
        dto.setAssignFor(entity.getAssignFor());

        return dto;
    }

    private VisitorDriver convertToEntity(VisitorDriverDTO dto) {
        VisitorDriver entity = new VisitorDriver();

        entity.setVisitorDriverId(dto.getVisitorDriverId());
        entity.setDate(dto.getDate());
        entity.setTime(dto.getTime());
        entity.setDriverName(dto.getDriverName());
        entity.setLocation(dto.getLocation());
        entity.setMobileNo(dto.getMobileNo());
        entity.setOtherMobile(dto.getOtherMobile());
        entity.setRelativeName(dto.getRelativeName());
        entity.setRelativeMobile(dto.getRelativeMobile());

        entity.setGaadi(dto.getGaadi());
        entity.setUnderload(dto.getUnderload());
        entity.setOverload(dto.getOverload());
        entity.setPreferedLocation(dto.getPreferedLocation());
        entity.setPreferedMonthlySalary(dto.getPreferedMonthlySalary());
        entity.setRegularTiming(dto.getRegularTiming());
        entity.setLeaveTime(dto.getLeaveTime());
        entity.setAnyIssue(dto.getAnyIssue());
        entity.setNotes(dto.getNotes());

        entity.setBirthPlace(dto.getBirthPlace());
        entity.setGrade(dto.getGrade());
        entity.setVehicle(dto.getVehicle());
        entity.setAssignFor(dto.getAssignFor());

        return entity;
    }

    @Override
    public VisitorDriverDTO saveVisitorDriver(VisitorDriverDTO dto) {
        VisitorDriver saved = repository.save(convertToEntity(dto));
        return convertToDTO(saved);
    }

    @Override
    public VisitorDriverDTO getVisitorDriver(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public List<VisitorDriverDTO> getAllVisitorDrivers() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VisitorDriverDTO updateVisitorDriver(Long id, VisitorDriverDTO dto) {
        VisitorDriver existing = repository.findById(id).orElse(null);

        if (existing == null) return null;

        dto.setVisitorDriverId(id);
        VisitorDriver updated = convertToEntity(dto);
        updated.setCreatedAt(existing.getCreatedAt());

        return convertToDTO(repository.save(updated));
    }

    @Override
    public void deleteVisitorDriver(Long id) {
        repository.deleteById(id);
    }
}
