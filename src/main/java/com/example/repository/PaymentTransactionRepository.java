package com.example.repository;

import com.example.entity.PaymentTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTransactionRepository
        extends JpaRepository<PaymentTransaction, Long> {
}
