package com.example.dto;

public class PaymentSummaryDto {

    private long totalPayments;
    private double totalAmount;
    private long paidPayments;
    private long failedPayments;
    private long driverPayments;
    private long transporterPayments;
	public long getTotalPayments() {
		return totalPayments;
	}
	public void setTotalPayments(long totalPayments) {
		this.totalPayments = totalPayments;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public long getPaidPayments() {
		return paidPayments;
	}
	public void setPaidPayments(long paidPayments) {
		this.paidPayments = paidPayments;
	}
	public long getFailedPayments() {
		return failedPayments;
	}
	public void setFailedPayments(long failedPayments) {
		this.failedPayments = failedPayments;
	}
	public long getDriverPayments() {
		return driverPayments;
	}
	public void setDriverPayments(long driverPayments) {
		this.driverPayments = driverPayments;
	}
	public long getTransporterPayments() {
		return transporterPayments;
	}
	public void setTransporterPayments(long transporterPayments) {
		this.transporterPayments = transporterPayments;
	}

    // GETTERS & SETTERS (Generate)
    
}
