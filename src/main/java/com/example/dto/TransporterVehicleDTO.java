package com.example.dto;

import com.example.entity.HirePaymentEnum;
import com.example.entity.PostOfVehicleEnum;

public class TransporterVehicleDTO {

    private String transporterRegistrationId; // from frontend
    private Integer totalGaadi;
    private PostOfVehicleEnum postOfVehicle;
    private String paymentTerms;
    private String payment30thDate;
    private String otherKnownTransporterInWtl;
    private String mobileNumber;
    private String make;
    private HirePaymentEnum hirePayment;
    private String gaadiRouteTo;
    private String gaadiRouteFrom;
    private String gaadiNumber;
    private Integer gaadiModelTo;
    private Integer gaadiModelFrom;
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

    // getters & setters
    
    
}
