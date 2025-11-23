package com.example.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_last_experience")
public class DriverExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_experience_id")
    private Long driverExperienceId;

    @Column(name = "driver_registration_id", nullable = false)
    private Long driverRegistrationId;

    @Column(name = "vehicle_make", length = 150)
    private String vehicleMake;

    @Column(name = "vehicle_model", length = 150)
    private String vehicleModel;

    @Column(name = "last_transport_name", length = 200)
    private String lastTransportName;

    @Column(name = "owner_name", length = 150)
    private String ownerName;

    @Column(name = "gaadi_number", length = 50)
    private String gaadiNumber;

    @Column(name = "transport_address", length = 500)
    private String transportAddress;

    @Column(name = "owner_contact_no", length = 20)
    private String ownerContactNo;

    @Column(name = "total_work_on_vehicle")
    private Integer totalWorkOnVehicle;

    @Column(name = "total_experience_years")
    private Integer totalExperienceYears;

    @Column(name = "leaving_reason", length = 500)
    private String leavingReason;

    @Column(name = "post_of_driving", length = 150)
    private String postOfDriving;

    @Column(name = "from_date")
    private LocalDate fromDate;

    @Column(name = "to_date")
    private LocalDate toDate;

    // ⭐ FIXED TIMESTAMPS ⭐
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public DriverExperience() {}

    // ---- Getters & Setters ----
    public Long getDriverExperienceId() {
        return driverExperienceId;
    }

    public void setDriverExperienceId(Long driverExperienceId) {
        this.driverExperienceId = driverExperienceId;
    }

    public Long getDriverRegistrationId() {
        return driverRegistrationId;
    }

    public void setDriverRegistrationId(Long driverRegistrationId) {
        this.driverRegistrationId = driverRegistrationId;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getLastTransportName() {
        return lastTransportName;
    }

    public void setLastTransportName(String lastTransportName) {
        this.lastTransportName = lastTransportName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getGaadiNumber() {
        return gaadiNumber;
    }

    public void setGaadiNumber(String gaadiNumber) {
        this.gaadiNumber = gaadiNumber;
    }

    public String getTransportAddress() {
        return transportAddress;
    }

    public void setTransportAddress(String transportAddress) {
        this.transportAddress = transportAddress;
    }

    public String getOwnerContactNo() {
        return ownerContactNo;
    }

    public void setOwnerContactNo(String ownerContactNo) {
        this.ownerContactNo = ownerContactNo;
    }

    public Integer getTotalWorkOnVehicle() {
        return totalWorkOnVehicle;
    }

    public void setTotalWorkOnVehicle(Integer totalWorkOnVehicle) {
        this.totalWorkOnVehicle = totalWorkOnVehicle;
    }

    public Integer getTotalExperienceYears() {
        return totalExperienceYears;
    }

    public void setTotalExperienceYears(Integer totalExperienceYears) {
        this.totalExperienceYears = totalExperienceYears;
    }

    public String getLeavingReason() {
        return leavingReason;
    }

    public void setLeavingReason(String leavingReason) {
        this.leavingReason = leavingReason;
    }

    public String getPostOfDriving() {
        return postOfDriving;
    }

    public void setPostOfDriving(String postOfDriving) {
        this.postOfDriving = postOfDriving;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
