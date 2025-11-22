package com.example.service;

import com.example.dto.DriverExperienceDTO;
import com.example.entity.DriverExperience;

import java.util.List;

public interface DriverExperienceService {

    DriverExperience create(DriverExperienceDTO dto);

    DriverExperience getById(Long id);

    DriverExperience getByDriverRegistrationId(Long driverRegistrationId);

    List<DriverExperience> getAllByDriverRegistrationId(Long driverRegistrationId);

    List<DriverExperience> getAll();

    DriverExperience update(Long id, DriverExperienceDTO dto);

    void delete(Long id);
}
