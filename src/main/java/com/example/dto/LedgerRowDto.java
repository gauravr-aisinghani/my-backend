package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class LedgerRowDto {

    @JsonProperty("txn_date")
    private LocalDateTime txnDate;

    @JsonProperty("description")
    private String description;

    @JsonProperty("credit_amount")
    private Double creditAmount;

    @JsonProperty("debit_amount")
    private Double debitAmount;

    @JsonProperty("closing_balance")
    private Double closingBalance;

    public LedgerRowDto(
            LocalDateTime txnDate,
            String description,
            Double creditAmount,
            Double debitAmount,
            Double closingBalance
    ) {
        this.txnDate = txnDate;
        this.description = description;
        this.creditAmount = creditAmount;
        this.debitAmount = debitAmount;
        this.closingBalance = closingBalance;
    }

    public LocalDateTime getTxnDate() { return txnDate; }
    public String getDescription() { return description; }
    public Double getCreditAmount() { return creditAmount; }
    public Double getDebitAmount() { return debitAmount; }
    public Double getClosingBalance() { return closingBalance; }
}
