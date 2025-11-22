package com.example.dto;

public class PendingDriverDto {
    private Long driverRegistrationId;
    private String fullName;
    private String mobileNo;
    private Integer totalDocs;

    public PendingDriverDto() {}

    public Long getDriverRegistrationId() { return driverRegistrationId; }
    public void setDriverRegistrationId(Long driverRegistrationId) { this.driverRegistrationId = driverRegistrationId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public Integer getTotalDocs() { return totalDocs; }
    public void setTotalDocs(Integer totalDocs) { this.totalDocs = totalDocs; }
}
