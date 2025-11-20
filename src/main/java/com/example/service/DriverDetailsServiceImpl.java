package com.example.service;

import com.example.dto.DriverDetailsDTO;
import com.example.entity.DriverDetails;
import com.example.repository.DriverDetailsRepository;
import com.example.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class DriverDetailsServiceImpl implements DriverDetailsService {

    private final DriverDetailsRepository repository;

    @Autowired
    public DriverDetailsServiceImpl(DriverDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public DriverDetails createDriver(DriverDetailsDTO dto) {
        DriverDetails driver = dto.toEntity();
        return repository.save(driver);
    }

    @Override
    public DriverDetails getDriverById(Long driverId) {
        return repository.findById(driverId)
                .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id " + driverId));
    }

    @Override
    public List<DriverDetails> getAllDrivers() {
        return repository.findAll();
    }

    @Override
    public DriverDetails updateDriver(Long driverId, DriverDetailsDTO dto) {
        DriverDetails existing = getDriverById(driverId);
        DriverDetails updated = dto.toEntity();
        updated.setDriverId(existing.getDriverId());
        return repository.save(updated);
    }

    @Override
    public void deleteDriver(Long driverId) {
        DriverDetails existing = getDriverById(driverId);
        repository.delete(existing);
    }
}
