package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver_details")
public class DriverDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long driverId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "father_name", length = 100)
    private String fatherName;

    @Column(name = "mother_name", length = 100)
    private String motherName;

    @Enumerated(EnumType.STRING)
    @Column(length = 3)
    private DrinkOption drink = DrinkOption.NO; // Enum YES / NO

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    @Column(name = "dob")
    private String dob; // Can be LocalDate if needed

    @Column(length = 100)
    private String education;

    @Column(name = "language_known", length = 100)
    private String languageKnown;

    @Column(name = "blood_group", length = 5)
    private String bloodGroup;

    @Column(name = "aadhar_no", length = 12, unique = true)
    private String aadharNo;

    @Column(name = "bhamashah_no", length = 20)
    private String bhamashahNo;

    @Column(length = 50)
    private String category;

    @Column(name = "mobile1", length = 10, unique = true, nullable = false)
    private String mobile1;

    @Column(name = "mobile2", length = 10)
    private String mobile2;

    @Column(name = "address_line", length = 255)
    private String addressLine;

    @Column(length = 100)
    private String village;

    @Column(length = 100)
    private String tehsil;

    @Column(name = "police_station", length = 100)
    private String policeStation;

    @Column(length = 100)
    private String district;

    @Column(length = 50)
    private String state;

    @Column(name = "pin_code", length = 6)
    private String pinCode;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // --- Constructors ---

    public DriverDetails() {}

    public DriverDetails(String fullName, String fatherName, String motherName, DrinkOption drink, Gender gender,
                         String dob, String education, String languageKnown, String bloodGroup, String aadharNo,
                         String bhamashahNo, String category, String mobile1, String mobile2, String addressLine,
                         String village, String tehsil, String policeStation, String district, String state,
                         String pinCode) {
        this.fullName = fullName;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.drink = drink;
        this.gender = gender;
        this.dob = dob;
        this.education = education;
        this.languageKnown = languageKnown;
        this.bloodGroup = bloodGroup;
        this.aadharNo = aadharNo;
        this.bhamashahNo = bhamashahNo;
        this.category = category;
        this.mobile1 = mobile1;
        this.mobile2 = mobile2;
        this.addressLine = addressLine;
        this.village = village;
        this.tehsil = tehsil;
        this.policeStation = policeStation;
        this.district = district;
        this.state = state;
        this.pinCode = pinCode;
    }

    // --- Getters and Setters ---

    public Long getDriverId() { return driverId; }
    public void setDriverId(Long driverId) { this.driverId = driverId; }

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

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getEducation() { return education; }
    public void setEducation(String education) { this.education = education; }

    public String getLanguageKnown() { return languageKnown; }
    public void setLanguageKnown(String languageKnown) { this.languageKnown = languageKnown; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getAadharNo() { return aadharNo; }
    public void setAadharNo(String aadharNo) { this.aadharNo = aadharNo; }

    public String getBhamashahNo() { return bhamashahNo; }
    public void setBhamashahNo(String bhamashahNo) { this.bhamashahNo = bhamashahNo; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getMobile1() { return mobile1; }
    public void setMobile1(String mobile1) { this.mobile1 = mobile1; }

    public String getMobile2() { return mobile2; }
    public void setMobile2(String mobile2) { this.mobile2 = mobile2; }

    public String getAddressLine() { return addressLine; }
    public void setAddressLine(String addressLine) { this.addressLine = addressLine; }

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

    public String getPinCode() { return pinCode; }
    public void setPinCode(String pinCode) { this.pinCode = pinCode; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // --- Enums ---
    public enum DrinkOption { YES, NO }
    public enum Gender { MALE, FEMALE, OTHER }
}
