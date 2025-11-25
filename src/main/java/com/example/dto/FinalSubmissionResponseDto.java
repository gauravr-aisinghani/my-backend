package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FinalSubmissionResponseDto {

    @JsonProperty("gdc_registration_number")
    private String gdcRegistrationNumber;

    @JsonProperty("id_card_url")
    private String idCardUrl;

    @JsonProperty("message")
    private String message;

    public FinalSubmissionResponseDto() {}

    public FinalSubmissionResponseDto(String gdcRegistrationNumber, String idCardUrl, String message) {
        this.gdcRegistrationNumber = gdcRegistrationNumber;
        this.idCardUrl = idCardUrl;
        this.message = message;
    }

    public String getGdcRegistrationNumber() { return gdcRegistrationNumber; }
    public void setGdcRegistrationNumber(String gdcRegistrationNumber) { this.gdcRegistrationNumber = gdcRegistrationNumber; }

    public String getIdCardUrl() { return idCardUrl; }
    public void setIdCardUrl(String idCardUrl) { this.idCardUrl = idCardUrl; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
