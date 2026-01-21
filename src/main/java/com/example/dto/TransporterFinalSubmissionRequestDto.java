package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransporterFinalSubmissionRequestDto {

    @JsonProperty("transporter_registration_id")
    private String transporterRegistrationId;

    @JsonProperty("verification_id")
    private Long verificationId;

    @JsonProperty("gdc_registration_number")
    private String gdcRegistrationNumber;

    @JsonProperty("remarks")
    private String remarks;

    @JsonProperty("final_approved_by")
    private String finalApprovedBy;

    @JsonProperty("terms_status")
    private String termsStatus;

    public TransporterFinalSubmissionRequestDto() {}

	public String getTransporterRegistrationId() {
		return transporterRegistrationId;
	}

	public void setTransporterRegistrationId(String transporterRegistrationId) {
		this.transporterRegistrationId = transporterRegistrationId;
	}

	public Long getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(Long verificationId) {
		this.verificationId = verificationId;
	}

	public String getGdcRegistrationNumber() {
		return gdcRegistrationNumber;
	}

	public void setGdcRegistrationNumber(String gdcRegistrationNumber) {
		this.gdcRegistrationNumber = gdcRegistrationNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFinalApprovedBy() {
		return finalApprovedBy;
	}

	public void setFinalApprovedBy(String finalApprovedBy) {
		this.finalApprovedBy = finalApprovedBy;
	}

	public String getTermsStatus() {
		return termsStatus;
	}

	public void setTermsStatus(String termsStatus) {
		this.termsStatus = termsStatus;
	}
    
    
}
