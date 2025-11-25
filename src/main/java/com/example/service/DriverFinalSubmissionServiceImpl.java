package com.example.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dto.FinalSubmissionRequestDto;
import com.example.dto.FinalSubmissionResponseDto;
import com.example.entity.DriverFinalSubmission;
import com.example.repository.DriverFinalSubmissionRepository;
import com.example.util.IdCardGenerator;
import com.example.util.WhatsappSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class DriverFinalSubmissionServiceImpl implements DriverFinalSubmissionService {

    private final DriverFinalSubmissionRepository repo;
    private final Cloudinary cloudinary;
    private final WhatsappSender whatsappSender;

    @Value("${app.fallback.idcard.path}")
    private String fallbackIdCardPath;

    public DriverFinalSubmissionServiceImpl(
            DriverFinalSubmissionRepository repo,
            Cloudinary cloudinary,
            WhatsappSender whatsappSender
    ) {
        this.repo = repo;
        this.cloudinary = cloudinary;
        this.whatsappSender = whatsappSender;
    }

    @Override
    @Transactional
    public FinalSubmissionResponseDto generateFinalSubmission(FinalSubmissionRequestDto dto) throws Exception {

        // -------------------------------------
        // 1️⃣ Generate GDC Registration Number
        // -------------------------------------
        String gdcNumber = dto.getGdcRegistrationNumber();

        if (gdcNumber == null || gdcNumber.trim().isEmpty()) {
            gdcNumber = "GDC-" + dto.getDriverRegistrationId() + "-" + System.currentTimeMillis();
        }

        // -------------------------------------
        // 2️⃣ Generate ID Card PNG bytes
        // -------------------------------------
        byte[] pngBytes = null;
        try {
            pngBytes = IdCardGenerator.generateFancyCardBytes("", gdcNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // -------------------------------------
        // 3️⃣ Write PNG to Temp File OR fallback
        // -------------------------------------
        File uploadFile;

        if (pngBytes != null) {
            uploadFile = IdCardGenerator.writeBytesToTempPng(pngBytes, dto.getDriverRegistrationId());
        } else {
            uploadFile = new File(fallbackIdCardPath); // fallback image from your config
        }

        // -------------------------------------
        // 4️⃣ Upload to Cloudinary
        // -------------------------------------
        Map uploadResult = cloudinary.uploader().upload(
                uploadFile,
                ObjectUtils.asMap(
                        "folder", "driver_documents/" + dto.getDriverRegistrationId(),
                        "public_id", "gdc_id_card",
                        "overwrite", true
                )
        );

        String secureUrl = uploadResult.get("secure_url") != null
                ? uploadResult.get("secure_url").toString()
                : null;

        // -------------------------------------
        // 5️⃣ Save into Database
        // -------------------------------------
        DriverFinalSubmission entity = new DriverFinalSubmission();
        entity.setDriverRegistrationId(dto.getDriverRegistrationId());
        entity.setVerificationId(dto.getVerificationId());
        entity.setGdcRegistrationNumber(gdcNumber);
        entity.setIdCardUrl(secureUrl);
        entity.setCompletionStatus("PENDING");
        entity.setFinalApprovedBy(dto.getFinalApprovedBy());
        entity.setRemarks(dto.getRemarks());
        entity.setTermsStatus(dto.getTermsStatus() == null ? "ACCEPT" : dto.getTermsStatus());
        entity.setWhatsappSent(false);
        entity.setWhatsappSentAt(null);
        entity.setFinalApprovedAt(LocalDateTime.now());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        repo.save(entity);

        // -------------------------------------
        // 6️⃣ (Optional) Send WhatsApp
        // -------------------------------------
        // Only if frontend sends phoneNumber or we fetch it from driver table

        return new FinalSubmissionResponseDto(
                gdcNumber,
                secureUrl,
                "GDC generated & saved successfully"
        );
    }
}
