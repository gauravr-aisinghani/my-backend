package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_final_gdc")
public class FinalGdc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "final_id")
    private Long finalId;

    @Column(name = "verification_id", nullable = false)
    private Long verificationId;

    @Column(name = "driver_registration_id", nullable = false)
    private Long driverRegistrationId;

    @Column(name = "gdc_registration_number")
    private String gdcRegistrationNumber;

    @Column(name = "id_card_url")
    private String idCardUrl;

    @Column(name = "remarks")
    private String remarks;

    @Enumerated(EnumType.STRING)
    @Column(name = "completion_status")
    private CompletionStatus completionStatus = CompletionStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "terms_status")
    private TermsStatus termsStatus = TermsStatus.ACCEPT;

    @Column(name = "whatsapp_sent")
    private Boolean whatsappSent = false;

    @Column(name = "whatsapp_sent_at")
    private LocalDateTime whatsappSentAt;

    @Column(name = "final_approved_at")
    private LocalDateTime finalApprovedAt;

    @Column(name = "final_approved_by")
    private String finalApprovedBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    // -------- ENUMS ----------
    public enum CompletionStatus {
        PENDING, PARTIAL, COMPLETED
    }

    public enum TermsStatus {
        ACCEPT, REJECT
    }

    // -------- GETTERS / SETTERS --------

    public Long getFinalId() { return finalId; }
    public void setFinalId(Long finalId) { this.finalId = finalId; }

    public Long getVerificationId() { return verificationId; }
    public void setVerificationId(Long verificationId) { this.verificationId = verificationId; }

    public Long getDriverRegistrationId() { return driverRegistrationId; }
    public void setDriverRegistrationId(Long driverRegistrationId) { this.driverRegistrationId = driverRegistrationId; }

    public String getGdcRegistrationNumber() { return gdcRegistrationNumber; }
    public void setGdcRegistrationNumber(String gdcRegistrationNumber) { this.gdcRegistrationNumber = gdcRegistrationNumber; }

    public String getIdCardUrl() { return idCardUrl; }
    public void setIdCardUrl(String idCardUrl) { this.idCardUrl = idCardUrl; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public CompletionStatus getCompletionStatus() { return completionStatus; }
    public void setCompletionStatus(CompletionStatus completionStatus) { this.completionStatus = completionStatus; }

    public TermsStatus getTermsStatus() { return termsStatus; }
    public void setTermsStatus(TermsStatus termsStatus) { this.termsStatus = termsStatus; }

    public Boolean getWhatsappSent() { return whatsappSent; }
    public void setWhatsappSent(Boolean whatsappSent) { this.whatsappSent = whatsappSent; }

    public LocalDateTime getWhatsappSentAt() { return whatsappSentAt; }
    public void setWhatsappSentAt(LocalDateTime whatsappSentAt) { this.whatsappSentAt = whatsappSentAt; }

    public LocalDateTime getFinalApprovedAt() { return finalApprovedAt; }
    public void setFinalApprovedAt(LocalDateTime finalApprovedAt) { this.finalApprovedAt = finalApprovedAt; }

    public String getFinalApprovedBy() { return finalApprovedBy; }
    public void setFinalApprovedBy(String finalApprovedBy) { this.finalApprovedBy = finalApprovedBy; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
