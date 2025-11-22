package com.example.service;

import com.example.dto.DriverSarpanchDTO;
import com.example.entity.DriverSarpanchDetails;

import java.util.List;

public interface DriverSarpanchService {

    DriverSarpanchDetails create(DriverSarpanchDTO dto);

    DriverSarpanchDetails update(Long id, DriverSarpanchDTO dto);

    DriverSarpanchDetails getById(Long id);

    DriverSarpanchDetails getByDriverRegistrationId(Long driverRegistrationId);

    List<DriverSarpanchDetails> getAll();

    void delete(Long id);
}
