package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.example.entity.DriverLicenceDetails;

import java.time.LocalDate;

public class DriverLicenceDTO {

    @JsonProperty("driver_registration_id")
    private Long driverRegistrationId;

    @JsonProperty("employee_card_no")
    private String employeeCardNo;

    @JsonProperty("pan_number")
    private String panNumber;

    @JsonProperty("licence_number")
    private String licenceNumber;

    @JsonProperty("licence_grade")
    private String licenceGrade;

    @JsonProperty("issue_date")
    private String issueDate; // expecting "yyyy-MM-dd" from frontend

    @JsonProperty("validity_end_date")
    private String validityEndDate; // "yyyy-MM-dd"

    @JsonProperty("issuing_authority")
    private String issuingAuthority;

    @JsonProperty("any_offence")
    private String anyOffence; // "YES"/"NO"

    @JsonProperty("offence_remark")
    private String offenceRemark;

    public DriverLicenceDTO() {}

    // Convert to entity. Caller should validate driverRegistrationId presence.
    public DriverLicenceDetails toEntity() {
        DriverLicenceDetails e = new DriverLicenceDetails();
        e.setDriverRegistrationId(this.driverRegistrationId);
        e.setEmployeeCardNo(this.employeeCardNo);
        e.setPanNumber(this.panNumber);
        e.setLicenceNumber(this.licenceNumber);
        e.setLicenceGrade(this.licenceGrade);

        if (this.issueDate != null && !this.issueDate.isBlank()) {
            e.setIssueDate(LocalDate.parse(this.issueDate));
        }
        if (this.validityEndDate != null && !this.validityEndDate.isBlank()) {
            e.setValidityEndDate(LocalDate.parse(this.validityEndDate));
        }

        e.setIssuingAuthority(this.issuingAuthority);
        e.setAnyOffence(this.anyOffence);
        e.setOffenceRemark(this.offenceRemark);
        return e;
    }

    // Getters and setters

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

    public String getIssueDate() { return issueDate; }
    public void setIssueDate(String issueDate) { this.issueDate = issueDate; }

    public String getValidityEndDate() { return validityEndDate; }
    public void setValidityEndDate(String validityEndDate) { this.validityEndDate = validityEndDate; }

    public String getIssuingAuthority() { return issuingAuthority; }
    public void setIssuingAuthority(String issuingAuthority) { this.issuingAuthority = issuingAuthority; }

    public String getAnyOffence() { return anyOffence; }
    public void setAnyOffence(String anyOffence) { this.anyOffence = anyOffence; }

    public String getOffenceRemark() { return offenceRemark; }
    public void setOffenceRemark(String offenceRemark) { this.offenceRemark = offenceRemark; }
}
