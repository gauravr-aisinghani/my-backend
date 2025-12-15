package com.example.controller;

import com.example.dto.YfsDriverPaymentDTO;
import com.example.service.DriverPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/driver/payment")
public class DriverPaymentController {

    @Autowired
    private DriverPaymentService driverPaymentService;

    // Check payment status by GDC number
    @GetMapping("/status/{gdcNumber}")
    public YfsDriverPaymentDTO getPaymentStatus(@PathVariable("gdcNumber") String gdcNumber){
        return driverPaymentService.getPaymentStatusByGdc(gdcNumber);
    }

    // Create new payment
    @PostMapping("/create")
    public YfsDriverPaymentDTO createPayment(@RequestBody YfsDriverPaymentDTO paymentDTO){
        return driverPaymentService.createPayment(paymentDTO);
    }

    // Update payment status (callback from gateway)
    @PostMapping("/update/{paymentId}")
    public YfsDriverPaymentDTO updatePaymentStatus(
            @PathVariable Long paymentId,
            @RequestParam String status,
            @RequestParam(required = false) String transactionId
    ){
        return driverPaymentService.updatePaymentStatus(paymentId, status, transactionId);
    }
}
