package com.example.repository;

import com.example.entity.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction, Long> {
    // 🔴 all default CRUD sufficient
}
