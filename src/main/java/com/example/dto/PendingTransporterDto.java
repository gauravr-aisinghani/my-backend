package com.example.dto;

public class PendingTransporterDto {

    private String transporterRegistrationId;
    private String companyName;
    private String ownerMobile;
    private Integer totalDocs;

    public PendingTransporterDto() {}

    public String getTransporterRegistrationId() { return transporterRegistrationId; }
    public void setTransporterRegistrationId(String transporterRegistrationId) { this.transporterRegistrationId = transporterRegistrationId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getOwnerMobile() { return ownerMobile; }
    public void setOwnerMobile(String ownerMobile) { this.ownerMobile = ownerMobile; }

    public Integer getTotalDocs() { return totalDocs; }
    public void setTotalDocs(Integer totalDocs) { this.totalDocs = totalDocs; }
}
