package com.example.service;

import org.springframework.stereotype.Service;

import com.example.dto.YfsTransporterDetailsDto;
import com.example.entity.YfsTransporterDetails;
import com.example.repository.YfsTransporterDetailsRepository;
import com.example.service.YfsTransporterDetailsService;

@Service
public class YfsTransporterDetailsServiceImpl
        implements YfsTransporterDetailsService {

    private final YfsTransporterDetailsRepository repository;

    public YfsTransporterDetailsServiceImpl(
            YfsTransporterDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public YfsTransporterDetailsDto saveTransporterDetails(
            YfsTransporterDetailsDto dto) {

        YfsTransporterDetails entity = new YfsTransporterDetails();

        entity.setTransporterRegistrationId(dto.getTransporterRegistrationId());
        entity.setTransportCompanyName(dto.getTransportCompanyName());
        entity.setGstNumber(dto.getGstNumber());
        entity.setAddress(dto.getAddress());
        entity.setOwnerName(dto.getOwnerName());
        entity.setOwnerMobileNumber(dto.getOwnerMobileNumber());
        entity.setContactManagerName(dto.getContactManagerName());
        entity.setContactManagerMobileNumber(dto.getContactManagerMobileNumber());
        entity.setEmailId(dto.getEmailId());
        entity.setPanCardNumber(dto.getPanCardNumber());
        entity.setAadharNumber(dto.getAadharNumber());
        entity.setDlNumber(dto.getDlNumber());

        repository.save(entity);

        return dto;
    }

    @Override
    public YfsTransporterDetailsDto getByRegistrationId(
            String transporterRegistrationId) {

        YfsTransporterDetails entity = repository.findById(
                transporterRegistrationId
        ).orElseThrow(() -> new RuntimeException("Transporter not found"));

        YfsTransporterDetailsDto dto = new YfsTransporterDetailsDto();

        dto.setTransporterRegistrationId(entity.getTransporterRegistrationId());
        dto.setTransportCompanyName(entity.getTransportCompanyName());
        dto.setGstNumber(entity.getGstNumber());
        dto.setAddress(entity.getAddress());
        dto.setOwnerName(entity.getOwnerName());
        dto.setOwnerMobileNumber(entity.getOwnerMobileNumber());
        dto.setContactManagerName(entity.getContactManagerName());
        dto.setContactManagerMobileNumber(entity.getContactManagerMobileNumber());
        dto.setEmailId(entity.getEmailId());
        dto.setPanCardNumber(entity.getPanCardNumber());
        dto.setAadharNumber(entity.getAadharNumber());
        dto.setDlNumber(entity.getDlNumber());

        return dto;
    }
}
