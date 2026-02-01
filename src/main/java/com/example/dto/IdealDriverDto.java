package com.example.dto;

import java.time.LocalDateTime;

public class IdealDriverDto {

    private Long driverRegistrationId;
    private String driverName;
    private String mobileNumber;
    private String gdcNumber;
    private LocalDateTime paymentDate;
    private LocalDateTime idleSince;

    public IdealDriverDto() {
    }

    public Long getDriverRegistrationId() {
        return driverRegistrationId;
    }

    public void setDriverRegistrationId(Long driverRegistrationId) {
        this.driverRegistrationId = driverRegistrationId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getGdcNumber() {
        return gdcNumber;
    }

    public void setGdcNumber(String gdcNumber) {
        this.gdcNumber = gdcNumber;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public LocalDateTime getIdleSince() {
        return idleSince;
    }

    public void setIdleSince(LocalDateTime idleSince) {
        this.idleSince = idleSince;
    }
}
