package com.example.dto;

public class DriverSummaryDto {

    private Long visitors;
    private Long selectedVisitors;
    private Long registeredDrivers;
    private Long documentsUploaded;
    private Long verificationPending;
    private Long verifiedDrivers;
    private Long gdcGenerated;

    public Long getVisitors() {
        return visitors;
    }

    public void setVisitors(Long visitors) {
        this.visitors = visitors;
    }

    public Long getSelectedVisitors() {
        return selectedVisitors;
    }

    public void setSelectedVisitors(Long selectedVisitors) {
        this.selectedVisitors = selectedVisitors;
    }

    public Long getRegisteredDrivers() {
        return registeredDrivers;
    }

    public void setRegisteredDrivers(Long registeredDrivers) {
        this.registeredDrivers = registeredDrivers;
    }

    public Long getDocumentsUploaded() {
        return documentsUploaded;
    }

    public void setDocumentsUploaded(Long documentsUploaded) {
        this.documentsUploaded = documentsUploaded;
    }

    public Long getVerificationPending() {
        return verificationPending;
    }

    public void setVerificationPending(Long verificationPending) {
        this.verificationPending = verificationPending;
    }

    public Long getVerifiedDrivers() {
        return verifiedDrivers;
    }

    public void setVerifiedDrivers(Long verifiedDrivers) {
        this.verifiedDrivers = verifiedDrivers;
    }

    public Long getGdcGenerated() {
        return gdcGenerated;
    }

    public void setGdcGenerated(Long gdcGenerated) {
        this.gdcGenerated = gdcGenerated;
    }
}
