package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitor_transporter")
public class VisitorTransporter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_transporter_id")
    private Long visitorTransporterId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "owner_mobile_no")
    private String ownerMobileNo;

    @Column(name = "authorised_name")
    private String authorisedName;

    @Column(name = "authorised_mobile_no")
    private String authorisedMobileNo;

    @Column(name = "need_driver")
    private Integer needDriver;

    @Column(name = "gaadi_type")
    private String gaadiType;

    @Column(name = "running_application")
    private String runningApplication;

    @Column(name = "loading_place")
    private String loadingPlace;

    @Column(name = "unload_place")
    private String unloadPlace;

    @Column(name = "monthly_salary")
    private Double monthlySalary;

    @Column(name = "other_benefit")
    private String otherBenefit;

    @Column(name = "need_timing")
    private String needTiming;

    @Column(name = "notes")
    private String notes;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

	public Long getVisitorTransporterId() {
		return visitorTransporterId;
	}

	public void setVisitorTransporterId(Long visitorTransporterId) {
		this.visitorTransporterId = visitorTransporterId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerMobileNo() {
		return ownerMobileNo;
	}

	public void setOwnerMobileNo(String ownerMobileNo) {
		this.ownerMobileNo = ownerMobileNo;
	}

	public String getAuthorisedName() {
		return authorisedName;
	}

	public void setAuthorisedName(String authorisedName) {
		this.authorisedName = authorisedName;
	}

	public String getAuthorisedMobileNo() {
		return authorisedMobileNo;
	}

	public void setAuthorisedMobileNo(String authorisedMobileNo) {
		this.authorisedMobileNo = authorisedMobileNo;
	}

	public Integer getNeedDriver() {
		return needDriver;
	}

	public void setNeedDriver(Integer needDriver) {
		this.needDriver = needDriver;
	}

	public String getGaadiType() {
		return gaadiType;
	}

	public void setGaadiType(String gaadiType) {
		this.gaadiType = gaadiType;
	}

	public String getRunningApplication() {
		return runningApplication;
	}

	public void setRunningApplication(String runningApplication) {
		this.runningApplication = runningApplication;
	}

	public String getLoadingPlace() {
		return loadingPlace;
	}

	public void setLoadingPlace(String loadingPlace) {
		this.loadingPlace = loadingPlace;
	}

	public String getUnloadPlace() {
		return unloadPlace;
	}

	public void setUnloadPlace(String unloadPlace) {
		this.unloadPlace = unloadPlace;
	}

	public Double getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(Double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public String getOtherBenefit() {
		return otherBenefit;
	}

	public void setOtherBenefit(String otherBenefit) {
		this.otherBenefit = otherBenefit;
	}

	public String getNeedTiming() {
		return needTiming;
	}

	public void setNeedTiming(String needTiming) {
		this.needTiming = needTiming;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

    // getters and setters
    
    
}
