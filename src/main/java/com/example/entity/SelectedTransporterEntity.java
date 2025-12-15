package com.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_selected_transporter")
public class SelectedTransporterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_transporter_id")
    private Long selectedTransporterId;

    private Long visitorTransporterId;

    private LocalDate finalDate;

    private String companyName;
    private String ownerName;
    private String ownerMobileNo;

    private String authorisedName;
    private String authorisedMobileNo;

    private Integer qtyFinalDriver;
    private String gaadiType;
    private String finalApplication;

    private String loadingPlace;
    private String unloadPlace;

    private Double monthlySalary;
    private String otherBenefit;
    private String needTiming;

    private String approvalStatus;
    private String notes;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
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

	public LocalDate getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
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

	public Integer getQtyFinalDriver() {
		return qtyFinalDriver;
	}

	public void setQtyFinalDriver(Integer qtyFinalDriver) {
		this.qtyFinalDriver = qtyFinalDriver;
	}

	public String getGaadiType() {
		return gaadiType;
	}

	public void setGaadiType(String gaadiType) {
		this.gaadiType = gaadiType;
	}

	public String getFinalApplication() {
		return finalApplication;
	}

	public void setFinalApplication(String finalApplication) {
		this.finalApplication = finalApplication;
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

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
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

    // Getters & Setters
    
}

