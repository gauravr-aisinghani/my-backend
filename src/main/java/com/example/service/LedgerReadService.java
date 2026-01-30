package com.example.service;

import com.example.dto.LedgerRowDto;
import com.example.dto.LedgerSummaryDto;
import com.example.entity.*;
import com.example.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LedgerReadService {

    private final WalletRepository walletRepository;
    private final WalletTransactionRepository walletTransactionRepository;
    private final YfsTransporterDetailsRepository transporterRepo;
    private final DriverDetailsRepository driverRepo;

    public LedgerReadService(
            WalletRepository walletRepository,
            WalletTransactionRepository walletTransactionRepository,
            YfsTransporterDetailsRepository transporterRepo,
            DriverDetailsRepository driverRepo
    ) {
        this.walletRepository = walletRepository;
        this.walletTransactionRepository = walletTransactionRepository;
        this.transporterRepo = transporterRepo;
        this.driverRepo = driverRepo;
    }

    // ================= TRANSPORTER LEDGER =================
    public List<LedgerRowDto> transporterLedger(String gdcNumber) {

        Wallet wallet = walletRepository
                .findByGdc(gdcNumber, PaymentType.TRANSPORTER)
                .orElseThrow(() -> new RuntimeException("Transporter wallet not found"));

        transporterRepo
                .findByTransporterRegistrationId(gdcNumber)
                .map(YfsTransporterDetails::getTransportCompanyName)
                .orElse("Unknown Transporter");

        return buildLedger(wallet);
    }

    // ================= DRIVER LEDGER =================
    public List<LedgerRowDto> driverLedger(
            String gdcNumber,
            Long driverRegistrationId
    ) {

        Wallet wallet = walletRepository
                .findByGdc(gdcNumber, PaymentType.DRIVER)
                .orElseThrow(() -> new RuntimeException("Driver wallet not found"));

        driverRepo
                .findByDriverRegistrationId(driverRegistrationId)
                .map(DriverDetails::getFullName)
                .orElse("Unknown Driver");

        return buildLedger(wallet);
    }

    // ================= COMMON BUILDER =================
    private List<LedgerRowDto> buildLedger(Wallet wallet) {

        List<WalletTransaction> transactions =
                walletTransactionRepository
                        .findByWalletIdOrderByCreatedAtAsc(wallet.getId());

        List<LedgerRowDto> ledger = new ArrayList<>();

        for (WalletTransaction tx : transactions) {

            Double credit = tx.getTxnType() == TransactionType.CREDIT
                    ? tx.getAmount()
                    : null;

            Double debit = tx.getTxnType() == TransactionType.DEBIT
                    ? tx.getAmount()
                    : null;

            ledger.add(
                    new LedgerRowDto(
                            tx.getCreatedAt(),
                            tx.getPurpose().name(),
                            credit,
                            debit,
                            tx.getClosingBalance()
                    )
            );
        }

        return ledger;
    }

    // ================= ALL TRANSPORTERS (SEARCHABLE) =================
    public List<LedgerSummaryDto> allTransporters(String search) {
        return transporterRepo.fetchTransporterLedgerSummary(
                (search == null || search.isBlank()) ? null : search,
                PaymentType.TRANSPORTER
        );
    }

    // ================= ALL DRIVERS (SEARCHABLE) =================
    public List<LedgerSummaryDto> allDrivers(String search) {
        return driverRepo.fetchDriverLedgerSummary(
                (search == null || search.isBlank()) ? null : search,
                PaymentType.DRIVER
        );
    }
}
