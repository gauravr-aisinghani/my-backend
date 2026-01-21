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
import java.util.List;
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
        System.out.println("REG ID FROM API = [" + regId + "]");

        boolean alreadyGenerated =
                repo.existsByTransporterRegistrationIdAndCompletionStatus(
                        regId, "COMPLETED"
                );

        if (alreadyGenerated) {
            return new TransporterFinalSubmissionResponseDto(
                    null,
                    null,
                    "GDC already generated for this transporter"
            );
        }

        String gdc = dto.getGdcRegistrationNumber();
        if (gdc == null || gdc.isBlank()) {
            gdc = "GDC-T-" + String.format("%06d", repo.count() + 1);
        }

        List<Map<String, Object>> rows =
                repo.getFullTransporterProfileRaw(regId);

        if (rows == null || rows.isEmpty()) {
            return new TransporterFinalSubmissionResponseDto(
                    null,
                    null,
                    "Transporter details not found for registrationId: " + regId
            );
        }

        Map<String, Object> row = rows.get(0);

        String companyName = row.get("companyName") != null ? row.get("companyName").toString() : "N/A";
        String mobileNumber = row.get("mobileNumber") != null ? row.get("mobileNumber").toString() : "N/A";
        String fullAddress = row.get("fullAddress") != null ? row.get("fullAddress").toString() : "N/A";
        String selfieUrl = row.get("selfieUrl") != null ? row.get("selfieUrl").toString() : null;

        FinalTransporterProfileDTO profile = new FinalTransporterProfileDTO(
                companyName,
                mobileNumber,
                fullAddress,
                selfieUrl
        );

        BufferedImage selfie = null;
        if (selfieUrl != null && !selfieUrl.isBlank()) {
            selfie = ImageIO.read(new URL(selfieUrl));
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
