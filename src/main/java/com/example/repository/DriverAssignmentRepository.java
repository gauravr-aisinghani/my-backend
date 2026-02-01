package com.example.repository;

import com.example.dto.CurrentPostingDto;
import com.example.entity.DriverAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverAssignmentRepository
        extends JpaRepository<DriverAssignment, Long> {

    boolean existsByAssignedDriverRegistrationId(Long driverRegistrationId);

    boolean existsByRequestId(Long requestId);

    // ✅ CURRENT POSTINGS
    @Query("""
        SELECT new com.example.dto.CurrentPostingDto(
            da.assignmentId,
            d.fullName,
            t.transportCompanyName,
            da.assignmentStatus,
            da.assignedAt
        )
        FROM DriverAssignment da
        JOIN YfsDriverDetails d
            ON d.driverRegistrationId = da.assignedDriverRegistrationId
        JOIN YfsTransporterDetails t
            ON t.transporterRegistrationId = da.transporterRegistrationId
        WHERE da.assignmentStatus = 'ASSIGNED'
        ORDER BY da.assignedAt DESC
    """)
    List<CurrentPostingDto> findCurrentPostings();
}
