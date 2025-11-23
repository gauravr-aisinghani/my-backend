package com.example.service;

import com.example.dto.VisitorDriverDTO;
import com.example.entity.VisitorDriver;
import com.example.repository.VisitorDriverRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorDriverServiceImpl implements VisitorDriverService {

    private final VisitorDriverRepository repository;

    public VisitorDriverServiceImpl(VisitorDriverRepository repository) {
        this.repository = repository;
    }

    private VisitorDriverDTO toDTO(VisitorDriver e) {
        VisitorDriverDTO dto = new VisitorDriverDTO();

        dto.setVisitorDriverId(e.getVisitorDriverId());
        dto.setDriverName(e.getDriverName());
        dto.setLocation(e.getLocation());
        dto.setMobileNo(e.getMobileNo());
        dto.setGrade(e.getGrade());
        dto.setOtherMobile(e.getOtherMobile());
        dto.setBirthPlace(e.getBirthPlace());
        dto.setRelativeName(e.getRelativeName());
        dto.setRelativeMobile(e.getRelativeMobile());
        dto.setGaadiDrivenInPast(e.getGaadiDrivenInPast());
        dto.setUnderload(e.getUnderload());
        dto.setOverload(e.getOverload());
        dto.setPreferedLocation(e.getPreferedLocation());
        dto.setPreferedMonthlySalary(e.getPreferedMonthlySalary());
        dto.setRegularTiming(e.getRegularTiming());
        dto.setOccasional(e.getOccasional());
        dto.setPermanent(e.getPermanent());
        dto.setAnyIssue(e.getAnyIssue());
        dto.setNotes(e.getNotes());
        dto.setPreferredVehicle(e.getPreferredVehicle()); // ⭐ NEW
        dto.setCreatedAt(e.getCreatedAt());
        dto.setUpdatedAt(e.getUpdatedAt());

        return dto;
    }

    private VisitorDriver toEntity(VisitorDriverDTO d) {
        VisitorDriver e = new VisitorDriver();

        e.setVisitorDriverId(d.getVisitorDriverId());
        e.setDriverName(d.getDriverName());
        e.setLocation(d.getLocation());
        e.setMobileNo(d.getMobileNo());
        e.setGrade(d.getGrade());
        e.setOtherMobile(d.getOtherMobile());
        e.setBirthPlace(d.getBirthPlace());
        e.setRelativeName(d.getRelativeName());
        e.setRelativeMobile(d.getRelativeMobile());
        e.setGaadiDrivenInPast(d.getGaadiDrivenInPast());
        e.setUnderload(d.getUnderload());
        e.setOverload(d.getOverload());
        e.setPreferedLocation(d.getPreferedLocation());
        e.setPreferedMonthlySalary(d.getPreferedMonthlySalary());
        e.setRegularTiming(d.getRegularTiming());
        e.setOccasional(d.getOccasional());
        e.setPermanent(d.getPermanent());
        e.setAnyIssue(d.getAnyIssue());
        e.setNotes(d.getNotes());
        e.setPreferredVehicle(d.getPreferredVehicle()); // ⭐ NEW

        return e;
    }

    @Override
    public VisitorDriverDTO saveVisitorDriver(VisitorDriverDTO dto) {
        VisitorDriver entity = toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return toDTO(repository.save(entity));
    }

    @Override
    public VisitorDriverDTO getVisitorDriver(Long id) {
        return repository.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<VisitorDriverDTO> getAllVisitorDrivers() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public VisitorDriverDTO updateVisitorDriver(Long id, VisitorDriverDTO dto) {
        VisitorDriver existing = repository.findById(id).orElse(null);
        if (existing == null) return null;

        VisitorDriver updated = toEntity(dto);
        updated.setVisitorDriverId(id);
        updated.setCreatedAt(existing.getCreatedAt());
        updated.setUpdatedAt(LocalDateTime.now());

        return toDTO(repository.save(updated));
    }

    @Override
    public void deleteVisitorDriver(Long id) {
        repository.deleteById(id);
    }
}
