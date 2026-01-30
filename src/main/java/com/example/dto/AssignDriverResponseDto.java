package com.example.dto;

public class AssignDriverResponseDto {

    private Long assignment_id;
    private String message;

    public AssignDriverResponseDto(Long assignmentId, String message) {
        this.assignment_id = assignmentId;
        this.message = message;
    }

	public Long getAssignment_id() {
		return assignment_id;
	}

	public void setAssignment_id(Long assignment_id) {
		this.assignment_id = assignment_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

    // getters]]]
    
    
}

