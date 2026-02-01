package com.example.dto;

public interface LedgerSummaryView {

    String getId();   // ✅ transporter_registration_id is STRING
    String getName();
    String getCode();
    Double getBalance();
    String getStatus();
}
