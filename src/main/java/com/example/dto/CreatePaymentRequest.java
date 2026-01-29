package com.example.dto;

import com.example.entity.PaymentPurpose;
import com.example.entity.PaymentType;

public class CreatePaymentRequest {

    private String gdcNumber;
    private PaymentType type;
    
    private Long requestId; // OPTIONAL


    public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	// 🔴 ADD
    private PaymentPurpose purpose;

    // 🔴 ADD (only used for TOPUP / ADVANCE)
    private Double amount;

	public String getGdcNumber() {
		return gdcNumber;
	}

	public void setGdcNumber(String gdcNumber) {
		this.gdcNumber = gdcNumber;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public PaymentPurpose getPurpose() {
		return purpose;
	}

	public void setPurpose(PaymentPurpose purpose) {
		this.purpose = purpose;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

    // getters setters generate yourself
    
}

