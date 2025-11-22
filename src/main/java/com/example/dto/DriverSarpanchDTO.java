package com.example.dto;

public class DriverSarpanchDTO {

    private Long sarpanchDetailsId;
    private Long driverRegistrationId;

    private String sarpanchName;
    private String sarpanchMobile;

    private String familyPerson1Name;
    private String familyPerson1Mobile;

    private String familyPerson2Name;
    private String familyPerson2Mobile;

    private String gdcRegistrationNumber;
    private String referenceDriverName;

    public DriverSarpanchDTO() {}

    // ---- Getters & Setters ----

    public Long getSarpanchDetailsId() {
        return sarpanchDetailsId;
    }

    public void setSarpanchDetailsId(Long sarpanchDetailsId) {
        this.sarpanchDetailsId = sarpanchDetailsId;
    }

    public Long getDriverRegistrationId() {
        return driverRegistrationId;
    }

    public void setDriverRegistrationId(Long driverRegistrationId) {
        this.driverRegistrationId = driverRegistrationId;
    }

    public String getSarpanchName() {
        return sarpanchName;
    }

    public void setSarpanchName(String sarpanchName) {
        this.sarpanchName = sarpanchName;
    }

    public String getSarpanchMobile() {
        return sarpanchMobile;
    }

    public void setSarpanchMobile(String sarpanchMobile) {
        this.sarpanchMobile = sarpanchMobile;
    }

    public String getFamilyPerson1Name() {
        return familyPerson1Name;
    }

    public void setFamilyPerson1Name(String familyPerson1Name) {
        this.familyPerson1Name = familyPerson1Name;
    }

    public String getFamilyPerson1Mobile() {
        return familyPerson1Mobile;
    }

    public void setFamilyPerson1Mobile(String familyPerson1Mobile) {
        this.familyPerson1Mobile = familyPerson1Mobile;
    }

    public String getFamilyPerson2Name() {
        return familyPerson2Name;
    }

    public void setFamilyPerson2Name(String familyPerson2Name) {
        this.familyPerson2Name = familyPerson2Name;
    }

    public String getFamilyPerson2Mobile() {
        return familyPerson2Mobile;
    }

    public void setFamilyPerson2Mobile(String familyPerson2Mobile) {
        this.familyPerson2Mobile = familyPerson2Mobile;
    }

    public String getGdcRegistrationNumber() {
        return gdcRegistrationNumber;
    }

    public void setGdcRegistrationNumber(String gdcRegistrationNumber) {
        this.gdcRegistrationNumber = gdcRegistrationNumber;
    }

    public String getReferenceDriverName() {
        return referenceDriverName;
    }

    public void setReferenceDriverName(String referenceDriverName) {
        this.referenceDriverName = referenceDriverName;
    }
}
