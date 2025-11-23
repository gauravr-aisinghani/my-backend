package com.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_sarpanch_details")
public class DriverSarpanchDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sarpanch_details_id")
    private Long sarpanchDetailsId;

    @Column(name = "driver_registration_id", nullable = false)
    private Long driverRegistrationId;

    @Column(name = "sarpanch_name", nullable = false, length = 150)
    private String sarpanchName;

    @Column(name = "sarpanch_mobile", nullable = false, length = 15)
    private String sarpanchMobile;

    @Column(name = "family_person_1_name", length = 150)
    private String familyPerson1Name;

    @Column(name = "family_person_1_mobile", length = 15)
    private String familyPerson1Mobile;

    @Column(name = "family_person_2_name", length = 150)
    private String familyPerson2Name;

    @Column(name = "family_person_2_mobile", length = 15)
    private String familyPerson2Mobile;

    @Column(name = "gdc_registration_number", length = 50)
    private String gdcRegistrationNumber;

    @Column(name = "reference_driver_name", length = 150)
    private String referenceDriverName;

    // ⭐ FIXED TIMESTAMPS ⭐
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public DriverSarpanchDetails() {}

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
