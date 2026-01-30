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

        String transporterName = transporterRepo
                .findByTransporterRegistrationId(gdcNumber)
                .map(YfsTransporterDetails::getTransportCompanyName)
                .orElse("Unknown Transporter");

        return buildLedger(wallet, transporterName, "TRP");
    }

    // ================= DRIVER LEDGER =================
    public List<LedgerRowDto> driverLedger(
            String gdcNumber,
            Long driverRegistrationId
    ) {

        Wallet wallet = walletRepository
                .findByGdc(gdcNumber, PaymentType.DRIVER)
                .orElseThrow(() -> new RuntimeException("Driver wallet not found"));

        String driverName = driverRepo
                .findByDriverRegistrationId(driverRegistrationId)
                .map(DriverDetails::getFullName)
                .orElse("Unknown Driver");

        return buildLedger(wallet, driverName, "DRV");
    }

    // ================= COMMON BUILDER =================
    private List<LedgerRowDto> buildLedger(
            Wallet wallet,
            String name,
            String prefix
    ) {

        List<WalletTransaction> transactions =
                walletTransactionRepository
                        .findByWalletIdOrderByCreatedAtAsc(wallet.getId());

        List<LedgerRowDto> ledger = new ArrayList<>();
        int seq = 1;

        for (WalletTransaction tx : transactions) {

            Double credit = tx.getTxnType() == TransactionType.CREDIT
                    ? tx.getAmount() : null;

            Double debit = tx.getTxnType() == TransactionType.DEBIT
                    ? tx.getAmount() : null;

            ledger.add(
                    new LedgerRowDto(
                            tx.getCreatedAt(),
                            name,
                            prefix + String.format("%04d", seq++),
                            credit,
                            debit,
                            tx.getClosingBalance(),
                            tx.getPurpose().name()
                    )
            );
        }

        return ledger;
    }
    
 // ================= ALL TRANSPORTERS (SEARCHABLE) =================
    public List<LedgerSummaryDto> allTransporters(String search) {

        List<YfsTransporterDetails> transporters =
                (search == null || search.isBlank())
                        ? transporterRepo.findAll()
                        : transporterRepo
                            .findByTransportCompanyNameContainingIgnoreCase(search);

        List<LedgerSummaryDto> result = new ArrayList<>();

        for (YfsTransporterDetails t : transporters) {

            walletRepository
                .findByGdc(t.getTransporterRegistrationId(), PaymentType.TRANSPORTER)
                .ifPresent(wallet ->
                    result.add(
                        new LedgerSummaryDto(
                            wallet.getGdcNumber(),
                            t.getTransportCompanyName(),
                            wallet.getBalance(),
                            wallet.getStatus().name()
                        )
                    )
                );
        }

        return result;
    }

    // ================= ALL DRIVERS (SEARCHABLE) =================
    public List<LedgerSummaryDto> allDrivers(String search) {

        List<DriverDetails> drivers =
                (search == null || search.isBlank())
                        ? driverRepo.findAll()
                        : driverRepo.findByFullNameContainingIgnoreCase(search);

        List<LedgerSummaryDto> result = new ArrayList<>();

        for (DriverDetails d : drivers) {

            walletRepository
                .findByGdc(d.getDriverRegistrationId().toString(), PaymentType.DRIVER)
                .ifPresent(wallet ->
                    result.add(
                        new LedgerSummaryDto(
                            wallet.getGdcNumber(),
                            d.getFullName(),
                            wallet.getBalance(),
                            wallet.getStatus().name()
                        )
                    )
                );
        }

        return result;
    }

}
