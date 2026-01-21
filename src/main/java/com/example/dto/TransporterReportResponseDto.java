package com.example.dto;

import java.util.List;

public class TransporterReportResponseDto {

    private TransporterSummaryDto summary;
    private List<TransporterReportRowDto> transporters;

    public TransporterReportResponseDto(
            TransporterSummaryDto summary,
            List<TransporterReportRowDto> transporters
    ) {
        this.summary = summary;
        this.transporters = transporters;
    }

	public TransporterSummaryDto getSummary() {
		return summary;
	}

	public void setSummary(TransporterSummaryDto summary) {
		this.summary = summary;
	}

	public List<TransporterReportRowDto> getTransporters() {
		return transporters;
	}

	public void setTransporters(List<TransporterReportRowDto> transporters) {
		this.transporters = transporters;
	}

    // getters
    
}
