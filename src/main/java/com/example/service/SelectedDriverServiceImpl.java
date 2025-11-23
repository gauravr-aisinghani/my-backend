package com.example.service;

import com.example.dto.SelectedDriverDTO;
import com.example.entity.SelectedDriver;
import com.example.repository.SelectedDriverRepository;
import com.example.repository.VisitorDriverRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SelectedDriverServiceImpl implements SelectedDriverService {

    private final SelectedDriverRepository repo;
    private final VisitorDriverRepository visitorRepo;

    public SelectedDriverServiceImpl(SelectedDriverRepository repo,
                                     VisitorDriverRepository visitorRepo) {
        this.repo = repo;
        this.visitorRepo = visitorRepo;
    }

    // ---------------- DTO MAPPERS ----------------

    private SelectedDriverDTO toDTO(SelectedDriver e) {
        SelectedDriverDTO d = new SelectedDriverDTO();

        d.setSelectedDriverId(e.getSelectedDriverId());
        d.setVisitorDriverId(e.getVisitorDriverId());
        d.setDriverName(e.getDriverName());
        d.setBirthPlace(e.getBirthPlace());
        d.setMobileNo(e.getMobileNo());
        d.setGrade(e.getGrade());
        d.setPreferredVehicle(e.getPreferredVehicle());

        d.setDate(e.getDate());   // <-- FIXED
        d.setTime(e.getTime());   // <-- FIXED

        d.setApproveFor(e.getApproveFor());
        d.setAssign(e.getAssign());
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

        e.setDate(d.getDate());   // <-- IMPORTANT
        e.setTime(d.getTime());   // <-- IMPORTANT

        e.setApproveFor(d.getApproveFor());
        e.setAssign(d.getAssign());
        e.setApprovedForAssign(d.getApprovedForAssign());
        e.setAssignedStatus(d.getAssignedStatus());
        e.setSelectionNotes(d.getSelectionNotes());
        e.setSelectedDate(d.getSelectedDate());

        return e;
    }

    // ---------------- CRUD METHODS ----------------

    @Override
    public SelectedDriverDTO saveSelectedDriver(SelectedDriverDTO dto) {

        SelectedDriver entity = toEntity(dto);

        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        SelectedDriver saved = repo.save(entity);

        if (saved.getVisitorDriverId() != null) {
            if (visitorRepo.existsById(saved.getVisitorDriverId())) {
                visitorRepo.deleteById(saved.getVisitorDriverId());
            }
        }

        return toDTO(saved);
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
