package com.example.service;

import com.example.dto.DriverSarpanchDTO;
import com.example.entity.DriverSarpanchDetails;
import com.example.repository.DriverSarpanchRepository;
import com.example.service.DriverSarpanchService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverSarpanchServiceImpl implements DriverSarpanchService {

    private final DriverSarpanchRepository repo;

    public DriverSarpanchServiceImpl(DriverSarpanchRepository repo) {
        this.repo = repo;
    }

    @Override
    public DriverSarpanchDetails create(DriverSarpanchDTO dto) {
        DriverSarpanchDetails entity = mapToEntity(dto);
        return repo.save(entity);
    }

    @Override
    public DriverSarpanchDetails update(Long id, DriverSarpanchDTO dto) {
        DriverSarpanchDetails existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Sarpanch details not found"));

        DriverSarpanchDetails updated = mapToEntity(dto);
        updated.setSarpanchDetailsId(id);

        return repo.save(updated);
    }

    @Override
    public DriverSarpanchDetails getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Details not found"));
    }

    @Override
    public DriverSarpanchDetails getByDriverRegistrationId(Long driverRegistrationId) {
        return repo.findByDriverRegistrationId(driverRegistrationId)
                .orElseThrow(() -> new RuntimeException("No details for driver"));
    }

    @Override
    public List<DriverSarpanchDetails> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private DriverSarpanchDetails mapToEntity(DriverSarpanchDTO dto) {

        DriverSarpanchDetails d = new DriverSarpanchDetails();

        d.setDriverRegistrationId(dto.getDriverRegistrationId());
        d.setSarpanchName(dto.getSarpanchName());
        d.setSarpanchMobile(dto.getSarpanchMobile());
        d.setFamilyPerson1Name(dto.getFamilyPerson1Name());
        d.setFamilyPerson1Mobile(dto.getFamilyPerson1Mobile());
        d.setFamilyPerson2Name(dto.getFamilyPerson2Name());
        d.setFamilyPerson2Mobile(dto.getFamilyPerson2Mobile());
        d.setGdcRegistrationNumber(dto.getGdcRegistrationNumber());
        d.setReferenceDriverName(dto.getReferenceDriverName());

        return d;
    }
}
