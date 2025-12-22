package com.example.dto;

import com.example.entity.PaymentType;

public class CreatePaymentRequest {

    private String gdcNumber;
    private PaymentType type;

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
}
