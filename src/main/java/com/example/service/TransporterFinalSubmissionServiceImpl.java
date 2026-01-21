package com.example.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dto.FinalTransporterProfileDTO;
import com.example.dto.TransporterFinalSubmissionRequestDto;
import com.example.dto.TransporterFinalSubmissionResponseDto;
import com.example.entity.TransporterFinalSubmission;
import com.example.repository.TransporterFinalSubmissionRepository;
import com.example.util.IdCardGenerator;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Transactional
public class TransporterFinalSubmissionServiceImpl
        implements TransporterFinalSubmissionService {

    private final TransporterFinalSubmissionRepository repo;
    private final Cloudinary cloudinary;

    public TransporterFinalSubmissionServiceImpl(
            TransporterFinalSubmissionRepository repo,
            Cloudinary cloudinary
    ) {
        this.repo = repo;
        this.cloudinary = cloudinary;
    }

    @Override
    public TransporterFinalSubmissionResponseDto generate(
            TransporterFinalSubmissionRequestDto dto
    ) throws Exception {

        String regId = dto.getTransporterRegistrationId();

        String gdc = dto.getGdcRegistrationNumber();
        if (gdc == null || gdc.isBlank()) {
            gdc = "GDC-T-" + String.format("%06d", repo.count() + 1);
        }

        // ✅ Native query result
        Object[] row = repo.getFullTransporterProfileRaw(regId);

        if (row == null) {
            throw new RuntimeException(
                "Transporter details not found for registrationId: " + regId
            );
        }

        FinalTransporterProfileDTO profile = new FinalTransporterProfileDTO(
                (String) row[0],  // company name
                (String) row[1],  // mobile
                (String) row[2],  // address
                (String) row[3]   // selfie url
        );

        BufferedImage selfie = null;
        if (profile.getSelfieUrl() != null && !profile.getSelfieUrl().isBlank()) {
            selfie = ImageIO.read(new URL(profile.getSelfieUrl()));
        }

        byte[] cardBytes = IdCardGenerator.generateFancyCardBytes(
                selfie,
                profile.getCompanyName(),
                profile.getMobileNumber(),
                profile.getFullAddress(),
                gdc
        );

        File temp = IdCardGenerator.writeBytesToTempPng(cardBytes, 0L);

        Map upload = cloudinary.uploader().upload(
                temp,
                ObjectUtils.asMap(
                        "folder", "transporter_documents/" + regId,
                        "public_id", "gdc_id_card",
                        "overwrite", true
                )
        );

        String uploadedUrl = upload.get("secure_url").toString();

        TransporterFinalSubmission entity = new TransporterFinalSubmission();

        entity.setTransporterRegistrationId(regId);
        entity.setVerificationId(dto.getVerificationId());
        entity.setGdcRegistrationNumber(gdc);
        entity.setIdCardUrl(uploadedUrl);
        entity.setCompletionStatus("COMPLETED");
        entity.setFinalApprovedBy(dto.getFinalApprovedBy());
        entity.setRemarks(dto.getRemarks());
        entity.setTermsStatus(dto.getTermsStatus());
        entity.setWhatsappSent(false);
        entity.setFinalApprovedAt(LocalDateTime.now());

        repo.save(entity);

        return new TransporterFinalSubmissionResponseDto(
                gdc,
                uploadedUrl,
                "Transporter GDC generated successfully"
        );
    }
}
