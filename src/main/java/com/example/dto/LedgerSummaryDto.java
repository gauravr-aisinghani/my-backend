package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LedgerSummaryDto {

    private String id;   // ✅ String
    private String name;
    private String code;
    private Double balance;
    private String status;

    public LedgerSummaryDto(
            String id,
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
    
    
}
