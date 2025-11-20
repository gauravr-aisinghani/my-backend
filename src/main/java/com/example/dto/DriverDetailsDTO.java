package com.example.dto;

import com.example.entity.DriverDetails;

public class DriverDetailsDTO {

    private String fullName;
    private String fatherName;
    private String motherName;
    private String drink;
    private String gender;
    private String dob;
    private String education;
    private String languageKnown;
    private String bloodGroup;
    private String aadharNo;
    private String bhamashahNo;
    private String category;
    private String mobile1;
    private String mobile2;
    private String addressLine;
    private String village;
    private String tehsil;
    private String policeStation;
    private String district;
    private String state;
    private String pinCode;

    public DriverDetailsDTO() {}

    // --- Convert DTO to Entity ---
    public DriverDetails toEntity() {
        DriverDetails driver = new DriverDetails();
        driver.setFullName(this.fullName);
        driver.setFatherName(this.fatherName);
        driver.setMotherName(this.motherName);
        driver.setDrink(DriverDetails.DrinkOption.valueOf(this.drink.toUpperCase()));
        driver.setGender(DriverDetails.Gender.valueOf(this.gender.toUpperCase()));
        driver.setDob(this.dob);
        driver.setEducation(this.education);
        driver.setLanguageKnown(this.languageKnown);
        driver.setBloodGroup(this.bloodGroup);
        driver.setAadharNo(this.aadharNo);
        driver.setBhamashahNo(this.bhamashahNo);
        driver.setCategory(this.category);
        driver.setMobile1(this.mobile1);
        driver.setMobile2(this.mobile2);
        driver.setAddressLine(this.addressLine);
        driver.setVillage(this.village);
        driver.setTehsil(this.tehsil);
        driver.setPoliceStation(this.policeStation);
        driver.setDistrict(this.district);
        driver.setState(this.state);
        driver.setPinCode(this.pinCode);
        return driver;
    }

    // --- Getters and Setters ---
    // (Add getters and setters for all fields)
}
