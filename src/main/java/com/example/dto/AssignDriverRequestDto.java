package com.example.dto;

public class AssignDriverRequestDto {

    private Long requestId;
    private Long driverRegistrationId;
    private Long adminId;
    private String remarks;
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public Long getDriverRegistrationId() {
		return driverRegistrationId;
	}
	public void setDriverRegistrationId(Long driverRegistrationId) {
		this.driverRegistrationId = driverRegistrationId;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    // getters setters
    
    
}
