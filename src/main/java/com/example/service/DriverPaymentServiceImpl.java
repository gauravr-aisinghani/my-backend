package com.example.service;

import com.example.dto.YfsDriverPaymentDTO;
import com.example.entity.YfsDriverPayment;
import com.example.entity.YfsDriverPayment.PaymentStatus;
import com.example.repository.YfsDriverPaymentRepository;
import com.example.repository.DriverFinalSubmissionRepository; // you must have this for driver info
import com.example.entity.DriverFinalSubmission; // entity of yfs_driver_final_submission
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverPaymentServiceImpl implements DriverPaymentService {

    @Autowired
    private YfsDriverPaymentRepository paymentRepository;

    @Autowired
    private DriverFinalSubmissionRepository driverRepository;

    @Override
    public YfsDriverPaymentDTO getPaymentStatusByGdc(String gdcRegistrationNumber) {
        Optional<DriverFinalSubmission> driverOpt = driverRepository.findByGdcRegistrationNumber(gdcRegistrationNumber);
        if(driverOpt.isEmpty()) return null;

        Long driverId = driverOpt.get().getFinalId();
        Optional<YfsDriverPayment> paymentOpt = paymentRepository.findTopByDriverRegistrationIdOrderByCreatedAtDesc(driverId);
        if(paymentOpt.isEmpty()) return null;

        return convertToDTO(paymentOpt.get());
    }

    @Override
    public YfsDriverPaymentDTO createPayment(YfsDriverPaymentDTO paymentDTO) {
        YfsDriverPayment payment = new YfsDriverPayment();
        payment.setDriverRegistrationId(paymentDTO.getDriverRegistrationId());
        payment.setGdcRegistrationNumber(paymentDTO.getGdcRegistrationNumber());
        payment.setAmount(paymentDTO.getAmount());
        payment.setStatus(PaymentStatus.PENDING);
        payment = paymentRepository.save(payment);
        return convertToDTO(payment);
    }

    @Override
    public YfsDriverPaymentDTO updatePaymentStatus(Long paymentId, String status, String transactionId) {
        Optional<YfsDriverPayment> paymentOpt = paymentRepository.findById(paymentId);
        if(paymentOpt.isEmpty()) return null;

        YfsDriverPayment payment = paymentOpt.get();
        payment.setStatus(PaymentStatus.valueOf(status));
        payment.setTransactionId(transactionId);
        payment = paymentRepository.save(payment);
        return convertToDTO(payment);
    }

    private YfsDriverPaymentDTO convertToDTO(YfsDriverPayment payment) {
        YfsDriverPaymentDTO dto = new YfsDriverPaymentDTO();
        dto.setPaymentId(payment.getPaymentId());
        dto.setDriverRegistrationId(payment.getDriverRegistrationId());
        dto.setGdcRegistrationNumber(payment.getGdcRegistrationNumber());
        dto.setAmount(payment.getAmount());
        dto.setStatus(payment.getStatus());
        dto.setTransactionId(payment.getTransactionId());
        return dto;
    }
}
