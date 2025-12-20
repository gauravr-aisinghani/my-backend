package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_transporter_details")
public class YfsTransporterDetails {

    @Id
    @Column(name = "transporter_registration_id", length = 36)
    private String transporterRegistrationId;

    @Column(name = "transport_company_name", nullable = false, length = 150)
    private String transportCompanyName;

    @Column(name = "gst_number", nullable = false, length = 15)
    private String gstNumber;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "owner_name", nullable = false, length = 100)
    private String ownerName;

    @Column(name = "owner_mobile_number", nullable = false, length = 15)
    private String ownerMobileNumber;

    @Column(name = "contact_manager_name", length = 100)
    private String contactManagerName;

    @Column(name = "contact_manager_mobile_number", length = 15)
    private String contactManagerMobileNumber;

    @Column(name = "email_id", length = 150)
    private String emailId;

    @Column(name = "pan_card_number", nullable = false, length = 10)
    private String panCardNumber;

    @Column(name = "aadhar_number", nullable = false, length = 12)
    private String aadharNumber;

    @Column(name = "dl_number", length = 25)
    private String dlNumber;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

	public String getTransporterRegistrationId() {
		return transporterRegistrationId;
	}

	public void setTransporterRegistrationId(String transporterRegistrationId) {
		this.transporterRegistrationId = transporterRegistrationId;
	}

	public String getTransportCompanyName() {
		return transportCompanyName;
	}

	public void setTransportCompanyName(String transportCompanyName) {
		this.transportCompanyName = transportCompanyName;
	}

	public String getGstNumber() {
		return gstNumber;
	}

	public void setGstNumber(String gstNumber) {
		this.gstNumber = gstNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerMobileNumber() {
		return ownerMobileNumber;
	}

	public void setOwnerMobileNumber(String ownerMobileNumber) {
		this.ownerMobileNumber = ownerMobileNumber;
	}

	public String getContactManagerName() {
		return contactManagerName;
	}

	public void setContactManagerName(String contactManagerName) {
		this.contactManagerName = contactManagerName;
	}

	public String getContactManagerMobileNumber() {
		return contactManagerMobileNumber;
	}

	public void setContactManagerMobileNumber(String contactManagerMobileNumber) {
		this.contactManagerMobileNumber = contactManagerMobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPanCardNumber() {
		return panCardNumber;
	}

	public void setPanCardNumber(String panCardNumber) {
		this.panCardNumber = panCardNumber;
	}

	public String getAadharNumber() {
		return aadharNumber;
	}

	public void setAadharNumber(String aadharNumber) {
		this.aadharNumber = aadharNumber;
	}

	public String getDlNumber() {
		return dlNumber;
	}

	public void setDlNumber(String dlNumber) {
		this.dlNumber = dlNumber;
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

    // Getters and Setters
    // (Generate from Eclipse)
    
}
