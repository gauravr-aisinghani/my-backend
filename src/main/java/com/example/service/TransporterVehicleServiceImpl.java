package com.example.service;

import com.example.dto.TransporterVehicleDTO;
import com.example.entity.TransporterVehicle;
import com.example.repository.TransporterVehicleRepository;
import com.example.service.TransporterVehicleService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TransporterVehicleServiceImpl implements TransporterVehicleService {

    private final TransporterVehicleRepository repository;

    public TransporterVehicleServiceImpl(TransporterVehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public TransporterVehicle saveVehicle(TransporterVehicleDTO dto) {

        TransporterVehicle vehicle = new TransporterVehicle();

        // UUID generated in backend
        vehicle.setTransporterVehicleId(UUID.randomUUID().toString());

        // Comes from frontend
        vehicle.setTransporterRegistrationId(dto.getTransporterRegistrationId());

        vehicle.setTotalGaadi(dto.getTotalGaadi());
        vehicle.setPostOfVehicle(dto.getPostOfVehicle());
        vehicle.setPaymentTerms(dto.getPaymentTerms());
        vehicle.setPayment30thDate(dto.getPayment30thDate());
        vehicle.setOtherKnownTransporterInWtl(dto.getOtherKnownTransporterInWtl());
        vehicle.setMobileNumber(dto.getMobileNumber());
        vehicle.setMake(dto.getMake());
        vehicle.setHirePayment(dto.getHirePayment());
        vehicle.setGaadiRouteTo(dto.getGaadiRouteTo());
        vehicle.setGaadiRouteFrom(dto.getGaadiRouteFrom());
        vehicle.setGaadiNumber(dto.getGaadiNumber());
        vehicle.setGaadiModelTo(dto.getGaadiModelTo());
        vehicle.setGaadiModelFrom(dto.getGaadiModelFrom());

        return repository.save(vehicle);
    }
}
