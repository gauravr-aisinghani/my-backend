package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "visitor_driver")
public class VisitorDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_driver_id")
    private Long visitorDriverId;

    private String date;
    private String time;
    private String driverName;
    private String location;
    private String mobileNo;
    private String otherMobile;
    private String relativeName;
    private String relativeMobile;

    private String gaadi;
    private String underload;
    private String overload;

    private String preferedLocation;
    private String preferedMonthlySalary;
    private String regularTiming;
    private String leaveTime;
    private String anyIssue;

    @Column(columnDefinition = "TEXT")
    private String notes;

    // EXTRA COLUMNS FROM SELECTED DRIVER TABLE
    private String birthPlace;
    private String grade;
    private String vehicle;
    private String assignFor;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // -------------------------
    // Constructors
    // -------------------------
    public VisitorDriver() {}

    // -------------------------
    // GETTERS & SETTERS
    // -------------------------

    public Long getVisitorDriverId() {
        return visitorDriverId;
    }

    public void setVisitorDriverId(Long visitorDriverId) {
        this.visitorDriverId = visitorDriverId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getOtherMobile() {
        return otherMobile;
    }

    public void setOtherMobile(String otherMobile) {
        this.otherMobile = otherMobile;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public void setRelativeName(String relativeName) {
        this.relativeName = relativeName;
    }

    public String getRelativeMobile() {
        return relativeMobile;
    }

    public void setRelativeMobile(String relativeMobile) {
        this.relativeMobile = relativeMobile;
    }

    public String getGaadi() {
        return gaadi;
    }

    public void setGaadi(String gaadi) {
        this.gaadi = gaadi;
    }

    public String getUnderload() {
        return underload;
    }

    public void setUnderload(String underload) {
        this.underload = underload;
    }

    public String getOverload() {
        return overload;
    }

    public void setOverload(String overload) {
        this.overload = overload;
    }

    public String getPreferedLocation() {
        return preferedLocation;
    }

    public void setPreferedLocation(String preferedLocation) {
        this.preferedLocation = preferedLocation;
    }

    public String getPreferedMonthlySalary() {
        return preferedMonthlySalary;
    }

    public void setPreferedMonthlySalary(String preferedMonthlySalary) {
        this.preferedMonthlySalary = preferedMonthlySalary;
    }

    public String getRegularTiming() {
        return regularTiming;
    }

    public void setRegularTiming(String regularTiming) {
        this.regularTiming = regularTiming;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getAnyIssue() {
        return anyIssue;
    }

    public void setAnyIssue(String anyIssue) {
        this.anyIssue = anyIssue;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getAssignFor() {
        return assignFor;
    }

    public void setAssignFor(String assignFor) {
        this.assignFor = assignFor;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
