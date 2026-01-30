package com.example.repository;

import com.example.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {

    // 🔥 wallet wise transactions
    List<WalletTransaction> findByWalletId(Long walletId);
    
    List<WalletTransaction> findByWalletIdOrderByCreatedAtAsc(Long walletId);

}
