package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class DriverExperienceDTO {

    private Long driverExperienceId;

    @NotNull(message = "driver_registration_id is required")
    @JsonProperty("driver_registration_id")
    private Long driverRegistrationId;

    @Size(max = 150)
    @JsonProperty("vehicle_make")
    private String vehicleMake;

    @Size(max = 150)
    @JsonProperty("vehicle_model")
    private String vehicleModel;

    @Size(max = 200)
    @JsonProperty("last_transport_name")
    private String lastTransportName;

    @Size(max = 150)
    @JsonProperty("owner_name")
    private String ownerName;

    @Size(max = 50)
    @JsonProperty("gaadi_number")
    private String gaadiNumber;

    @Size(max = 500)
    @JsonProperty("transport_address")
    private String transportAddress;

    @Size(max = 20)
    @JsonProperty("owner_contact_no")
    private String ownerContactNo;

    @JsonProperty("total_work_on_vehicle")
    private Integer totalWorkOnVehicle;

    @JsonProperty("total_experience_years")
    private Integer totalExperienceYears;

    @Size(max = 500)
    @JsonProperty("leaving_reason")
    private String leavingReason;

    @Size(max = 150)
    @JsonProperty("post_of_driving")
    private String postOfDriving;

    @JsonProperty("from_date")
    private LocalDate fromDate;

    @JsonProperty("to_date")
    private LocalDate toDate;

    public DriverExperienceDTO() {}

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
}
