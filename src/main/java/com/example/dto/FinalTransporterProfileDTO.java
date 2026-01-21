package com.example.dto;

public class FinalTransporterProfileDTO {

    private String companyName;
    private String mobileNumber;
    private String fullAddress;
    private String selfieUrl;

    public FinalTransporterProfileDTO(
            String companyName,
            String mobileNumber,
            String fullAddress,
            String selfieUrl
    ) {
        this.companyName = companyName;
        this.mobileNumber = mobileNumber;
        this.fullAddress = fullAddress;
        this.selfieUrl = selfieUrl;
    }

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getSelfieUrl() {
		return selfieUrl;
	}

	public void setSelfieUrl(String selfieUrl) {
		this.selfieUrl = selfieUrl;
	}
    
    
}
