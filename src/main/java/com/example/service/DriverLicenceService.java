package com.example.service;

import com.example.dto.DriverLicenceDTO;
import com.example.entity.DriverLicenceDetails;

import java.util.List;

public interface DriverLicenceService {

    DriverLicenceDetails createLicence(DriverLicenceDTO dto);

    DriverLicenceDetails getLicenceById(Long id);

    DriverLicenceDetails getLicenceByDriverRegistrationId(Long driverRegistrationId);

    List<DriverLicenceDetails> getAllLicences();

    DriverLicenceDetails updateLicence(Long id, DriverLicenceDTO dto);

    void deleteLicence(Long id);
}
