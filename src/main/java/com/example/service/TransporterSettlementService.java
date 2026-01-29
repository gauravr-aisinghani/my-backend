package com.example.service;

import com.example.entity.TransporterDriverRequest;
import com.example.repository.TransporterDriverRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class TransporterSettlementService {

    private final TransporterDriverRequestRepository requestRepo;

    public TransporterSettlementService(TransporterDriverRequestRepository requestRepo) {
        this.requestRepo = requestRepo;
    }

    // ================= ADVANCE (20%) =================
    public Double calculateAdvance(Long requestId) {

        TransporterDriverRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Driver request not found"));

        if (Boolean.TRUE.equals(request.getAdvancePaid())) {
            throw new RuntimeException("Advance already paid for this request");
        }

        return request.getMonthlySalary() * 0.20;
    }

    // ================= MONTHLY SETTLEMENT =================
    public Double calculateMonthlySettlement(Long requestId) {

        TransporterDriverRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Driver request not found"));

        if (Boolean.TRUE.equals(request.getSettlementPaid())) {
            throw new RuntimeException("Settlement already paid for this request");
        }

        return request.getMonthlySalary();
    }

    // ================= MARK ADVANCE PAID =================
    public void markAdvancePaid(Long requestId) {

        TransporterDriverRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Driver request not found"));

        request.setAdvancePaid(true);
        requestRepo.save(request);
    }

    // ================= MARK SETTLEMENT PAID =================
    public void markSettlementPaid(Long requestId) {

        TransporterDriverRequest request = requestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Driver request not found"));

        request.setSettlementPaid(true);
        requestRepo.save(request);
    }
}
