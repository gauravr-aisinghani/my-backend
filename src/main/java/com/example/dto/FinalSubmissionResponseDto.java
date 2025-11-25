package com.example.dto;


public class FinalSubmissionResponseDto {
private String gdcRegistrationNumber;
private String idCardUrl;
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