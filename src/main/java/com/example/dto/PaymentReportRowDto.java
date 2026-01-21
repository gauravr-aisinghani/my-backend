package com.example.dto;

public class PaymentReportRowDto {

    private Long id;
    private String gdcNumber;
    private String paymentType;
    private Double amount;
    private String status;
    private String razorpayPaymentId;
    private String createdAt;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGdcNumber() {
		return gdcNumber;
	}
	public void setGdcNumber(String gdcNumber) {
		this.gdcNumber = gdcNumber;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRazorpayPaymentId() {
		return razorpayPaymentId;
	}
	public void setRazorpayPaymentId(String razorpayPaymentId) {
		this.razorpayPaymentId = razorpayPaymentId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

    // GETTERS & SETTERS (Generate)
    
}
