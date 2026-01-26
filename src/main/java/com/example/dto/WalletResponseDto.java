package com.example.dto;

import com.example.entity.PaymentType;

public class WalletResponseDto {

    private String gdcNumber;
    private PaymentType userType;
    private Double balance;
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
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

    // 🔴 GENERATE getters & setters
    
}
