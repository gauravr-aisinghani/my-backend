package com.example.dto;

import com.example.entity.DriverDetails;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public class DriverDetailsDTO {

    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("father_name")
    private String fatherName;

    @JsonProperty("mother_name")
    private String motherName;

    private String drink;
    private String gender;

    @JsonProperty("dob")
    private String dob;

    private Integer age;
    private String education;

    @JsonProperty("language_known")
    private String languageKnown;

    @JsonProperty("mobile_number")
    private String mobileNumber;

    @JsonProperty("mobile_number_alt")
    private String mobileNumberAlt;

    @JsonProperty("blood_group")
    private String bloodGroup;

    @JsonProperty("aadhar_no")
    private String aadharNo;

    @JsonProperty("bhamashah_no")
    private String bhamashahNo;

    private String category;

    @JsonProperty("colony_house_no")
    private String colonyHouseNo;

    private String village;
    private String tehsil;

    @JsonProperty("police_station")
    private String policeStation;

    private String district;
    private String state;
    private String pincode;


    // ------------------- toEntity() -------------------
    public DriverDetails toEntity() {

        DriverDetails d = new DriverDetails();

        d.setFullName(fullName);
        d.setFatherName(fatherName);
        d.setMotherName(motherName);

        if (drink != null) {
            try { d.setDrink(DriverDetails.DrinkOption.valueOf(drink.toUpperCase())); }
            catch (Exception e) { d.setDrink(DriverDetails.DrinkOption.NO); }
        }

        if (gender != null) {
            try { d.setGender(DriverDetails.Gender.valueOf(gender.toUpperCase())); }
            catch (Exception e) { d.setGender(DriverDetails.Gender.OTHER); }
        }

        if (dob != null && !dob.isBlank()) {
            d.setDob(LocalDate.parse(dob));
        }

        d.setAge(age);
        d.setEducation(education);
        d.setLanguageKnown(languageKnown);
        d.setMobileNumber(mobileNumber);
        d.setMobileNumberAlt(mobileNumberAlt);
        d.setBloodGroup(bloodGroup);
        d.setAadharNo(aadharNo);
        d.setBhamashahNo(bhamashahNo);
        d.setCategory(category);
        d.setColonyHouseNo(colonyHouseNo);
        d.setVillage(village);
        d.setTehsil(tehsil);
        d.setPoliceStation(policeStation);
        d.setDistrict(district);
        d.setState(state);
        d.setPincode(pincode);

        return d;
    }

    // ---------- GETTERS & SETTERS ----------
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getFatherName() { return fatherName; }
    public void setFatherName(String fatherName) { this.fatherName = fatherName; }

    public String getMotherName() { return motherName; }
    public void setMotherName(String motherName) { this.motherName = motherName; }

    public String getDrink() { return drink; }
    public void setDrink(String drink) { this.drink = drink; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

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
}
