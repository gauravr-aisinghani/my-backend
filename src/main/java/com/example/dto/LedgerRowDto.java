package com.example.dto;

import java.time.LocalDateTime;

public class LedgerRowDto {

    private LocalDateTime date;
    private String partyName;     // Transporter / Driver Name
    private String referenceId;   // TRP0001 / DRV0001
    private Double credit;
    private Double debit;
    private Double balance;
    private String purpose;

    public LedgerRowDto(
            LocalDateTime date,
            String partyName,
            String referenceId,
            Double credit,
            Double debit,
            Double balance,
            String purpose
    ) {
        this.date = date;
        this.partyName = partyName;
        this.referenceId = referenceId;
        this.credit = credit;
        this.debit = debit;
        this.balance = balance;
        this.purpose = purpose;
    }

    public LocalDateTime getDate() { return date; }
    public String getPartyName() { return partyName; }
    public String getReferenceId() { return referenceId; }
    public Double getCredit() { return credit; }
    public Double getDebit() { return debit; }
    public Double getBalance() { return balance; }
    public String getPurpose() { return purpose; }
}
