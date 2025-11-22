package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_licence_details")
public class DriverLicenceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_licence_id")
    private Long driverLicenceId;

    @Column(name = "driver_registration_id", nullable = false)
    private Long driverRegistrationId;

    @Column(name = "employee_card_no", length = 100)
    private String employeeCardNo;

    @Column(name = "pan_number", length = 20)
    private String panNumber;

    @Column(name = "licence_number", length = 50)
    private String licenceNumber;

    @Column(name = "licence_grade", length = 50)
    private String licenceGrade;

    @Column(name = "issue_date")
    private LocalDate issueDate;

    @Column(name = "validity_end_date")
    private LocalDate validityEndDate;

    @Column(name = "issuing_authority", length = 150)
    private String issuingAuthority;

    @Column(name = "any_offence", length = 3)
    private String anyOffence; // "YES" or "NO"

    @Column(name = "offence_remark", length = 500)
    private String offenceRemark;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters & Setters

    public Long getDriverLicenceId() { return driverLicenceId; }
    public void setDriverLicenceId(Long driverLicenceId) { this.driverLicenceId = driverLicenceId; }

    public Long getDriverRegistrationId() { return driverRegistrationId; }
    public void setDriverRegistrationId(Long driverRegistrationId) { this.driverRegistrationId = driverRegistrationId; }

    public String getEmployeeCardNo() { return employeeCardNo; }
    public void setEmployeeCardNo(String employeeCardNo) { this.employeeCardNo = employeeCardNo; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

    public String getLicenceNumber() { return licenceNumber; }
    public void setLicenceNumber(String licenceNumber) { this.licenceNumber = licenceNumber; }

    public String getLicenceGrade() { return licenceGrade; }
    public void setLicenceGrade(String licenceGrade) { this.licenceGrade = licenceGrade; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getValidityEndDate() { return validityEndDate; }
    public void setValidityEndDate(LocalDate validityEndDate) { this.validityEndDate = validityEndDate; }

    public String getIssuingAuthority() { return issuingAuthority; }
    public void setIssuingAuthority(String issuingAuthority) { this.issuingAuthority = issuingAuthority; }

    public String getAnyOffence() { return anyOffence; }
    public void setAnyOffence(String anyOffence) { this.anyOffence = anyOffence; }

    public String getOffenceRemark() { return offenceRemark; }
    public void setOffenceRemark(String offenceRemark) { this.offenceRemark = offenceRemark; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
