package com.example.dto;

import java.util.UUID;

/**
 * DTO for Transporter Driver Request creation
 */
public class TransporterDriverRequestDTO {

    private UUID transporterRegistrationId;
    private String transporterPhone;
    private String gdcNumber;
    private String vehicleNumber;
    private String route;
    private Double monthlySalary;
    private String remarks;

    public UUID getTransporterRegistrationId() {
        return transporterRegistrationId;
    }

    public void setTransporterRegistrationId(UUID transporterRegistrationId) {
        this.transporterRegistrationId = transporterRegistrationId;
    }

    public String getTransporterPhone() {
        return transporterPhone;
    }

    public void setTransporterPhone(String transporterPhone) {
        this.transporterPhone = transporterPhone;
    }

    public String getGdcNumber() {
        return gdcNumber;
    }

    public void setGdcNumber(String gdcNumber) {
        this.gdcNumber = gdcNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Double getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(Double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
