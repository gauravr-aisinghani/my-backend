package com.example.dto;

public class VerifyPaymentResponse {

    private String status;
    private String message;

    public VerifyPaymentResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
