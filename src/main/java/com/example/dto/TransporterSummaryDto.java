package com.example.dto;

public class TransporterSummaryDto {

    private Long registeredTransporters;
    private Long verificationPending;
    private Long verifiedTransporters;
    private Long gdcGenerated;
	public Long getRegisteredTransporters() {
		return registeredTransporters;
	}
	public void setRegisteredTransporters(Long registeredTransporters) {
		this.registeredTransporters = registeredTransporters;
	}
	public Long getVerificationPending() {
		return verificationPending;
	}
	public void setVerificationPending(Long verificationPending) {
		this.verificationPending = verificationPending;
	}
	public Long getVerifiedTransporters() {
		return verifiedTransporters;
	}
	public void setVerifiedTransporters(Long verifiedTransporters) {
		this.verifiedTransporters = verifiedTransporters;
	}
	public Long getGdcGenerated() {
		return gdcGenerated;
	}
	public void setGdcGenerated(Long gdcGenerated) {
		this.gdcGenerated = gdcGenerated;
	}

    // getters & setters
    
    
}
