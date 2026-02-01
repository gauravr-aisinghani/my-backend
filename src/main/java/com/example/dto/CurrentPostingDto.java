package com.example.dto;

import java.time.LocalDateTime;

public class CurrentPostingDto {

    private Long assignmentId;
    private String driverName;
    private String transporterName;
    private String assignmentStatus;
    private LocalDateTime assignedAt;

    public CurrentPostingDto(
            Long assignmentId,
            String driverName,
            String transporterName,
            String assignmentStatus,
            LocalDateTime assignedAt
    ) {
        this.assignmentId = assignmentId;
        this.driverName = driverName;
        this.transporterName = transporterName;
        this.assignmentStatus = assignmentStatus;
        this.assignedAt = assignedAt;
    }

    public Long getAssignmentId() {
        return assignmentId;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getTransporterName() {
        return transporterName;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }
}
