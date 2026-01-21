package com.example.dto;

import java.util.List;

public class PaymentReportResponseDto {

    private PaymentSummaryDto summary;
    private List<PaymentReportRowDto> payments;

    public PaymentReportResponseDto(PaymentSummaryDto summary,
                                    List<PaymentReportRowDto> payments) {
        this.summary = summary;
        this.payments = payments;
    }

	public PaymentSummaryDto getSummary() {
		return summary;
	}

	public void setSummary(PaymentSummaryDto summary) {
		this.summary = summary;
	}

	public List<PaymentReportRowDto> getPayments() {
		return payments;
	}

	public void setPayments(List<PaymentReportRowDto> payments) {
		this.payments = payments;
	}

    // GETTERS & SETTERS (Generate)
    
}
