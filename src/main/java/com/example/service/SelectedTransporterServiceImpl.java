package com.example.service;

import com.example.dto.SelectedTransporterDTO;
import com.example.entity.SelectedTransporter;
import com.example.repository.SelectedTransporterRepository;
import com.example.service.SelectedTransporterService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SelectedTransporterServiceImpl implements SelectedTransporterService {

    private final SelectedTransporterRepository repository;

    public SelectedTransporterServiceImpl(SelectedTransporterRepository repository) {
        this.repository = repository;
    }

    @Override
    public SelectedTransporter saveSelectedTransporter(SelectedTransporterDTO dto) {

        SelectedTransporter entity = new SelectedTransporter();

        entity.setVisitorTransporterId(dto.getVisitorTransporterId());
        entity.setCompanyName(dto.getCompanyName());
        entity.setOwnerName(dto.getOwnerName());
        entity.setOwnerMobileNo(dto.getOwnerMobileNo());
        entity.setAuthorisedName(dto.getAuthorisedName());
        entity.setAuthorisedMobileNo(dto.getAuthorisedMobileNo());
        entity.setGaadiType(dto.getGaadiType());
        entity.setLoadingPlace(dto.getLoadingPlace());
        entity.setUnloadPlace(dto.getUnloadPlace());
        entity.setMonthlySalary(dto.getMonthlySalary());
        entity.setOtherBenefit(dto.getOtherBenefit());
        entity.setNeedTiming(dto.getNeedTiming());
        entity.setNotes(dto.getNotes());
        entity.setQtyFinalDriver(dto.getQtyFinalDriver());
        entity.setFinalApplication(dto.getFinalApplication());
        entity.setFinalDate(dto.getFinalDate());
        entity.setApprovalStatus(dto.getApprovalStatus());

        return repository.save(entity);
    }
    
    
    @Override
    public List<SelectedTransporter> getAllSelectedTransporters() {
        return repository.findAll();
    }

    @Override
    public SelectedTransporter getSelectedTransporterById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Selected transporter not found"));
    }

}
