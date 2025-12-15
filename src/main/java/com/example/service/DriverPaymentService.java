package com.example.service;

import com.example.dto.YfsDriverPaymentDTO;

public interface DriverPaymentService {

    YfsDriverPaymentDTO getPaymentStatusByGdc(String gdcRegistrationNumber);

    YfsDriverPaymentDTO createPayment(YfsDriverPaymentDTO paymentDTO);

    YfsDriverPaymentDTO updatePaymentStatus(Long paymentId, String status, String transactionId);
}
