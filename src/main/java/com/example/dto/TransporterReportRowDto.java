package com.example.dto;

public class TransporterReportRowDto {

    private String transporterId;
    private String companyName;
    private String ownerMobile;
    private String stage;
    private String verificationStatus;
    private String gdcNumber;

    public TransporterReportRowDto(
            String transporterId,
            String companyName,
            String ownerMobile,
            String stage,
            String verificationStatus,
            String gdcNumber
    ) {
        this.transporterId = transporterId;
        this.companyName = companyName;
        this.ownerMobile = ownerMobile;
        this.stage = stage;
        this.verificationStatus = verificationStatus;
        this.gdcNumber = gdcNumber;
    }

	public String getTransporterId() {
		return transporterId;
	}

	public void setTransporterId(String transporterId) {
		this.transporterId = transporterId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOwnerMobile() {
		return ownerMobile;
	}

	public void setOwnerMobile(String ownerMobile) {
		this.ownerMobile = ownerMobile;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getVerificationStatus() {
		return verificationStatus;
	}

	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}

	public String getGdcNumber() {
		return gdcNumber;
	}

	public void setGdcNumber(String gdcNumber) {
		this.gdcNumber = gdcNumber;
	}

    // getters
    
}
