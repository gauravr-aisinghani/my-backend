package com.example.repository;

import com.example.entity.YfsDriverAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YfsDriverAssignmentRepository
        extends JpaRepository<YfsDriverAssignment, Long> {

    boolean existsByAssignedDriverRegistrationId(Long driverRegistrationId);

    boolean existsByRequestId(Long requestId);
}
