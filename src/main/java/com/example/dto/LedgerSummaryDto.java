package com.example.dto;

public class LedgerSummaryDto {

    private String gdcNumber;
    private String name;
    private Double balance;
    private String status;

    public LedgerSummaryDto(
            String gdcNumber,
            String name,
            Double balance,
            String status
    ) {
        this.gdcNumber = gdcNumber;
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    public String getGdcNumber() { return gdcNumber; }
    public String getName() { return name; }
    public Double getBalance() { return balance; }
    public String getStatus() { return status; }
}
