package com.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_driver_final_submission")
public class DriverFinal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "final_id")
    private Long finalId;

    @Column(name = "driver_registration_id", nullable = false, unique = true)
    private Long driverRegistrationId;

    @Column(name = "gdc_registration_number", length = 30, unique = true)
    private String gdcRegistrationNumber;

    @Column(name = "id_card_url", length = 255)
    private String idCardUrl;

    @Column(name = "completion_status", columnDefinition = "ENUM('PENDING','PARTIAL','COMPLETED') DEFAULT 'PENDING'")
    private String completionStatus;

    @Column(name = "terms_status", columnDefinition = "ENUM('ACCEPT','REJECT') DEFAULT 'ACCEPT'")
    private String termsStatus;

    @Column(name = "final_approved_by", length = 100)
    private String finalApprovedBy;

    @Column(name = "final_approved_at")
    private LocalDateTime finalApprovedAt;

    @Column(name = "whatsapp_sent")
    private Boolean whatsappSent;

    @Column(name = "whatsapp_sent_at")
    private LocalDateTime whatsappSentAt;

    @Column(name = "remarks", length = 255)
    private String remarks;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // getters / setters
    public Long getFinalId() { return finalId; }
    public void setFinalId(Long finalId) { this.finalId = finalId; }

    public Long getDriverRegistrationId() { return driverRegistrationId; }
    public void setDriverRegistrationId(Long driverRegistrationId) { this.driverRegistrationId = driverRegistrationId; }

    public String getGdcRegistrationNumber() { return gdcRegistrationNumber; }
    public void setGdcRegistrationNumber(String gdcRegistrationNumber) { this.gdcRegistrationNumber = gdcRegistrationNumber; }

    public String getIdCardUrl() { return idCardUrl; }
    public void setIdCardUrl(String idCardUrl) { this.idCardUrl = idCardUrl; }

    public String getCompletionStatus() { return completionStatus; }
    public void setCompletionStatus(String completionStatus) { this.completionStatus = completionStatus; }

    public String getTermsStatus() { return termsStatus; }
    public void setTermsStatus(String termsStatus) { this.termsStatus = termsStatus; }

    public String getFinalApprovedBy() { return finalApprovedBy; }
    public void setFinalApprovedBy(String finalApprovedBy) { this.finalApprovedBy = finalApprovedBy; }

    public LocalDateTime getFinalApprovedAt() { return finalApprovedAt; }
    public void setFinalApprovedAt(LocalDateTime finalApprovedAt) { this.finalApprovedAt = finalApprovedAt; }

    public Boolean getWhatsappSent() { return whatsappSent; }
    public void setWhatsappSent(Boolean whatsappSent) { this.whatsappSent = whatsappSent; }

    public LocalDateTime getWhatsappSentAt() { return whatsappSentAt; }
    public void setWhatsappSentAt(LocalDateTime whatsappSentAt) { this.whatsappSentAt = whatsappSentAt; }

    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
