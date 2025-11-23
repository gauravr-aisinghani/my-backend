package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "yfs_selected_driver")
public class SelectedDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "selected_driver_id")
    private Long selectedDriverId;

    @Column(name = "visitor_driver_id", nullable = false)
    private Long visitorDriverId;

    @Column(name = "driver_name", nullable = false)
    private String driverName;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "mobile_no", nullable = false)
    private String mobileNo;

    @Column(name = "grade", nullable = false)
    private String grade;

    @Column(name = "preferred_vehicle")
    private String preferredVehicle;

    // NEW REQUIRED FIELDS (DB MATCH)
    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    // optional fields
    @Column(name = "approve_for")
    private String approveFor;

    @Column(name = "assign")
    private String assign;

    @Column(name = "approved_for_assign")
    private String approvedForAssign;

    @Column(name = "assigned_status")
    private String assignedStatus;

    @Column(name = "selection_notes")
    private String selectionNotes;

    @Column(name = "selected_date")
    private LocalDateTime selectedDate;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // -------------------- GETTERS & SETTERS --------------------

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

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getApproveFor() { return approveFor; }
    public void setApproveFor(String approveFor) { this.approveFor = approveFor; }

    public String getAssign() { return assign; }
    public void setAssign(String assign) { this.assign = assign; }

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
