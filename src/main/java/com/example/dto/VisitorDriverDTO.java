package com.example.dto;

import java.time.LocalDateTime;

public class VisitorDriverDTO {

    private Long visitorDriverId;
    private String driverName;
    private String location;
    private String mobileNo;
    private String grade;
    private String otherMobile;
    private String birthPlace;
    private String relativeName;
    private String relativeMobile;
    private String gaadiDrivenInPast;
    private Boolean underload;
    private Boolean overload;
    private String preferedLocation;
    private String preferedMonthlySalary;
    private String regularTiming;
    private Boolean occasional;
    private Boolean permanent;
    private String anyIssue;
    private String notes;

    // ⭐ NEW FIELD ⭐
    private String preferredVehicle;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ---- GETTERS & SETTERS ----

    public Long getVisitorDriverId() { return visitorDriverId; }
    public void setVisitorDriverId(Long visitorDriverId) { this.visitorDriverId = visitorDriverId; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getOtherMobile() { return otherMobile; }
    public void setOtherMobile(String otherMobile) { this.otherMobile = otherMobile; }

    public String getBirthPlace() { return birthPlace; }
    public void setBirthPlace(String birthPlace) { this.birthPlace = birthPlace; }

    public String getRelativeName() { return relativeName; }
    public void setRelativeName(String relativeName) { this.relativeName = relativeName; }

    public String getRelativeMobile() { return relativeMobile; }
    public void setRelativeMobile(String relativeMobile) { this.relativeMobile = relativeMobile; }

    public String getGaadiDrivenInPast() { return gaadiDrivenInPast; }
    public void setGaadiDrivenInPast(String gaadiDrivenInPast) { this.gaadiDrivenInPast = gaadiDrivenInPast; }

    public Boolean getUnderload() { return underload; }
    public void setUnderload(Boolean underload) { this.underload = underload; }

    public Boolean getOverload() { return overload; }
    public void setOverload(Boolean overload) { this.overload = overload; }

    public String getPreferedLocation() { return preferedLocation; }
    public void setPreferedLocation(String preferedLocation) { this.preferedLocation = preferedLocation; }

    public String getPreferedMonthlySalary() { return preferedMonthlySalary; }
    public void setPreferedMonthlySalary(String preferedMonthlySalary) { this.preferedMonthlySalary = preferedMonthlySalary; }

    public String getRegularTiming() { return regularTiming; }
    public void setRegularTiming(String regularTiming) { this.regularTiming = regularTiming; }

    public Boolean getOccasional() { return occasional; }
    public void setOccasional(Boolean occasional) { this.occasional = occasional; }

    public Boolean getPermanent() { return permanent; }
    public void setPermanent(Boolean permanent) { this.permanent = permanent; }

    public String getAnyIssue() { return anyIssue; }
    public void setAnyIssue(String anyIssue) { this.anyIssue = anyIssue; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getPreferredVehicle() { return preferredVehicle; }
    public void setPreferredVehicle(String preferredVehicle) { this.preferredVehicle = preferredVehicle; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
