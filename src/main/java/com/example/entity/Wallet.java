package com.example.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "yfs_wallet",
    uniqueConstraints = @UniqueConstraint(columnNames = {"gdc_number", "user_type"})
)
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gdc_number", nullable = false)
    private String gdcNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private PaymentType userType;

    private Double balance = 0.0;

    // ✅ FIXED — NOW ENUM
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WalletStatus status = WalletStatus.ACTIVE;

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

    // ================= GETTERS / SETTERS =================

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

    public WalletStatus getStatus() {
        return status;
    }

    public void setStatus(WalletStatus status) {
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
}
