package com.example.dto;

public class LoginResponseDto {

    private boolean exists;
    private String message;

    // 🔑 websocket + notification ke liye
    private String role;
    private String userId;   // transporter / driver = mobile, admin = email

    // ✅ old constructor (taaki purana code na toote)
    public LoginResponseDto(boolean exists, String message) {
        this.exists = exists;
        this.message = message;
    }

    // ✅ new constructor (websocket + offline notification ke liye)
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

	public boolean isExists() {
		return exists;
	}

	public void setExists(boolean exists) {
		this.exists = exists;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

    // getters setters tu generate karega
    
    
}
