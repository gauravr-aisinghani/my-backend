package com.example.dto;

import com.example.entity.YfsDriverPayment.PaymentStatus;

public class YfsDriverPaymentDTO {

    private Long paymentId;
    private Long driverRegistrationId;
    private String gdcRegistrationNumber;
    private Double amount;
    private PaymentStatus status;
    private String transactionId;
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	public Long getDriverRegistrationId() {
		return driverRegistrationId;
	}
	public void setDriverRegistrationId(Long driverRegistrationId) {
		this.driverRegistrationId = driverRegistrationId;
	}
	public String getGdcRegistrationNumber() {
		return gdcRegistrationNumber;
	}
	public void setGdcRegistrationNumber(String gdcRegistrationNumber) {
		this.gdcRegistrationNumber = gdcRegistrationNumber;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

    // Getters and Setters
    
}
