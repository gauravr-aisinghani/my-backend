package com.example.repository;

import com.example.entity.Wallet;
import com.example.entity.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByGdcNumberAndUserType(String gdcNumber, PaymentType userType);

    // 🔴 ALIAS METHOD for WalletService
    default Optional<Wallet> findByGdc(String gdc, PaymentType type){
        return findByGdcNumberAndUserType(gdc, type);
    }
}
