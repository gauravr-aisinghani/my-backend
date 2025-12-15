package com.example.service;

import com.example.dto.VisitorTransporterDTO;
import com.example.entity.SelectedTransporterEntity;
import com.example.entity.VisitorTransporterEntity;
import com.example.repository.SelectedTransporterRepository;
import com.example.repository.VisitorTransporterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TransporterServiceImpl implements TransporterService {

    private final VisitorTransporterRepository visitorRepo;
    private final SelectedTransporterRepository selectedRepo;

    public TransporterServiceImpl(VisitorTransporterRepository visitorRepo,
                                  SelectedTransporterRepository selectedRepo) {
        this.visitorRepo = visitorRepo;
        this.selectedRepo = selectedRepo;
    }

    @Override
    public VisitorTransporterEntity saveVisitor(VisitorTransporterDTO dto) {
        VisitorTransporterEntity entity = new VisitorTransporterEntity();

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
        entity.setStatus("VISITED");

        return visitorRepo.save(entity);
    }

    @Override
    public List<VisitorTransporterEntity> getAllVisitors() {
        return visitorRepo.findByStatus("VISITED");
    }

    @Transactional
    @Override
    public void addToFinal(Long visitorTransporterId) {
        VisitorTransporterEntity visitor =
                visitorRepo.findById(visitorTransporterId)
                        .orElseThrow(() -> new RuntimeException("Visitor not found"));

        SelectedTransporterEntity selected = new SelectedTransporterEntity();

        selected.setVisitorTransporterId(visitor.getVisitorTransporterId());
        selected.setFinalDate(LocalDate.now());
        selected.setCompanyName(visitor.getCompanyName());
        selected.setOwnerName(visitor.getOwnerName());
        selected.setOwnerMobileNo(visitor.getOwnerMobileNo());
        selected.setAuthorisedName(visitor.getAuthorisedName());
        selected.setAuthorisedMobileNo(visitor.getAuthorisedMobileNo());
        selected.setQtyFinalDriver(visitor.getNeedDriver());
        selected.setGaadiType(visitor.getGaadiType());
        selected.setFinalApplication(visitor.getRunningApplication());
        selected.setLoadingPlace(visitor.getLoadingPlace());
        selected.setUnloadPlace(visitor.getUnloadPlace());
        selected.setMonthlySalary(visitor.getMonthlySalary());
        selected.setOtherBenefit(visitor.getOtherBenefit());
        selected.setNeedTiming(visitor.getNeedTiming());
        selected.setApprovalStatus("APPROVED");

        selectedRepo.save(selected);

        visitor.setStatus("FINALIZED");
        visitorRepo.save(visitor);
    }

    @Override
    public List<SelectedTransporterEntity> getAllFinalTransporters() {
        return selectedRepo.findAll();
    }
}
