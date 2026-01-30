package com.example.dto;

public class AssignDriverRequestDto {

    private Long request_id;
    private Long assigned_driver_registration_id;
    private String remarks;
	public Long getRequest_id() {
		return request_id;
	}
	public void setRequest_id(Long request_id) {
		this.request_id = request_id;
	}
	public Long getAssigned_driver_registration_id() {
		return assigned_driver_registration_id;
	}
	public void setAssigned_driver_registration_id(Long assigned_driver_registration_id) {
		this.assigned_driver_registration_id = assigned_driver_registration_id;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    // getters setters
    
    
}

