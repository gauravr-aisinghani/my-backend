package com.example.dto;

public class FinalDriverProfileDTO {

    private String fullName;
    private String mobileNumber;
    private String fullAddress;
    private String driverSelfie;

    public FinalDriverProfileDTO(String fullName,
                                 String mobileNumber,
                                 String fullAddress,
                                 String driverSelfie) {
        this.fullName = fullName;
        this.mobileNumber = mobileNumber;
        this.fullAddress = fullAddress;
        this.driverSelfie = driverSelfie;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getDriverSelfie() {
        return driverSelfie;
    }
}
