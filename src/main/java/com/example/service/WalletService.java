package com.example.service;

import com.example.dto.*;
import com.example.entity.*;
import com.example.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WalletService {

    private final WalletRepository walletRepo;
    private final WalletTransactionRepository txnRepo;

    public WalletService(WalletRepository walletRepo,
                         WalletTransactionRepository txnRepo){
        this.walletRepo = walletRepo;
        this.txnRepo = txnRepo;
    }

    // =========================
    // 1️⃣ Frontend / Controller Topup
    // =========================
    @Transactional
    public WalletResponseDto creditWallet(WalletRequestDto req){
        Wallet wallet = walletRepo.findByGdc(req.getGdcNumber(), req.getUserType())
                .orElseGet(() -> createWallet(req.getGdcNumber(), req.getUserType()));

        Double opening = wallet.getBalance() != null ? wallet.getBalance() : 0.0;
        Double closing = opening + req.getAmount();

        wallet.setBalance(closing);
        walletRepo.save(wallet);

        WalletTransaction txn = new WalletTransaction();
        txn.setWallet(wallet);
        txn.setAmount(req.getAmount());
        txn.setOpeningBalance(opening);
        txn.setClosingBalance(closing);

        // ✅ Use TransactionType enum
        txn.setTxnType(TransactionType.CREDIT);

        // ✅ Use enum directly
        WalletTransactionPurpose purpose = req.getPurpose() != null
                ? req.getPurpose()
                : WalletTransactionPurpose.MANUAL_TOPUP;
        txn.setPurpose(purpose);

        txn.setDescription("Wallet topup");
        txnRepo.save(txn);

        WalletResponseDto res = new WalletResponseDto();
        res.setGdcNumber(wallet.getGdcNumber());
        res.setUserType(wallet.getUserType());
        res.setBalance(wallet.getBalance());

        return res;
    }

    // =========================
    // 2️⃣ PaymentService Integration
    // =========================
    @Transactional
    public void credit(Payment payment){
        Wallet wallet = walletRepo.findByGdc(payment.getGdcNumber(), payment.getPaymentType())
                .orElseGet(() -> createWallet(payment.getGdcNumber(), payment.getPaymentType()));

        Double opening = wallet.getBalance() != null ? wallet.getBalance() : 0.0;
        Double closing = opening + payment.getAmount();

        wallet.setBalance(closing);
        walletRepo.save(wallet);

        // ✅ Map PaymentPurpose → WalletTransactionPurpose
        WalletTransactionPurpose purpose = mapPaymentPurpose(payment.getPurpose());

        WalletTransaction txn = new WalletTransaction();
        txn.setWallet(wallet);
        txn.setAmount(payment.getAmount());
        txn.setOpeningBalance(opening);
        txn.setClosingBalance(closing);

        // ✅ Use enum
        txn.setTxnType(TransactionType.CREDIT);

        txn.setPurpose(purpose);
        txn.setDescription("Payment credited");

        txnRepo.save(txn);
    }

    // =========================
    // 3️⃣ Get Wallet Balance
    // =========================
    public WalletResponseDto getWallet(String gdc, PaymentType type){
        Wallet wallet = walletRepo.findByGdc(gdc,type)
                .orElseGet(() -> createWallet(gdc,type));

        WalletResponseDto res = new WalletResponseDto();
        res.setGdcNumber(wallet.getGdcNumber());
        res.setUserType(wallet.getUserType());
        res.setBalance(wallet.getBalance());
        return res;
    }

    // =========================
    // 4️⃣ Get Wallet Transactions
    // =========================
    public List<WalletTransactionResponseDto> getTransactions(String gdc, PaymentType type){
        Wallet wallet = walletRepo.findByGdc(gdc,type)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        return txnRepo.findAll().stream()
                .filter(t -> t.getWallet().getId().equals(wallet.getId()))
                .map(t -> {
                    WalletTransactionResponseDto dto = new WalletTransactionResponseDto();
                    dto.setGdcNumber(wallet.getGdcNumber());
                    dto.setAmount(t.getAmount());
                    dto.setOpeningBalance(t.getOpeningBalance());
                    dto.setClosingBalance(t.getClosingBalance());

                    // ✅ Convert enum to string for frontend
                    dto.setTxnType(t.getTxnType().name());
                    dto.setPurpose(t.getPurpose().name());
                    dto.setDescription(t.getDescription());
                    return dto;
                }).collect(Collectors.toList());
    }

    // =========================
    // 5️⃣ Helper to create Wallet if missing
    // =========================
    private Wallet createWallet(String gdc, PaymentType type){
        Wallet w = new Wallet();
        w.setGdcNumber(gdc);
        w.setUserType(type);
        w.setBalance(0.0);
        return walletRepo.save(w);
    }

    // =========================
    // 6️⃣ Helper to map PaymentPurpose → WalletTransactionPurpose
    // =========================
    private WalletTransactionPurpose mapPaymentPurpose(PaymentPurpose paymentPurpose) {
        if(paymentPurpose == null) return WalletTransactionPurpose.MANUAL_TOPUP;

        switch(paymentPurpose) {
            case DRIVER_REGISTRATION:
            case TRANSPORTER_REGISTRATION:
                return WalletTransactionPurpose.REGISTRATION;

            case TRANSPORTER_ADVANCE:
                return WalletTransactionPurpose.TRANSPORTER_ADVANCE;

            case MONTHLY_SETTLEMENT:
                return WalletTransactionPurpose.TRANSPORTER_MONTHLY_SETTLEMENT;

            case DRIVER_TOPUP:
            case MANUAL_TOPUP:
            case DRIVER_ADVANCE: // optional
            default:
                return WalletTransactionPurpose.MANUAL_TOPUP;
        }
    }

}
