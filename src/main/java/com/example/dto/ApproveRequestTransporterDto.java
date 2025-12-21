package com.example.dto;

public class ApproveRequestTransporterDto {

    private String transporterRegistrationId;
    private Long verificationId;
    private String remarks;
    private String approvedBy;

    public ApproveRequestTransporterDto() {}

    public String getTransporterRegistrationId() { return transporterRegistrationId; }
    public void setTransporterRegistrationId(String transporterRegistrationId) { this.transporterRegistrationId = transporterRegistrationId; }

    public Long getVerificationId() { return verificationId; }
    public void setVerificationId(Long verificationId) { this.verificationId = verificationId; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
}
