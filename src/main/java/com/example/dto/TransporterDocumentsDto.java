package com.example.dto;

public class TransporterDocumentsDto {

    private Long transporterDocumentId;
    private String transporterRegistrationId;

    private String aadharOriginalPhotoUrl;
    private String panOriginalPhotoUrl;
    private String licenceOriginalPhotoUrl;
    private String gstCertificateUrl;
    private String liveHomeOfficePhotoUrl;
    private String stampLetterAgreementUrl;
    private String transporterAccountPassbookUrl;
    private String transporterAutoSignatureUrl;
    private String transporterSelfieLiveLocationUrl;

    // Getters & Setters
    public Long getTransporterDocumentId() { return transporterDocumentId; }
    public void setTransporterDocumentId(Long transporterDocumentId) { this.transporterDocumentId = transporterDocumentId; }

    public String getTransporterRegistrationId() { return transporterRegistrationId; }
    public void setTransporterRegistrationId(String transporterRegistrationId) {
        this.transporterRegistrationId = transporterRegistrationId;
    }

    public String getAadharOriginalPhotoUrl() { return aadharOriginalPhotoUrl; }
    public void setAadharOriginalPhotoUrl(String aadharOriginalPhotoUrl) {
        this.aadharOriginalPhotoUrl = aadharOriginalPhotoUrl;
    }

    public String getPanOriginalPhotoUrl() { return panOriginalPhotoUrl; }
    public void setPanOriginalPhotoUrl(String panOriginalPhotoUrl) {
        this.panOriginalPhotoUrl = panOriginalPhotoUrl;
    }

    public String getLicenceOriginalPhotoUrl() { return licenceOriginalPhotoUrl; }
    public void setLicenceOriginalPhotoUrl(String licenceOriginalPhotoUrl) {
        this.licenceOriginalPhotoUrl = licenceOriginalPhotoUrl;
    }

    public String getGstCertificateUrl() { return gstCertificateUrl; }
    public void setGstCertificateUrl(String gstCertificateUrl) {
        this.gstCertificateUrl = gstCertificateUrl;
    }

    public String getLiveHomeOfficePhotoUrl() { return liveHomeOfficePhotoUrl; }
    public void setLiveHomeOfficePhotoUrl(String liveHomeOfficePhotoUrl) {
        this.liveHomeOfficePhotoUrl = liveHomeOfficePhotoUrl;
    }

    public String getStampLetterAgreementUrl() { return stampLetterAgreementUrl; }
    public void setStampLetterAgreementUrl(String stampLetterAgreementUrl) {
        this.stampLetterAgreementUrl = stampLetterAgreementUrl;
    }

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
