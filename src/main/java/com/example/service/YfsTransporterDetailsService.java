package com.example.service;

import com.example.dto.YfsTransporterDetailsDto;

public interface YfsTransporterDetailsService {

    YfsTransporterDetailsDto saveTransporterDetails(
            YfsTransporterDetailsDto transporterDetailsDto
    );

    YfsTransporterDetailsDto getByRegistrationId(String transporterRegistrationId);
}
