package com.example.dto;

import com.example.entity.PaymentType;
import com.example.entity.WalletTransactionPurpose;
import com.example.entity.PaymentPurpose;

public class WalletRequestDto {

    private String gdcNumber;
    private PaymentType userType;
    private WalletTransactionPurpose purpose;
    private Double amount; // for topup/advance
	public String getGdcNumber() {
		return gdcNumber;
	}
	public void setGdcNumber(String gdcNumber) {
		this.gdcNumber = gdcNumber;
	}
	public PaymentType getUserType() {
		return userType;
	}
	public void setUserType(PaymentType userType) {
		this.userType = userType;
	}
	public WalletTransactionPurpose getPurpose() {
		return purpose;
	}
	public void setPurpose(WalletTransactionPurpose purpose) {
		this.purpose = purpose;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

    // 🔴 GENERATE getters & setters
    
    
}
