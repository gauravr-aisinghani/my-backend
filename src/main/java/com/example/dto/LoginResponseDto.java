package com.example.dto;

public class LoginResponseDto {

    private boolean exists;
    private String message;

    // 🔑 websocket + notification
    private String role;
    private String userId;   // mobile / email

    // 🆕 IDs (AS STRING)
    private String transporterRegistrationId;
    private Long driverRegistrationId;

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

    // ✅ FULL constructor
    public LoginResponseDto(
            boolean exists,
            String message,
            String role,
            String userId,
            String transporterRegistrationId,
            Long driverRegistrationId
    ) {
        this.exists = exists;
        this.message = message;
        this.role = role;
        this.userId = userId;
        this.transporterRegistrationId = transporterRegistrationId;
        this.driverRegistrationId = driverRegistrationId;
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
}
