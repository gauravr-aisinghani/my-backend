package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransporterFinalSubmissionResponseDto {

    @JsonProperty("gdc_registration_number")
    private String gdcRegistrationNumber;

    @JsonProperty("id_card_url")
    private String idCardUrl;

    @JsonProperty("message")
    private String message;

    public TransporterFinalSubmissionResponseDto() {}

    public TransporterFinalSubmissionResponseDto(
            String gdc,
            String url,
            String msg
    ) {
        this.gdcRegistrationNumber = gdc;
        this.idCardUrl = url;
        this.message = msg;
    }

	public String getGdcRegistrationNumber() {
		return gdcRegistrationNumber;
	}

	public void setGdcRegistrationNumber(String gdcRegistrationNumber) {
		this.gdcRegistrationNumber = gdcRegistrationNumber;
	}

	public String getIdCardUrl() {
		return idCardUrl;
	}

	public void setIdCardUrl(String idCardUrl) {
		this.idCardUrl = idCardUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
