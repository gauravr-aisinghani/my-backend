package com.example.dto;

public class DriverReportRowDto {

    private Long driverId;
    private String name;
    private String mobile;
    private String stage;
    private String verificationStatus;
    private String gdcNumber;

    public DriverReportRowDto(Long driverId, String name, String mobile,
                              String stage, String verificationStatus, String gdcNumber) {
        this.driverId = driverId;
        this.name = name;
        this.mobile = mobile;
        this.stage = stage;
        this.verificationStatus = verificationStatus;
        this.gdcNumber = gdcNumber;
    }

    public Long getDriverId() {
        return driverId;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getStage() {
        return stage;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public String getGdcNumber() {
        return gdcNumber;
    }
}
