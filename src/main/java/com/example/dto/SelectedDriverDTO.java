package com.example.dto;

import java.time.LocalDateTime;

public class SelectedDriverDTO {

    private Long selectedDriverId;
    private Long visitorDriverId;
    private String driverName;
    private String birthPlace;
    private String mobileNo;
    private String grade;
    private String preferredVehicle;
    private String approvedForAssign;
    private String assignedStatus;
    private String selectionNotes;
    private LocalDateTime selectedDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // -------- GETTERS & SETTERS ----------

    public Long getSelectedDriverId() { return selectedDriverId; }
    public void setSelectedDriverId(Long selectedDriverId) { this.selectedDriverId = selectedDriverId; }

    public Long getVisitorDriverId() { return visitorDriverId; }
    public void setVisitorDriverId(Long visitorDriverId) { this.visitorDriverId = visitorDriverId; }

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getBirthPlace() { return birthPlace; }
    public void setBirthPlace(String birthPlace) { this.birthPlace = birthPlace; }

    public String getMobileNo() { return mobileNo; }
    public void setMobileNo(String mobileNo) { this.mobileNo = mobileNo; }

    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    public String getPreferredVehicle() { return preferredVehicle; }
    public void setPreferredVehicle(String preferredVehicle) { this.preferredVehicle = preferredVehicle; }

    public String getApprovedForAssign() { return approvedForAssign; }
    public void setApprovedForAssign(String approvedForAssign) { this.approvedForAssign = approvedForAssign; }

    public String getAssignedStatus() { return assignedStatus; }
    public void setAssignedStatus(String assignedStatus) { this.assignedStatus = assignedStatus; }

    public String getSelectionNotes() { return selectionNotes; }
    public void setSelectionNotes(String selectionNotes) { this.selectionNotes = selectionNotes; }

    public LocalDateTime getSelectedDate() { return selectedDate; }
    public void setSelectedDate(LocalDateTime selectedDate) { this.selectedDate = selectedDate; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
