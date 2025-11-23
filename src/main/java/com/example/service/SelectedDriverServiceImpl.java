package com.example.service;

import com.example.dto.SelectedDriverDTO;
import com.example.entity.SelectedDriver;
import com.example.repository.SelectedDriverRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectedDriverServiceImpl implements SelectedDriverService {

    private final SelectedDriverRepository repo;

    public SelectedDriverServiceImpl(SelectedDriverRepository repo) {
        this.repo = repo;
    }

    private SelectedDriverDTO toDTO(SelectedDriver e) {
        SelectedDriverDTO d = new SelectedDriverDTO();

        d.setSelectedDriverId(e.getSelectedDriverId());
        d.setVisitorDriverId(e.getVisitorDriverId());
        d.setDriverName(e.getDriverName());
        d.setBirthPlace(e.getBirthPlace());
        d.setMobileNo(e.getMobileNo());
        d.setGrade(e.getGrade());
        d.setPreferredVehicle(e.getPreferredVehicle());
        d.setApprovedForAssign(e.getApprovedForAssign());
        d.setAssignedStatus(e.getAssignedStatus());
        d.setSelectionNotes(e.getSelectionNotes());
        d.setSelectedDate(e.getSelectedDate());
        d.setCreatedAt(e.getCreatedAt());
        d.setUpdatedAt(e.getUpdatedAt());

        return d;
    }

    private SelectedDriver toEntity(SelectedDriverDTO d) {
        SelectedDriver e = new SelectedDriver();

        e.setSelectedDriverId(d.getSelectedDriverId());
        e.setVisitorDriverId(d.getVisitorDriverId());
        e.setDriverName(d.getDriverName());
        e.setBirthPlace(d.getBirthPlace());
        e.setMobileNo(d.getMobileNo());
        e.setGrade(d.getGrade());
        e.setPreferredVehicle(d.getPreferredVehicle());
        e.setApprovedForAssign(d.getApprovedForAssign());
        e.setAssignedStatus(d.getAssignedStatus());
        e.setSelectionNotes(d.getSelectionNotes());
        e.setSelectedDate(d.getSelectedDate());

        return e;
    }

    @Override
    public SelectedDriverDTO saveSelectedDriver(SelectedDriverDTO dto) {
        SelectedDriver entity = toEntity(dto);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return toDTO(repo.save(entity));
    }

    @Override
    public SelectedDriverDTO getSelectedDriver(Long id) {
        return repo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public List<SelectedDriverDTO> getAllSelectedDrivers() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public SelectedDriverDTO updateSelectedDriver(Long id, SelectedDriverDTO dto) {
        SelectedDriver existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        SelectedDriver updated = toEntity(dto);
        updated.setSelectedDriverId(id);
        updated.setCreatedAt(existing.getCreatedAt());
        updated.setUpdatedAt(LocalDateTime.now());

        return toDTO(repo.save(updated));
    }

    @Override
    public void deleteSelectedDriver(Long id) {
        repo.deleteById(id);
    }
}
