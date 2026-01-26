package com.example.controller;

import com.example.dto.*;
import com.example.entity.PaymentType;
import com.example.service.WalletService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    // 🔹 Get Wallet Balance
    @GetMapping("/{gdcNumber}/{userType}")
    public WalletResponseDto getWallet(
        @PathVariable String gdcNumber,
        @PathVariable PaymentType userType
    ){
        return walletService.getWallet(gdcNumber,userType);
    }

    // 🔹 Credit Wallet (topup / advance)
    @PostMapping("/credit")
    public WalletResponseDto creditWallet(@RequestBody WalletRequestDto req){
        return walletService.creditWallet(req);
    }

    // 🔹 Wallet Transactions History
    @GetMapping("/transactions/{gdcNumber}/{userType}")
    public List<WalletTransactionResponseDto> getTransactions(
        @PathVariable String gdcNumber,
        @PathVariable PaymentType userType
    ){
        return walletService.getTransactions(gdcNumber,userType);
    }
}
