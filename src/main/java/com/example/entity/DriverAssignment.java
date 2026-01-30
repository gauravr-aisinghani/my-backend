package com.example.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "yfs_driver_assignments")
public class DriverAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long assignmentId;

    @Column(name = "request_id", nullable = false)
    private Long requestId;

    @Column(name = "assigned_driver_registration_id", nullable = false)
    private Long assignedDriverRegistrationId;

    @Column(
        name = "transporter_registration_id",
        columnDefinition = "BINARY(16)",
        nullable = false
    )
    private UUID transporterRegistrationId;

    @Column(name = "transporter_phone")
    private String transporterPhone;

    @Column(name = "assigned_by")
    private Long assignedBy; // admin id

    @Column(name = "assignment_status")
    private String assignmentStatus; // ASSIGNED / RELEASED

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt = LocalDateTime.now();

    @Column(name = "released_at")
    private LocalDateTime releasedAt;

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

	public UUID getTransporterRegistrationId() {
		return transporterRegistrationId;
	}

	public void setTransporterRegistrationId(UUID transporterRegistrationId) {
		this.transporterRegistrationId = transporterRegistrationId;
	}

	public String getTransporterPhone() {
		return transporterPhone;
	}

	public void setTransporterPhone(String transporterPhone) {
		this.transporterPhone = transporterPhone;
	}

	public Long getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(Long assignedBy) {
		this.assignedBy = assignedBy;
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

	public LocalDateTime getReleasedAt() {
		return releasedAt;
	}

	public void setReleasedAt(LocalDateTime releasedAt) {
		this.releasedAt = releasedAt;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

    // getters setters
    
    
}
