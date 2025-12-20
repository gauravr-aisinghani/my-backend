package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_selected_transporter")
public class SelectedTransporter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_transporter_id")
    private Long selectedTransporterId;

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

    @Column(name = "gaadi_type")
    private String gaadiType;

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

    @Column(name = "qty_final_driver")
    private Integer qtyFinalDriver;

    @Column(name = "final_application")
    private String finalApplication;

    @Column(name = "final_date")
    private LocalDate finalDate;

    @Column(name = "approval_status")
    private String approvalStatus;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

	public Long getSelectedTransporterId() {
		return selectedTransporterId;
	}

	public void setSelectedTransporterId(Long selectedTransporterId) {
		this.selectedTransporterId = selectedTransporterId;
	}

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

	public String getGaadiType() {
		return gaadiType;
	}

	public void setGaadiType(String gaadiType) {
		this.gaadiType = gaadiType;
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

	public Integer getQtyFinalDriver() {
		return qtyFinalDriver;
	}

	public void setQtyFinalDriver(Integer qtyFinalDriver) {
		this.qtyFinalDriver = qtyFinalDriver;
	}

	public String getFinalApplication() {
		return finalApplication;
	}

	public void setFinalApplication(String finalApplication) {
		this.finalApplication = finalApplication;
	}

	public LocalDate getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
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

    // getters & setters
    
}
