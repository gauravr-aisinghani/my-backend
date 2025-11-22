package com.example.service;

import com.example.dto.DriverLicenceDTO;
import com.example.entity.DriverLicenceDetails;
import com.example.repository.DriverLicenceRepository;
import com.example.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverLicenceServiceImpl implements DriverLicenceService {

    private final DriverLicenceRepository repository;

    @Autowired
    public DriverLicenceServiceImpl(DriverLicenceRepository repository) {
        this.repository = repository;
    }

    @Override
    public DriverLicenceDetails createLicence(DriverLicenceDTO dto) {
        if (dto.getDriverRegistrationId() == null) {
            throw new IllegalArgumentException("driver_registration_id is required");
        }
        DriverLicenceDetails entity = dto.toEntity();
        return repository.save(entity);
    }

    @Override
    public DriverLicenceDetails getLicenceById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Licence not found with id " + id));
    }

    @Override
    public DriverLicenceDetails getLicenceByDriverRegistrationId(Long driverRegistrationId) {
        return repository.findByDriverRegistrationId(driverRegistrationId)
                .orElseThrow(() -> new ResourceNotFoundException("Licence not found for driver " + driverRegistrationId));
    }

    @Override
    public List<DriverLicenceDetails> getAllLicences() {
        return repository.findAll();
    }

    @Override
    public DriverLicenceDetails updateLicence(Long id, DriverLicenceDTO dto) {
        DriverLicenceDetails existing = getLicenceById(id);
        DriverLicenceDetails updated = dto.toEntity();
        updated.setDriverLicenceId(existing.getDriverLicenceId());
        // ensure driverRegistrationId doesn't change unexpectedly
        updated.setDriverRegistrationId(existing.getDriverRegistrationId());
        return repository.save(updated);
    }

    @Override
    public void deleteLicence(Long id) {
        DriverLicenceDetails existing = getLicenceById(id);
        repository.delete(existing);
    }
}
