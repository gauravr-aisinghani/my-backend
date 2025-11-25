package com.example.service;

import com.example.dto.FinalGdcRequestDto;
import com.example.dto.FinalGdcResponseDto;
import com.example.entity.FinalGdc;
import com.example.repository.FinalGdcRepository;
import com.example.service.FinalGdcService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FinalGdcServiceImpl implements FinalGdcService {

    private final FinalGdcRepository repo;

    public FinalGdcServiceImpl(FinalGdcRepository repo) {
        this.repo = repo;
    }

    @Override
    public FinalGdcResponseDto generateGdc(FinalGdcRequestDto req) {

        if (repo.existsByVerificationId(req.getVerificationId())) {
            FinalGdc old = repo.findByVerificationId(req.getVerificationId());
            return new FinalGdcResponseDto(
                old.getGdcRegistrationNumber(),
                old.getIdCardUrl(),
                "GDC already created earlier!"
            );
        }

        FinalGdc gdc = new FinalGdc();
        gdc.setVerificationId(req.getVerificationId());
        gdc.setRemarks(req.getRemarks());

        // TEMPORARY: real number later
        gdc.setGdcRegistrationNumber("GDC-" + UUID.randomUUID().toString().substring(0, 8));

        // TEMPORARY: file upload later
        gdc.setIdCardUrl("https://dummy.com/card/" + gdc.getGdcRegistrationNumber());

        gdc.setCreatedAt(LocalDateTime.now());
        gdc.setUpdatedAt(LocalDateTime.now());

        repo.save(gdc);

        return new FinalGdcResponseDto(
                gdc.getGdcRegistrationNumber(),
                gdc.getIdCardUrl(),
                "GDC Generated Successfully!"
        );
    }
}
