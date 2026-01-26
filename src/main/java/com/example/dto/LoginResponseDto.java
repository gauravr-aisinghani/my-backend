package com.example.dto;

public class LoginResponseDto {

    private boolean exists;
    private String message;

    // 🔑 websocket + notification
    private String role;
    private String userId;   // mobile / email

    // 🆕 IDs
    private String transporterRegistrationId;
    private Long driverRegistrationId;

    // 🆕 GDC number (driver / transporter)
    private String gdcNumber;

    // old constructor (safe)
    public LoginResponseDto(boolean exists, String message) {
        this.exists = exists;
        this.message = message;
    }

    // role + userId
    public LoginResponseDto(
            boolean exists,
            String message,
            String role,
            String userId
    ) {
        this.exists = exists;
        this.message = message;
        this.role = role;
        this.userId = userId;
    }

    // ✅ FULL constructor (with GDC)
    public LoginResponseDto(
            boolean exists,
            String message,
            String role,
            String userId,
            String transporterRegistrationId,
            Long driverRegistrationId,
            String gdcNumber
    ) {
        this.exists = exists;
        this.message = message;
        this.role = role;
        this.userId = userId;
        this.transporterRegistrationId = transporterRegistrationId;
        this.driverRegistrationId = driverRegistrationId;
        this.gdcNumber = gdcNumber;
    }

    public boolean isExists() { return exists; }
    public void setExists(boolean exists) { this.exists = exists; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getTransporterRegistrationId() {
        return transporterRegistrationId;
    }

    public void setTransporterRegistrationId(String transporterRegistrationId) {
        this.transporterRegistrationId = transporterRegistrationId;
    }

    public Long getDriverRegistrationId() {
        return driverRegistrationId;
    }

    public void setDriverRegistrationId(Long driverRegistrationId) {
        this.driverRegistrationId = driverRegistrationId;
    }

    public String getGdcNumber() {
        return gdcNumber;
    }

    public void setGdcNumber(String gdcNumber) {
        this.gdcNumber = gdcNumber;
    }
}
