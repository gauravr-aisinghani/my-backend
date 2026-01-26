package com.example.dto;

public class WalletTransactionResponseDto {

    private String gdcNumber;
    private Double amount;
    private Double openingBalance;
    private Double closingBalance;
    private String txnType;
    private String purpose; // stays String for frontend
    private String description;

    public String getGdcNumber() { return gdcNumber; }
    public void setGdcNumber(String gdcNumber) { this.gdcNumber = gdcNumber; }
    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
    public Double getOpeningBalance() { return openingBalance; }
    public void setOpeningBalance(Double openingBalance) { this.openingBalance = openingBalance; }
    public Double getClosingBalance() { return closingBalance; }
    public void setClosingBalance(Double closingBalance) { this.closingBalance = closingBalance; }
    public String getTxnType() { return txnType; }
    public void setTxnType(String txnType) { this.txnType = txnType; }
    public String getPurpose() { return purpose; }
    public void setPurpose(String purpose) { this.purpose = purpose; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

}
