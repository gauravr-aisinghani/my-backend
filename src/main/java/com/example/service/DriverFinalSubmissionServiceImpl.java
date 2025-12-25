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

        // 1️⃣ Auto-generate GDC number
        String gdcNumber = dto.getGdcRegistrationNumber();
        if (gdcNumber == null || gdcNumber.isBlank()) {
            long seq = repo.count() + 1;
            gdcNumber = String.format("GDC-YFS-%06d", seq);
        }

        // 2️⃣ Fetch full profile
        FinalDriverProfileDTO profile = repo.getFullDriverProfile(driverRegId);

        String fullName = (profile != null) ? profile.getFullName() : "N/A";
        String mobile = (profile != null) ? profile.getMobileNumber() : "N/A";
        String formattedAddress = (profile != null) ? profile.getFullAddress() : "N/A";

        // 3️⃣ Load Selfie
        BufferedImage selfie = null;

        try {
            String selfieUrl = (profile != null) ? profile.getDriverSelfie() : null;

            System.out.println("🖼 Selfie URL from DB: " + selfieUrl);

            if (selfieUrl != null && !selfieUrl.isBlank()) {

                // Case: Cloudinary or web URL
                if (selfieUrl.startsWith("http://") || selfieUrl.startsWith("https://")) {
                    selfie = ImageIO.read(new URL(selfieUrl));
                    System.out.println("✅ Loaded selfie from URL successfully.");
                }
                // Case: Local file
                else {
                    File local = new File(selfieUrl);
                    if (local.exists()) {
                        selfie = ImageIO.read(local);
                        System.out.println("✅ Loaded selfie from local file.");
                    } else {
                        System.out.println("❌ Local selfie file does NOT exist: " + selfieUrl);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("⚠ Failed to load selfie → Will use fallback image.");
            selfie = null;
        }

        // 4️⃣ Generate ID Card
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
            System.out.println("🧾 Generated ID card stored temporarily at: " + fileToUpload.getAbsolutePath());
        } else {
            // The fallback path
            fileToUpload = new File(fallbackIdCardPath);

            System.out.println("🛑 Using fallback ID card image!");
            System.out.println("📍 Fallback image path: " + fileToUpload.getAbsolutePath());

            if (!fileToUpload.exists()) {
                System.out.println("❌ ERROR: Fallback image does NOT exist at the given path!");
            }
        }

        // 5️⃣ Upload to Cloudinary
        Map upload = cloudinary.uploader().upload(
                fileToUpload,
                ObjectUtils.asMap(
                        "folder", "driver_documents/" + driverRegId,
                        "public_id", "gdc_id_card",
                        "overwrite", true,
                        "resource_type", "image"
                )
        );

        String uploadedCardUrl = upload.get("secure_url").toString();

        // 6️⃣ Save in DB
        DriverFinalSubmission entity = new DriverFinalSubmission();
        entity.setDriverRegistrationId(driverRegId);
        entity.setVerificationId(dto.getVerificationId());
        entity.setGdcRegistrationNumber(gdcNumber);
        entity.setIdCardUrl(uploadedCardUrl);
        entity.setCompletionStatus("COMPLETED");
        entity.setFinalApprovedBy(dto.getFinalApprovedBy());
        entity.setRemarks(dto.getRemarks());
        entity.setTermsStatus(dto.getTermsStatus() == null ? "ACCEPT" : dto.getTermsStatus());
        entity.setWhatsappSent(false);
        entity.setWhatsappSentAt(null);
        entity.setFinalApprovedAt(LocalDateTime.now());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());

        repo.save(entity);

        System.out.println("📵 WhatsApp sending disabled. Skipping...");

        return new FinalSubmissionResponseDto(
                gdcNumber,
                uploadedCardUrl,
                "GDC generated & saved successfully"
        );
    }
}
