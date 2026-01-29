package com.example.entity;

import jakarta.persistence.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_driver_assignments")
public class TransporterDriverAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Long assignmentId;

    @Column(name = "request_id")
    private Long requestId;

    @Column(name = "assigned_driver_registration_id")
    private Long assignedDriverRegistrationId;

    @Column(name = "transporter_registration_id")
    private Long transporterRegistrationId;

    @Column(name = "transporter_phone")
    private String transporterPhone;

    @Column(name = "assignment_status")
    private String assignmentStatus;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @Column(name = "remarks")
    private String remarks;

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public Long getAssignedDriverRegistrationId() {
		return assignedDriverRegistrationId;
	}

	public void setAssignedDriverRegistrationId(Long assignedDriverRegistrationId) {
		this.assignedDriverRegistrationId = assignedDriverRegistrationId;
	}

	public Long getTransporterRegistrationId() {
		return transporterRegistrationId;
	}

	public void setTransporterRegistrationId(Long transporterRegistrationId) {
		this.transporterRegistrationId = transporterRegistrationId;
	}

	public String getTransporterPhone() {
		return transporterPhone;
	}

	public void setTransporterPhone(String transporterPhone) {
		this.transporterPhone = transporterPhone;
	}

	public String getAssignmentStatus() {
		return assignmentStatus;
	}

	public void setAssignmentStatus(String assignmentStatus) {
		this.assignmentStatus = assignmentStatus;
	}

	public LocalDateTime getAssignedAt() {
		return assignedAt;
	}

	public void setAssignedAt(LocalDateTime assignedAt) {
		this.assignedAt = assignedAt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    // 👉 getters & setters generate kar lena
    
    
}
