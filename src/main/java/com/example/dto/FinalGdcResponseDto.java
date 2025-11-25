package com.example.dto;

public class FinalGdcResponseDto {

    private String gdc;
    private String idCardUrl;
    private String message;

    public FinalGdcResponseDto() {}

    public FinalGdcResponseDto(String gdc, String idCardUrl, String message) {
        this.gdc = gdc;
        this.idCardUrl = idCardUrl;
        this.message = message;
    }

    public String getGdc() { return gdc; }
    public void setGdc(String gdc) { this.gdc = gdc; }

    public String getIdCardUrl() { return idCardUrl; }
    public void setIdCardUrl(String idCardUrl) { this.idCardUrl = idCardUrl; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
