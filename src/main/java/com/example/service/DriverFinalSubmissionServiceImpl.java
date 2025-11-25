package com.example.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dto.FinalDriverProfileDTO;
import com.example.dto.FinalSubmissionRequestDto;
import com.example.dto.FinalSubmissionResponseDto;
import com.example.entity.DriverFinalSubmission;
import com.example.repository.DriverFinalSubmissionRepository;
import com.example.util.IdCardGenerator;
import com.example.util.WhatsappSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
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

        Long driverRegId = dto.getDriverRegistrationId();

        // ------------------------------------------------
        // 1️⃣ GDC Number (Auto-generate if missing)
        // ------------------------------------------------
        String gdcNumber = dto.getGdcRegistrationNumber();
        if (gdcNumber == null || gdcNumber.trim().isEmpty()) {
            long seq = repo.count() + 1;
            gdcNumber = String.format("GDC-YFS-%06d", seq);
        }

        // ------------------------------------------------
        // 2️⃣ Fetch merged driver data with JOIN
        // ------------------------------------------------
        FinalDriverProfileDTO profile = repo.getFullDriverProfile(driverRegId);

        String fullName = profile != null ? profile.getFullName() : "N/A";
        String mobile = profile != null ? profile.getMobileNumber() : "N/A";
        String formattedAddress = profile != null ? profile.getFullAddress() : "N/A";

        // ------------------------------------------------
        // 3️⃣ Load selfie (URL → BufferedImage)
        // ------------------------------------------------
        BufferedImage selfie = null;
        if (profile != null && profile.getDriverSelfie() != null) {
            try {
                selfie = ImageIO.read(new URL(profile.getDriverSelfie()));
            } catch (Exception ex) {
                File f = new File(profile.getDriverSelfie());
                if (f.exists()) selfie = ImageIO.read(f);
            }
        }

        // ------------------------------------------------
        // 4️⃣ Generate GDC ID Card
        // ------------------------------------------------
        byte[] cardBytes = IdCardGenerator.generateFancyCardBytes(
                selfie,
                fullName,
                mobile,
                formattedAddress,
                gdcNumber
        );

        File fileToUpload;
        if (cardBytes != null) {
            fileToUpload = IdCardGenerator.writeBytesToTempPng(cardBytes, driverRegId);
        } else {
            fileToUpload = new File(fallbackIdCardPath);
        }

        // ------------------------------------------------
        // 5️⃣ Upload to Cloudinary
        // ------------------------------------------------
        Map uploadResult = cloudinary.uploader().upload(
                fileToUpload,
                ObjectUtils.asMap(
                        "folder", "driver_documents/" + driverRegId,
                        "public_id", "gdc_id_card",
                        "overwrite", true,
                        "resource_type", "image"
                )
        );

        String uploadedCardUrl = uploadResult.get("secure_url").toString();

        // ------------------------------------------------
        // 6️⃣ Save Final Submission
        // ------------------------------------------------
        DriverFinalSubmission entity = new DriverFinalSubmission();
        entity.setDriverRegistrationId(driverRegId);
        entity.setVerificationId(dto.getVerificationId());
        entity.setGdcRegistrationNumber(gdcNumber);
        entity.setIdCardUrl(uploadedCardUrl);
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

        System.out.println("📵 WhatsApp sending skipped (disabled).");

        return new FinalSubmissionResponseDto(
                gdcNumber,
                uploadedCardUrl,
                "GDC generated & saved successfully"
        );
    }
}
