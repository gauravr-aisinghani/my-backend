package com.example.service;

import com.example.dto.DriverExperienceDTO;
import com.example.entity.DriverExperience;
import com.example.exception.NotFoundException;
import com.example.repository.DriverExperienceRepository;
import com.example.service.DriverExperienceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverExperienceServiceImpl implements DriverExperienceService {

    private final DriverExperienceRepository repo;

    public DriverExperienceServiceImpl(DriverExperienceRepository repo) {
        this.repo = repo;
    }

    @Override
    public DriverExperience create(DriverExperienceDTO dto) {
        DriverExperience e = mapToEntity(dto);
        return repo.save(e);
    }

    @Override
    public DriverExperience getById(Long id) {
        return repo.findById(id).orElseThrow(() -> new NotFoundException("Driver experience not found"));
    }

    @Override
    public DriverExperience getByDriverRegistrationId(Long driverRegistrationId) {
        return repo.findByDriverRegistrationId(driverRegistrationId)
                .orElseThrow(() -> new NotFoundException("Driver experience not found for driver id: " + driverRegistrationId));
    }

    @Override
    public List<DriverExperience> getAllByDriverRegistrationId(Long driverRegistrationId) {
        return repo.findAllByDriverRegistrationId(driverRegistrationId);
    }

    @Override
    public List<DriverExperience> getAll() {
        return repo.findAll();
    }

    @Override
    public DriverExperience update(Long id, DriverExperienceDTO dto) {
        DriverExperience existing = repo.findById(id)
                .orElseThrow(() -> new NotFoundException("Driver experience not found"));

        // map updatable fields
        existing.setDriverRegistrationId(dto.getDriverRegistrationId());
        existing.setVehicleMake(dto.getVehicleMake());
        existing.setVehicleModel(dto.getVehicleModel());
        existing.setLastTransportName(dto.getLastTransportName());
        existing.setOwnerName(dto.getOwnerName());
        existing.setGaadiNumber(dto.getGaadiNumber());
        existing.setTransportAddress(dto.getTransportAddress());
        existing.setOwnerContactNo(dto.getOwnerContactNo());
        existing.setTotalWorkOnVehicle(dto.getTotalWorkOnVehicle());
        existing.setTotalExperienceYears(dto.getTotalExperienceYears());
        existing.setLeavingReason(dto.getLeavingReason());
        existing.setPostOfDriving(dto.getPostOfDriving());
        existing.setFromDate(dto.getFromDate());
        existing.setToDate(dto.getToDate());

        return repo.save(existing);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    private DriverExperience mapToEntity(DriverExperienceDTO dto) {
        DriverExperience e = new DriverExperience();
        e.setDriverRegistrationId(dto.getDriverRegistrationId());
        e.setVehicleMake(dto.getVehicleMake());
        e.setVehicleModel(dto.getVehicleModel());
        e.setLastTransportName(dto.getLastTransportName());
        e.setOwnerName(dto.getOwnerName());
        e.setGaadiNumber(dto.getGaadiNumber());
        e.setTransportAddress(dto.getTransportAddress());
        e.setOwnerContactNo(dto.getOwnerContactNo());
        e.setTotalWorkOnVehicle(dto.getTotalWorkOnVehicle());
        e.setTotalExperienceYears(dto.getTotalExperienceYears());
        e.setLeavingReason(dto.getLeavingReason());
        e.setPostOfDriving(dto.getPostOfDriving());
        e.setFromDate(dto.getFromDate());
        e.setToDate(dto.getToDate());
        return e;
    }
}
