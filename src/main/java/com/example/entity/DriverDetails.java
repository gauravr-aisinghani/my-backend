package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_driver_details")
public class DriverDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_registration_id")
    private Long driverRegistrationId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Column(name = "mother_name", length = 100)
    private String motherName;

    @Enumerated(EnumType.STRING)
    @Column(name = "drink", length = 3)
    private DrinkOption drink = DrinkOption.NO;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    private Gender gender;

    // DOB stored as LocalDate for correctness
    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "age")
    private Integer age;

    @Column(name = "education", length = 100)
    private String education;

    @Column(name = "language_known", length = 100)
    private String languageKnown;

    @Column(name = "mobile_number", nullable = false, length = 10, unique = true)
    private String mobileNumber;

    @Column(name = "mobile_number_alt", length = 10)
    private String mobileNumberAlt;

    @Column(name = "blood_group", length = 5)
    private String bloodGroup;

    @Column(name = "aadhar_no", length = 12, unique = true, nullable = false)
    private String aadharNo;

    @Column(name = "bhamashah_no", length = 20)
    private String bhamashahNo;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "colony_house_no", length = 255)
    private String colonyHouseNo;

    @Column(name = "village", length = 100)
    private String village;

    @Column(name = "tehsil", length = 100)
    private String tehsil;

    @Column(name = "police_station", length = 100)
    private String policeStation;

    @Column(name = "district", length = 100)
    private String district;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "pincode", length = 6)
    private String pincode;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // PrePersist / PreUpdate to maintain timestamps
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ---------- ENUMS ----------
    public enum DrinkOption { YES, NO }
    public enum Gender { MALE, FEMALE, OTHER }

    // ---------- GETTERS + SETTERS ----------
    public Long getDriverRegistrationId() { return driverRegistrationId; }
    public void setDriverRegistrationId(Long driverRegistrationId) { this.driverRegistrationId = driverRegistrationId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }

    public DrinkOption getDrink() { return drink; }
    public void setDrink(DrinkOption drink) { this.drink = drink; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getLanguageKnown() { return languageKnown; }
    public void setLanguageKnown(String languageKnown) { this.languageKnown = languageKnown; }

    public String getMobileNumber() { return mobileNumber; }
    public void setMobileNumber(String mobileNumber) { this.mobileNumber = mobileNumber; }

    public String getMobileNumberAlt() { return mobileNumberAlt; }
    public void setMobileNumberAlt(String mobileNumberAlt) { this.mobileNumberAlt = mobileNumberAlt; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getAadharNo() { return aadharNo; }
    public void setAadharNo(String aadharNo) { this.aadharNo = aadharNo; }

    public String getBhamashahNo() { return bhamashahNo; }
    public void setBhamashahNo(String bhamashahNo) { this.bhamashahNo = bhamashahNo; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getColonyHouseNo() { return colonyHouseNo; }
    public void setColonyHouseNo(String colonyHouseNo) { this.colonyHouseNo = colonyHouseNo; }

    public String getVillage() { return village; }
    public void setVillage(String village) { this.village = village; }

    public String getTehsil() { return tehsil; }
    public void setTehsil(String tehsil) { this.tehsil = tehsil; }

    public String getPoliceStation() { return policeStation; }
    public void setPoliceStation(String policeStation) { this.policeStation = policeStation; }

    public String getDistrict() { return district; }
    public void setDistrict(String district) { this.district = district; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getPincode() { return pincode; }
    public void setPincode(String pincode) { this.pincode = pincode; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
