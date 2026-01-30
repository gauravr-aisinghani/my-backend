package com.example.repository;

import com.example.entity.DriverAssignment;
import com.example.entity.YfsDriverAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverAssignmentRepository
        extends JpaRepository<DriverAssignment, Long> {

    boolean existsByAssignedDriverRegistrationId(Long driverRegistrationId);

    boolean existsByRequestId(Long requestId);
}
