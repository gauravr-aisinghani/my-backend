package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_visitor_driver")
public class VisitorDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visitor_driver_id")
    private Long visitorDriverId;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @Column(name = "grade", nullable = false)
    private String grade;

    @Column(name = "other_mobile")
    private String otherMobile;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "relative_name")
    private String relativeName;

    @Column(name = "relative_mobile")
    private String relativeMobile;

    @Column(name = "gaadi_driven_in_past", nullable = false)
    private String gaadiDrivenInPast;

    @Column(name = "underload", nullable = false)
    private Boolean underload;

    @Column(name = "overload", nullable = false)
    private Boolean overload;

    @Column(name = "prefered_location")
    private String preferedLocation;

    @Column(name = "prefered_monthly_salary")
    private String preferedMonthlySalary;

    @Column(name = "regular_timing")
    private String regularTiming;

    @Column(name = "occasional")
    private Boolean occasional;

    @Column(name = "permanent")
    private Boolean permanent;

    @Column(name = "any_issue")
    private String anyIssue;

    @Column(name = "notes")
    private String notes;

    // ⭐ NEW FIELD ⭐
    @Column(name = "preferred_vehicle")
    private String preferredVehicle;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    // ===============================
    // GETTERS & SETTERS
    // ===============================

    public Long getVisitorDriverId() {
        return visitorDriverId;
    }

    public void setVisitorDriverId(Long visitorDriverId) {
        this.visitorDriverId = visitorDriverId;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getOtherMobile() {
        return otherMobile;
    }

    public void setOtherMobile(String otherMobile) {
        this.otherMobile = otherMobile;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
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

    public String getGaadiDrivenInPast() {
        return gaadiDrivenInPast;
    }

    public void setGaadiDrivenInPast(String gaadiDrivenInPast) {
        this.gaadiDrivenInPast = gaadiDrivenInPast;
    }

    public Boolean getUnderload() {
        return underload;
    }

    public void setUnderload(Boolean underload) {
        this.underload = underload;
    }

    public Boolean getOverload() {
        return overload;
    }

    public void setOverload(Boolean overload) {
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

    public Boolean getOccasional() {
        return occasional;
    }

    public void setOccasional(Boolean occasional) {
        this.occasional = occasional;
    }

    public Boolean getPermanent() {
        return permanent;
    }

    public void setPermanent(Boolean permanent) {
        this.permanent = permanent;
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

    public String getPreferredVehicle() {
        return preferredVehicle;
    }

    public void setPreferredVehicle(String preferredVehicle) {
        this.preferredVehicle = preferredVehicle;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
