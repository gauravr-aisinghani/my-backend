package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "yfs_wallet",
       uniqueConstraints = @UniqueConstraint(columnNames = {"gdc_number", "user_type"}))
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔴 ADD: GDC Number
    @Column(name = "gdc_number", nullable = false)
    private String gdcNumber;

    // 🔴 ADD: DRIVER / TRANSPORTER
    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private PaymentType userType;

    private Double balance = 0.0;

    @Enumerated(EnumType.STRING)
    private String status = "ACTIVE"; // ACTIVE/BLOCKED

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

    // 🔴 GENERATE getters & setters
    
    
}
