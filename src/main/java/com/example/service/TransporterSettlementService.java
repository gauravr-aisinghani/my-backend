package com.example.service;

import com.example.entity.TransporterDriverRequest;
import com.example.repository.TransporterDriverRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransporterSettlementService {

    private final TransporterDriverRequestRepository requestRepo;

    public TransporterSettlementService(TransporterDriverRequestRepository repo) {
        this.requestRepo = repo;
    }

    // ================= ADVANCE (20%) =================
    public Double calculateAdvance(String gdcNumber) {

        List<TransporterDriverRequest> list =
                requestRepo.findByGdcNumberAndAdvancePaidFalse(gdcNumber);

        return list.stream()
                .mapToDouble(r -> r.getMonthlySalary() * 0.20)
                .sum();
    }

    // ================= MONTHLY =================
    public Double calculateMonthlySettlement(String gdcNumber) {

        List<TransporterDriverRequest> list =
                requestRepo.findByGdcNumberAndSettlementPaidFalse(gdcNumber);

        return list.stream()
                .mapToDouble(TransporterDriverRequest::getMonthlySalary)
                .sum();
    }

    // ================= MARK ADVANCE PAID =================
    public void markAdvancePaid(String gdcNumber) {

        requestRepo.findByGdcNumberAndAdvancePaidFalse(gdcNumber)
                .forEach(r -> {
                    r.setAdvancePaid(true);
                    requestRepo.save(r);
                });
    }

    // ================= MARK SETTLEMENT PAID =================
    public void markSettlementPaid(String gdcNumber) {

        requestRepo.findByGdcNumberAndSettlementPaidFalse(gdcNumber)
                .forEach(r -> {
                    r.setSettlementPaid(true);
                    requestRepo.save(r);
                });
    }
}
