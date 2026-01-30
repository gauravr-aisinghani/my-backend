package com.example.dto;

public class AssignDriverResponseDto {

    private String message;
    private Long assignmentId;

    public AssignDriverResponseDto(String message, Long assignmentId) {
        this.message = message;
        this.assignmentId = assignmentId;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

    // getters
    
    
}
