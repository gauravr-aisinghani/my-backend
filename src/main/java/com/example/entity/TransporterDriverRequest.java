package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a Driver Request raised by Transporter
 * Table: yfs_driver_requests
 */
@Entity
@Table(name = "yfs_driver_requests")
public class TransporterDriverRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId;

    @Column(
        name = "transporter_registration_id",
        nullable = false,
        length = 255
    )
    private String transporterRegistrationId; // Changed from UUID to String

    private String transporterPhone;

    private String gdcNumber;

    private String vehicleNumber;

    private String route;

    @Column(name = "advance_paid")
    private Boolean advancePaid = false;

    @Column(name = "settlement_paid")
    private Boolean settlementPaid = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_grade")
    private VehicleGrade vehicleGrade;

    private Double monthlySalary;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @Enumerated(EnumType.STRING)
    private CompletionStatus completionStatus = CompletionStatus.PENDING;

    private Long assignedDriverId; // NULL initially

    private String remarks;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime updatedAt = LocalDateTime.now();

    // enums
    public enum Status {
        PENDING, APPROVED, ASSIGNED, REJECTED, CANCELLED
    }

    public enum CompletionStatus {
        PENDING, PARTIAL, COMPLETED
    }

    // ======== Getters & Setters ========

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getTransporterRegistrationId() {
        return transporterRegistrationId;
    }

    public void setTransporterRegistrationId(String transporterRegistrationId) {
        this.transporterRegistrationId = transporterRegistrationId;
    }

    public String getTransporterPhone() {
        return transporterPhone;
    }

    public void setTransporterPhone(String transporterPhone) {
        this.transporterPhone = transporterPhone;
    }

    public String getGdcNumber() {
        return gdcNumber;
    }

    public void setGdcNumber(String gdcNumber) {
        this.gdcNumber = gdcNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public CompletionStatus getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(CompletionStatus completionStatus) {
        this.completionStatus = completionStatus;
    }

    public Long getAssignedDriverId() {
        return assignedDriverId;
    }

    public void setAssignedDriverId(Long assignedDriverId) {
        this.assignedDriverId = assignedDriverId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getAdvancePaid() {
        return advancePaid;
    }

    public void setAdvancePaid(Boolean advancePaid) {
        this.advancePaid = advancePaid;
    }

    public Boolean getSettlementPaid() {
        return settlementPaid;
    }

    public void setSettlementPaid(Boolean settlementPaid) {
        this.settlementPaid = settlementPaid;
    }

    public VehicleGrade getVehicleGrade() {
        return vehicleGrade;
    }

    public void setVehicleGrade(VehicleGrade vehicleGrade) {
        this.vehicleGrade = vehicleGrade;
    }

}
