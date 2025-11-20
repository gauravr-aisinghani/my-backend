package com.example.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    public Map<String, Object> getSummary() {
        Map<String, Object> map = new HashMap<>();

        // Replace these with real queries from DB
        map.put("driverVisitors", 10);
        map.put("transporterVisitors", 5);
        map.put("driverPaid", 4);
        map.put("transporterPaid", 2);

        map.put("pendingPayments", 3);

        map.put("vehiclesByType", Map.of(
                "Truck", 12,
                "Mini Truck", 8,
                "Tanker", 5,
                "Pickup", 9
        ));
        
        map.put("totalVehicles", 34);
        map.put("availableVehicles", 20);
        map.put("assignedVehicles", 14);

        // reports counts
        map.put("reportCounts", Map.of(
                "driverReports", 7,
                "paymentReports", 3
        ));

        map.put("driverFinal", 6);

        return map;
    }
}
