package com.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_driver_documents")
public class DriverDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_document_id")
    private Long driverDocumentId;

    @Column(name = "driver_registration_id", nullable = false)
    private Long driverRegistrationId;

    @Column(name = "driver_selfie", nullable = false, length = 255)
    private String driverSelfie;

    @Column(name = "home_photo", nullable = false, length = 255)
    private String homePhoto;

    @Column(name = "sarpanch_letter", nullable = false, length = 255)
    private String sarpanchLetter;

    @Column(name = "bank_account_details", nullable = false, length = 255)
    private String bankAccountDetails;

    @Column(name = "passbook_photo", nullable = false, length = 255)
    private String passbookPhoto;

    @Column(name = "aadhar_photo", nullable = false, length = 255)
    private String aadharPhoto;

    @Column(name = "pan_photo", nullable = false, length = 255)
    private String panPhoto;

    @Column(name = "licence_photo", nullable = false, length = 255)
    private String licencePhoto;

    @Column(name = "payment_proof_upi", nullable = false, length = 255)
    private String paymentProofUpi;

    @Column(name = "driver_signature", nullable = false, length = 255)
    private String driverSignature;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public DriverDocuments() { }

    // Getters and Setters
    public Long getDriverDocumentId() { return driverDocumentId; }
    public void setDriverDocumentId(Long driverDocumentId) { this.driverDocumentId = driverDocumentId; }

    public Long getDriverRegistrationId() { return driverRegistrationId; }
    public void setDriverRegistrationId(Long driverRegistrationId) { this.driverRegistrationId = driverRegistrationId; }

    public String getDriverSelfie() { return driverSelfie; }
    public void setDriverSelfie(String driverSelfie) { this.driverSelfie = driverSelfie; }

    public String getHomePhoto() { return homePhoto; }
    public void setHomePhoto(String homePhoto) { this.homePhoto = homePhoto; }

    public String getSarpanchLetter() { return sarpanchLetter; }
    public void setSarpanchLetter(String sarpanchLetter) { this.sarpanchLetter = sarpanchLetter; }

    public String getBankAccountDetails() { return bankAccountDetails; }
    public void setBankAccountDetails(String bankAccountDetails) { this.bankAccountDetails = bankAccountDetails; }

    public String getPassbookPhoto() { return passbookPhoto; }
    public void setPassbookPhoto(String passbookPhoto) { this.passbookPhoto = passbookPhoto; }

    public String getAadharPhoto() { return aadharPhoto; }
    public void setAadharPhoto(String aadharPhoto) { this.aadharPhoto = aadharPhoto; }

    public String getPanPhoto() { return panPhoto; }
    public void setPanPhoto(String panPhoto) { this.panPhoto = panPhoto; }

    public String getLicencePhoto() { return licencePhoto; }
    public void setLicencePhoto(String licencePhoto) { this.licencePhoto = licencePhoto; }

    public String getPaymentProofUpi() { return paymentProofUpi; }
    public void setPaymentProofUpi(String paymentProofUpi) { this.paymentProofUpi = paymentProofUpi; }

    public String getDriverSignature() { return driverSignature; }
    public void setDriverSignature(String driverSignature) { this.driverSignature = driverSignature; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
