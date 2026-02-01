package com.example.dto;

import java.time.LocalDateTime;

public interface IdealDriverDto {

    Long getDriverRegistrationId();
    String getDriverName();
    String getMobileNumber();
    String getGdcNumber();

    LocalDateTime getPaymentDate();
    LocalDateTime getIdleSince();
}
