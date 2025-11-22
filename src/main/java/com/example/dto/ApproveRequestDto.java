package com.example.dto;

public class ApproveRequestDto {
    private Long driverRegistrationId;
    private String remarks;
    private String approvedBy; // optional

    public ApproveRequestDto() {}

    public Long getDriverRegistrationId() { return driverRegistrationId; }
    public void setDriverRegistrationId(Long driverRegistrationId) { this.driverRegistrationId = driverRegistrationId; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }
}
