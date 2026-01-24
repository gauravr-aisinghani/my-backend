package com.example.dto;

public class NotificationMessage {

    private String title;
    private String message;
    private String role;
    private Long userId;

    public NotificationMessage() {}

    public NotificationMessage(String title, String message, String role, Long userId) {
        this.title = title;
        this.message = message;
        this.role = role;
        this.userId = userId;
    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

    // getters setters
    
    
}
