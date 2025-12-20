package com.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_transporter_documents")
public class TransporterDocuments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transporter_document_id")
    private Long transporterDocumentId;

    @Column(name = "transporter_registration_id", nullable = false, length = 36)
    private String transporterRegistrationId;

    @Column(name = "aadhar_original_photo_url", nullable = false, columnDefinition = "TEXT")
    private String aadharOriginalPhotoUrl;

    @Column(name = "pan_original_photo_url", nullable = false, columnDefinition = "TEXT")
    private String panOriginalPhotoUrl;

    @Column(name = "licence_original_photo_url", nullable = false, columnDefinition = "TEXT")
    private String licenceOriginalPhotoUrl;

    @Column(name = "gst_certificate_url", nullable = false, columnDefinition = "TEXT")
    private String gstCertificateUrl;

    @Column(name = "live_home_office_photo_url", nullable = false, columnDefinition = "TEXT")
    private String liveHomeOfficePhotoUrl;

    @Column(name = "stamp_letter_agreement_url", nullable = false, columnDefinition = "TEXT")
    private String stampLetterAgreementUrl;

    @Column(name = "transporter_account_passbook_url", nullable = false, columnDefinition = "TEXT")
    private String transporterAccountPassbookUrl;

    @Column(name = "transporter_auto_signature_url", nullable = false, columnDefinition = "TEXT")
    private String transporterAutoSignatureUrl;

    @Column(name = "transporter_selfie_live_location_url", nullable = false, columnDefinition = "TEXT")
    private String transporterSelfieLiveLocationUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Getters & Setters
    public Long getTransporterDocumentId() { return transporterDocumentId; }
    public void setTransporterDocumentId(Long transporterDocumentId) { this.transporterDocumentId = transporterDocumentId; }

    public String getTransporterRegistrationId() { return transporterRegistrationId; }
    public void setTransporterRegistrationId(String transporterRegistrationId) { this.transporterRegistrationId = transporterRegistrationId; }

    public String getAadharOriginalPhotoUrl() { return aadharOriginalPhotoUrl; }
    public void setAadharOriginalPhotoUrl(String aadharOriginalPhotoUrl) { this.aadharOriginalPhotoUrl = aadharOriginalPhotoUrl; }

    public String getPanOriginalPhotoUrl() { return panOriginalPhotoUrl; }
    public void setPanOriginalPhotoUrl(String panOriginalPhotoUrl) { this.panOriginalPhotoUrl = panOriginalPhotoUrl; }

    public String getLicenceOriginalPhotoUrl() { return licenceOriginalPhotoUrl; }
    public void setLicenceOriginalPhotoUrl(String licenceOriginalPhotoUrl) { this.licenceOriginalPhotoUrl = licenceOriginalPhotoUrl; }

    public String getGstCertificateUrl() { return gstCertificateUrl; }
    public void setGstCertificateUrl(String gstCertificateUrl) { this.gstCertificateUrl = gstCertificateUrl; }

    public String getLiveHomeOfficePhotoUrl() { return liveHomeOfficePhotoUrl; }
    public void setLiveHomeOfficePhotoUrl(String liveHomeOfficePhotoUrl) { this.liveHomeOfficePhotoUrl = liveHomeOfficePhotoUrl; }

    public String getStampLetterAgreementUrl() { return stampLetterAgreementUrl; }
    public void setStampLetterAgreementUrl(String stampLetterAgreementUrl) { this.stampLetterAgreementUrl = stampLetterAgreementUrl; }

    public String getTransporterAccountPassbookUrl() { return transporterAccountPassbookUrl; }
    public void setTransporterAccountPassbookUrl(String transporterAccountPassbookUrl) {
        this.transporterAccountPassbookUrl = transporterAccountPassbookUrl;
    }

    public String getTransporterAutoSignatureUrl() { return transporterAutoSignatureUrl; }
    public void setTransporterAutoSignatureUrl(String transporterAutoSignatureUrl) {
        this.transporterAutoSignatureUrl = transporterAutoSignatureUrl;
    }

    public String getTransporterSelfieLiveLocationUrl() { return transporterSelfieLiveLocationUrl; }
    public void setTransporterSelfieLiveLocationUrl(String transporterSelfieLiveLocationUrl) {
        this.transporterSelfieLiveLocationUrl = transporterSelfieLiveLocationUrl;
    }
}
