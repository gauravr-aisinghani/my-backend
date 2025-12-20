package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_transporter_vehicle_details")
public class TransporterVehicle {

    @Id
    @Column(name = "transporter_vehicle_id", length = 36, nullable = false)
    private String transporterVehicleId;

    @Column(name = "transporter_registration_id", nullable = false, length = 36)
    private String transporterRegistrationId;

    @Column(name = "total_gaadi", nullable = false)
    private Integer totalGaadi;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_of_vehicle", nullable = false)
    private PostOfVehicleEnum postOfVehicle;

    @Column(name = "payment_terms", nullable = false, length = 100)
    private String paymentTerms;

    @Column(name = "payment_30th_date", nullable = false, length = 50)
    private String payment30thDate;

    @Column(name = "other_known_transporter_in_wtl", length = 150)
    private String otherKnownTransporterInWtl;

    @Column(name = "mobile_number", length = 15)
    private String mobileNumber;

    @Column(name = "make", nullable = false, length = 100)
    private String make;

    @Enumerated(EnumType.STRING)
    @Column(name = "hire_payment", nullable = false)
    private HirePaymentEnum hirePayment;

    @Column(name = "gaadi_route_to", nullable = false, length = 100)
    private String gaadiRouteTo;

    @Column(name = "gaadi_route_from", nullable = false, length = 100)
    private String gaadiRouteFrom;

    @Column(name = "gaadi_number", nullable = false, length = 20)
    private String gaadiNumber;

    @Column(name = "gaadi_model_to")
    private Integer gaadiModelTo;

    @Column(name = "gaadi_model_from", nullable = false)
    private Integer gaadiModelFrom;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ===== GETTERS & SETTERS =====

    public String getTransporterVehicleId() {
        return transporterVehicleId;
    }

    public void setTransporterVehicleId(String transporterVehicleId) {
        this.transporterVehicleId = transporterVehicleId;
    }

    public String getTransporterRegistrationId() {
        return transporterRegistrationId;
    }

    public void setTransporterRegistrationId(String transporterRegistrationId) {
        this.transporterRegistrationId = transporterRegistrationId;
    }

    public Integer getTotalGaadi() {
        return totalGaadi;
    }

    public void setTotalGaadi(Integer totalGaadi) {
        this.totalGaadi = totalGaadi;
    }

    public PostOfVehicleEnum getPostOfVehicle() {
        return postOfVehicle;
    }

    public void setPostOfVehicle(PostOfVehicleEnum postOfVehicle) {
        this.postOfVehicle = postOfVehicle;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getPayment30thDate() {
        return payment30thDate;
    }

    public void setPayment30thDate(String payment30thDate) {
        this.payment30thDate = payment30thDate;
    }

    public String getOtherKnownTransporterInWtl() {
        return otherKnownTransporterInWtl;
    }

    public void setOtherKnownTransporterInWtl(String otherKnownTransporterInWtl) {
        this.otherKnownTransporterInWtl = otherKnownTransporterInWtl;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public HirePaymentEnum getHirePayment() {
        return hirePayment;
    }

    public void setHirePayment(HirePaymentEnum hirePayment) {
        this.hirePayment = hirePayment;
    }

    public String getGaadiRouteTo() {
        return gaadiRouteTo;
    }

    public void setGaadiRouteTo(String gaadiRouteTo) {
        this.gaadiRouteTo = gaadiRouteTo;
    }

    public String getGaadiRouteFrom() {
        return gaadiRouteFrom;
    }

    public void setGaadiRouteFrom(String gaadiRouteFrom) {
        this.gaadiRouteFrom = gaadiRouteFrom;
    }

    public String getGaadiNumber() {
        return gaadiNumber;
    }

    public void setGaadiNumber(String gaadiNumber) {
        this.gaadiNumber = gaadiNumber;
    }

    public Integer getGaadiModelTo() {
        return gaadiModelTo;
    }

    public void setGaadiModelTo(Integer gaadiModelTo) {
        this.gaadiModelTo = gaadiModelTo;
    }

    public Integer getGaadiModelFrom() {
        return gaadiModelFrom;
    }

    public void setGaadiModelFrom(Integer gaadiModelFrom) {
        this.gaadiModelFrom = gaadiModelFrom;
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
