package com.example.service;

import com.example.dto.VisitorTransporterDTO;
import com.example.entity.VisitorTransporter;
import com.example.repository.VisitorTransporterRepository;
import com.example.service.VisitorTransporterService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitorTransporterServiceImpl implements VisitorTransporterService {

    private final VisitorTransporterRepository repository;

    public VisitorTransporterServiceImpl(VisitorTransporterRepository repository) {
        this.repository = repository;
    }

    @Override
    public VisitorTransporterDTO save(VisitorTransporterDTO dto) {
        VisitorTransporter entity = mapToEntity(dto);
        return mapToDTO(repository.save(entity));
    }

    @Override
    public List<VisitorTransporterDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VisitorTransporterDTO getById(Long id) {
        VisitorTransporter entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));
        return mapToDTO(entity);
    }

    @Override
    public VisitorTransporterDTO update(Long id, VisitorTransporterDTO dto) {
        VisitorTransporter entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        entity.setCompanyName(dto.getCompanyName());
        entity.setOwnerName(dto.getOwnerName());
        entity.setOwnerMobileNo(dto.getOwnerMobileNo());
        entity.setAuthorisedName(dto.getAuthorisedName());
        entity.setAuthorisedMobileNo(dto.getAuthorisedMobileNo());
        entity.setNeedDriver(dto.getNeedDriver());
        entity.setGaadiType(dto.getGaadiType());
        entity.setRunningApplication(dto.getRunningApplication());
        entity.setLoadingPlace(dto.getLoadingPlace());
        entity.setUnloadPlace(dto.getUnloadPlace());
        entity.setMonthlySalary(dto.getMonthlySalary());
        entity.setOtherBenefit(dto.getOtherBenefit());
        entity.setNeedTiming(dto.getNeedTiming());
        entity.setNotes(dto.getNotes());
        entity.setStatus(dto.getStatus());

        return mapToDTO(repository.save(entity));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private VisitorTransporter mapToEntity(VisitorTransporterDTO dto) {
        VisitorTransporter entity = new VisitorTransporter();
        entity.setCompanyName(dto.getCompanyName());
        entity.setOwnerName(dto.getOwnerName());
        entity.setOwnerMobileNo(dto.getOwnerMobileNo());
        entity.setAuthorisedName(dto.getAuthorisedName());
        entity.setAuthorisedMobileNo(dto.getAuthorisedMobileNo());
        entity.setNeedDriver(dto.getNeedDriver());
        entity.setGaadiType(dto.getGaadiType());
        entity.setRunningApplication(dto.getRunningApplication());
        entity.setLoadingPlace(dto.getLoadingPlace());
        entity.setUnloadPlace(dto.getUnloadPlace());
        entity.setMonthlySalary(dto.getMonthlySalary());
        entity.setOtherBenefit(dto.getOtherBenefit());
        entity.setNeedTiming(dto.getNeedTiming());
        entity.setNotes(dto.getNotes());
        entity.setStatus(dto.getStatus());
        return entity;
    }

    private VisitorTransporterDTO mapToDTO(VisitorTransporter entity) {
        VisitorTransporterDTO dto = new VisitorTransporterDTO();
        dto.setVisitorTransporterId(entity.getVisitorTransporterId());
        dto.setCompanyName(entity.getCompanyName());
        dto.setOwnerName(entity.getOwnerName());
        dto.setOwnerMobileNo(entity.getOwnerMobileNo());
        dto.setAuthorisedName(entity.getAuthorisedName());
        dto.setAuthorisedMobileNo(entity.getAuthorisedMobileNo());
        dto.setNeedDriver(entity.getNeedDriver());
        dto.setGaadiType(entity.getGaadiType());
        dto.setRunningApplication(entity.getRunningApplication());
        dto.setLoadingPlace(entity.getLoadingPlace());
        dto.setUnloadPlace(entity.getUnloadPlace());
        dto.setMonthlySalary(entity.getMonthlySalary());
        dto.setOtherBenefit(entity.getOtherBenefit());
        dto.setNeedTiming(entity.getNeedTiming());
        dto.setNotes(entity.getNotes());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}
