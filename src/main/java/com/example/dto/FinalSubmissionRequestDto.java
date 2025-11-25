package com.example.dto;


public class FinalSubmissionRequestDto {
private Long driverRegistrationId;
private Long verificationId;
private String gdcRegistrationNumber; // optional - frontend may send
private String remarks;
private String finalApprovedBy;
private String termsStatus; // optional


public FinalSubmissionRequestDto() {}


public Long getDriverRegistrationId() { return driverRegistrationId; }
public void setDriverRegistrationId(Long driverRegistrationId) { this.driverRegistrationId = driverRegistrationId; }


public Long getVerificationId() { return verificationId; }
public void setVerificationId(Long verificationId) { this.verificationId = verificationId; }


public String getGdcRegistrationNumber() { return gdcRegistrationNumber; }
public void setGdcRegistrationNumber(String gdcRegistrationNumber) { this.gdcRegistrationNumber = gdcRegistrationNumber; }


public String getRemarks() { return remarks; }
public void setRemarks(String remarks) { this.remarks = remarks; }


public String getFinalApprovedBy() { return finalApprovedBy; }
public void setFinalApprovedBy(String finalApprovedBy) { this.finalApprovedBy = finalApprovedBy; }


public String getTermsStatus() { return termsStatus; }
public void setTermsStatus(String termsStatus) { this.termsStatus = termsStatus; }
}