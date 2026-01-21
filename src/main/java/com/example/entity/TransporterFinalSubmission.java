package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_transporter_final_submission")
public class TransporterFinalSubmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "final_id")
    private Long finalId;

    @Column(name = "transporter_registration_id")
    private String transporterRegistrationId;

    @Column(name = "verification_id")
    private Long verificationId;

    @Column(name = "gdc_registration_number", unique = true)
    private String gdcRegistrationNumber;

    @Column(name = "id_card_url")
    private String idCardUrl;

    @Column(name = "completion_status")
    private String completionStatus;

    @Column(name = "final_approved_by")
    private String finalApprovedBy;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "terms_status")
    private String termsStatus;

    @Column(name = "whatsapp_sent")
    private Boolean whatsappSent;

    @Column(name = "whatsapp_sent_at")
    private LocalDateTime whatsappSentAt;

    @Column(name = "final_approved_at")
    private LocalDateTime finalApprovedAt;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public TransporterFinalSubmission() {}

	public Long getFinalId() {
		return finalId;
	}

	public void setFinalId(Long finalId) {
		this.finalId = finalId;
	}

	public String getTransporterRegistrationId() {
		return transporterRegistrationId;
	}

	public void setTransporterRegistrationId(String transporterRegistrationId) {
		this.transporterRegistrationId = transporterRegistrationId;
	}

	public Long getVerificationId() {
		return verificationId;
	}

	public void setVerificationId(Long verificationId) {
		this.verificationId = verificationId;
	}

	public String getGdcRegistrationNumber() {
		return gdcRegistrationNumber;
	}

	public void setGdcRegistrationNumber(String gdcRegistrationNumber) {
		this.gdcRegistrationNumber = gdcRegistrationNumber;
	}

	public String getIdCardUrl() {
		return idCardUrl;
	}

	public void setIdCardUrl(String idCardUrl) {
		this.idCardUrl = idCardUrl;
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public String getFinalApprovedBy() {
		return finalApprovedBy;
	}

	public void setFinalApprovedBy(String finalApprovedBy) {
		this.finalApprovedBy = finalApprovedBy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getTermsStatus() {
		return termsStatus;
	}

	public void setTermsStatus(String termsStatus) {
		this.termsStatus = termsStatus;
	}

	public Boolean getWhatsappSent() {
		return whatsappSent;
	}

	public void setWhatsappSent(Boolean whatsappSent) {
		this.whatsappSent = whatsappSent;
	}

	public LocalDateTime getWhatsappSentAt() {
		return whatsappSentAt;
	}

	public void setWhatsappSentAt(LocalDateTime whatsappSentAt) {
		this.whatsappSentAt = whatsappSentAt;
	}

	public LocalDateTime getFinalApprovedAt() {
		return finalApprovedAt;
	}

	public void setFinalApprovedAt(LocalDateTime finalApprovedAt) {
		this.finalApprovedAt = finalApprovedAt;
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
    
    
}
