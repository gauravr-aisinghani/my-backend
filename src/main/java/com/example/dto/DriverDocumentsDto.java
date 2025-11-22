package com.example.dto;

public class DriverDocumentsDto {

    private Long driverDocumentId;
    private Long driverRegistrationId;

    private String driverSelfie;
    private String homePhoto;
    private String sarpanchLetter;
    private String bankAccountDetails;
    private String passbookPhoto;
    private String aadharPhoto;
    private String panPhoto;
    private String licencePhoto;
    private String paymentProofUpi;
    private String driverSignature;

    public DriverDocumentsDto() {}

    // Getters & Setters
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
}
