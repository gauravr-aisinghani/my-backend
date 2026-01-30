package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LedgerSummaryDto {

    @JsonProperty("id")
    private Long id;  // Changed from String to Long

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("status")
    private String status;

    public LedgerSummaryDto(
            Long id,   // Changed from String to Long
            String name,
            String code,
            Double balance,
            String status
    ) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.balance = balance;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCode() { return code; }
    public Double getBalance() { return balance; }
    public String getStatus() { return status; }
}
