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
    private final DriverFinalSubmissionRepository driverRepo;
    private final TransporterFinalSubmissionRepository transporterRepo;

    public WalletService(
            WalletRepository walletRepo,
            WalletTransactionRepository txnRepo,
            DriverFinalSubmissionRepository driverRepo,
            TransporterFinalSubmissionRepository transporterRepo
    ){
        this.walletRepo = walletRepo;
        this.txnRepo = txnRepo;
        this.driverRepo = driverRepo;
        this.transporterRepo = transporterRepo;
    }

    // ================= FRONTEND TOPUP =================

    @Transactional
    public WalletResponseDto creditWallet(WalletRequestDto req){

        Wallet wallet = walletRepo.findByGdc(req.getGdcNumber(), req.getUserType())
                .orElseGet(() -> createWallet(req.getGdcNumber(), req.getUserType()));

        Double opening = wallet.getBalance();
        Double closing = opening + req.getAmount();

        wallet.setBalance(closing);
        walletRepo.save(wallet);

        WalletTransaction txn = new WalletTransaction();
        txn.setWallet(wallet);
        txn.setAmount(req.getAmount());
        txn.setOpeningBalance(opening);
        txn.setClosingBalance(closing);
        txn.setTxnType(TransactionType.CREDIT);
        txn.setPurpose(req.getPurpose());
        txn.setDescription("Wallet topup");

        txnRepo.save(txn);

        WalletResponseDto res = new WalletResponseDto();
        res.setGdcNumber(wallet.getGdcNumber());
        res.setUserType(wallet.getUserType());
        res.setBalance(wallet.getBalance());

        return res;
    }

    // ================= PAYMENT CREDIT =================

    @Transactional
    public void credit(Payment payment){

        Wallet wallet = walletRepo.findByGdc(payment.getGdcNumber(), payment.getPaymentType())
                .orElseGet(() -> createWallet(payment.getGdcNumber(), payment.getPaymentType()));

        Double opening = wallet.getBalance();
        Double closing = opening + payment.getAmount();

        wallet.setBalance(closing);
        walletRepo.save(wallet);

        WalletTransaction txn = new WalletTransaction();

        txn.setWallet(wallet);

        // 🔥 THIS LINE WAS MISSING
        txn.setPaymentId(payment.getId());

        txn.setAmount(payment.getAmount());
        txn.setOpeningBalance(opening);
        txn.setClosingBalance(closing);
        txn.setTxnType(TransactionType.CREDIT);
        txn.setPurpose(mapPaymentPurpose(payment.getPurpose()));
        txn.setDescription("Payment credited");

        txnRepo.save(txn);
    }


    // ================= GET WALLET =================

    public WalletResponseDto getWallet(String gdc, PaymentType type){

        Wallet wallet = walletRepo.findByGdc(gdc,type)
                .orElseGet(() -> createWallet(gdc,type));

        WalletResponseDto res = new WalletResponseDto();
        res.setGdcNumber(wallet.getGdcNumber());
        res.setUserType(wallet.getUserType());
        res.setBalance(wallet.getBalance());

        return res;
    }

    // ================= TXN LIST =================

    public List<WalletTransactionResponseDto> getTransactions(String gdc, PaymentType type){

        Wallet wallet = walletRepo.findByGdc(gdc,type)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        return txnRepo.findByWalletId(wallet.getId()).stream()
                .map(t -> {

                    WalletTransactionResponseDto dto = new WalletTransactionResponseDto();
                    dto.setGdcNumber(wallet.getGdcNumber());
                    dto.setAmount(t.getAmount());
                    dto.setOpeningBalance(t.getOpeningBalance());
                    dto.setClosingBalance(t.getClosingBalance());
                    dto.setTxnType(t.getTxnType().name());
                    dto.setPurpose(t.getPurpose().name());
                    dto.setDescription(t.getDescription());

                    return dto;

                }).collect(Collectors.toList());
    }

    // ================= CREATE WALLET =================

    private Wallet createWallet(String gdc, PaymentType type){

        Wallet w = new Wallet();
        w.setGdcNumber(gdc);
        w.setUserType(type);
        w.setBalance(0.0);
        w.setStatus(WalletStatus.ACTIVE);

        String mobile = resolveMobile(gdc,type);
        w.setUserId(Long.parseLong(mobile));

        return walletRepo.save(w);
    }

    // ================= PURPOSE MAP =================

    private WalletTransactionPurpose mapPaymentPurpose(PaymentPurpose p){

        if(p == null) return WalletTransactionPurpose.MANUAL_TOPUP;

        switch(p){

            case DRIVER_REGISTRATION:
                return WalletTransactionPurpose.DRIVER_REGISTRATION;

            case TRANSPORTER_REGISTRATION:
                return WalletTransactionPurpose.TRANSPORTER_REGISTRATION;

            case DRIVER_ADVANCE:
                return WalletTransactionPurpose.DRIVER_ADVANCE;

            case TRANSPORTER_ADVANCE:
                return WalletTransactionPurpose.TRANSPORTER_ADVANCE;

            case MONTHLY_SETTLEMENT:
                return WalletTransactionPurpose.MONTHLY_SETTLEMENT;

            default:
                return WalletTransactionPurpose.MANUAL_TOPUP;
        }
    }

    // ================= MOBILE RESOLVER =================

    private String resolveMobile(String gdc, PaymentType type){

        String mobile;

        if(type == PaymentType.DRIVER){
            mobile = driverRepo.findDriverMobileByGdc(gdc);
        } else {
            mobile = transporterRepo.findTransporterMobileByGdc(gdc);
        }

        if(mobile == null){
            throw new RuntimeException("Mobile not found for GDC: " + gdc);
        }

        return mobile;
    }
}
