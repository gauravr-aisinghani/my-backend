package com.example.service;

import com.cloudinary.Cloudinary;
import com.example.dto.ApproveRequestDto;
import com.example.dto.PendingDriverDto;
import com.example.entity.DriverDocuments;
import com.example.entity.DriverFinal;
import com.example.entity.DriverDetails;
import com.example.entity.DriverVerification;
import com.example.repository.DriverDocumentsRepository;
import com.example.repository.DriverFinalRepository;
import com.example.repository.DriverDetailsRepository;
import com.example.repository.DriverVerificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class DriverVerificationServiceImpl implements DriverVerificationService {

    private final DriverDocumentsRepository documentsRepository;
    private final DriverVerificationRepository verificationRepository;
    private final DriverFinalRepository finalRepository;
    private final DriverDetailsRepository driverDetailsRepo;
    private final Cloudinary cloudinary;

    private static final String SAMPLE_IDCARD_LOCAL_PATH =
            "/mnt/data/6521dd74-4e83-4f38-b04a-889dbd27124c.png";

    public DriverVerificationServiceImpl(
            DriverDocumentsRepository documentsRepository,
            DriverVerificationRepository verificationRepository,
            DriverFinalRepository finalRepository,
            DriverDetailsRepository driverDetailsRepo,
            Cloudinary cloudinary
    ) {
        this.documentsRepository = documentsRepository;
        this.verificationRepository = verificationRepository;
        this.finalRepository = finalRepository;
        this.driverDetailsRepo = driverDetailsRepo;
        this.cloudinary = cloudinary;
    }

    // ===========================
    //        PENDING DRIVERS
    // ===========================
    @Override
    public List<PendingDriverDto> getPendingDrivers() {

        List<Long> regIds = documentsRepository.findDistinctRegistrationIds();

        List<PendingDriverDto> result = new ArrayList<>();

        for (Long regId : regIds) {

            Optional<DriverVerification> opt =
                    verificationRepository.findByDriverRegistrationId(regId);

            if (opt.isPresent() && "APPROVED".equalsIgnoreCase(opt.get().getFinalStatus())) {
                continue;
            }

            DriverDocuments d = documentsRepository.findByDriverRegistrationId(regId);
            if (d == null) continue;

            Optional<DriverDetails> detailsOpt = driverDetailsRepo.findByDriverRegistrationId(regId);

            String name = detailsOpt.map(DriverDetails::getFullName).orElse("N/A");
            String mobile = detailsOpt.map(DriverDetails::getMobileNumber).orElse("N/A");

            PendingDriverDto dto = new PendingDriverDto();
            dto.setDriverRegistrationId(regId);
            dto.setFullName(name);
            dto.setMobileNo(mobile);

            int cnt = 0;
            if (isNotEmpty(d.getDriverSelfie())) cnt++;
            if (isNotEmpty(d.getHomePhoto())) cnt++;
            if (isNotEmpty(d.getSarpanchLetter())) cnt++;
            if (isNotEmpty(d.getBankAccountDetails())) cnt++;
            if (isNotEmpty(d.getPassbookPhoto())) cnt++;
            if (isNotEmpty(d.getAadharPhoto())) cnt++;
            if (isNotEmpty(d.getPanPhoto())) cnt++;
            if (isNotEmpty(d.getLicencePhoto())) cnt++;
            if (isNotEmpty(d.getPaymentProofUpi())) cnt++;
            if (isNotEmpty(d.getDriverSignature())) cnt++;

            dto.setTotalDocs(cnt);

            result.add(dto);
        }

        return result;
    }

    // ===========================
    //   VIEW DRIVER DOCUMENTS
    // ===========================
    @Override
    public Map<String, String> getDriverDocuments(Long driverRegistrationId) {
        DriverDocuments d = documentsRepository.findByDriverRegistrationId(driverRegistrationId);
        Map<String, String> map = new LinkedHashMap<>();

        map.put("driver_selfie", safe(d == null ? null : d.getDriverSelfie()));
        map.put("home_photo", safe(d == null ? null : d.getHomePhoto()));
        map.put("sarpanch_letter", safe(d == null ? null : d.getSarpanchLetter()));
        map.put("bank_account_details", safe(d == null ? null : d.getBankAccountDetails()));
        map.put("passbook_photo", safe(d == null ? null : d.getPassbookPhoto()));
        map.put("aadhar_photo", safe(d == null ? null : d.getAadharPhoto()));
        map.put("pan_photo", safe(d == null ? null : d.getPanPhoto()));
        map.put("licence_photo", safe(d == null ? null : d.getLicencePhoto()));
        map.put("payment_proof_upi", safe(d == null ? null : d.getPaymentProofUpi()));
        map.put("driver_signature", safe(d == null ? null : d.getDriverSignature()));

        return map;
    }

    // ===========================
    //       APPROVE DRIVER
    // ===========================
    @Override
    @Transactional
    public void approveDriver(ApproveRequestDto request) {

        Long regId = request.getDriverRegistrationId();

        DriverVerification dv = verificationRepository
                .findByDriverRegistrationId(regId)
                .orElse(null);

        if (dv == null) {
            dv = new DriverVerification();
            dv.setCreatedAt(LocalDateTime.now());
        }

        dv.setDriverRegistrationId(regId);
        dv.setFinalStatus("APPROVED");
        dv.setVerifiedBy(
                request.getApprovedBy() == null ? "SYSTEM_ADMIN" : request.getApprovedBy()
        );
        dv.setVerifiedAt(LocalDateTime.now());
        dv.setUpdatedAt(LocalDateTime.now());
        dv.setRemarks(request.getRemarks());

        verificationRepository.save(dv);
    }

    // ===========================
    //         REJECT DRIVER
    // ===========================
    @Override
    @Transactional
    public void rejectDriver(ApproveRequestDto request) {

        Long regId = request.getDriverRegistrationId();

        DriverVerification dv = verificationRepository
                .findByDriverRegistrationId(regId)
                .orElse(null);

        if (dv == null) {
            dv = new DriverVerification();
            dv.setCreatedAt(LocalDateTime.now());
        }

        dv.setDriverRegistrationId(regId);
        dv.setFinalStatus("REJECTED");
        dv.setVerifiedBy(
                request.getApprovedBy() == null ? "SYSTEM_ADMIN" : request.getApprovedBy()
        );
        dv.setVerifiedAt(LocalDateTime.now());
        dv.setUpdatedAt(LocalDateTime.now());
        dv.setRemarks(request.getRemarks());

        verificationRepository.save(dv);
    }

    // ===========================
    //    APPROVED DRIVERS LIST
    // ===========================
    @Override
    public List<Map<String, Object>> getApprovedDrivers() {
        return verificationRepository.findApprovedDriversJoined();
    }

    // ===========================
    //         HELPERS
    // ===========================
    private boolean isNotEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }

    private String generateGdcNumber(Long driverRegistrationId) {
        int rand = new Random().nextInt(9000) + 1000;
        return String.format("GDC-%d-%d-%04d",
                LocalDateTime.now().getYear(),
                driverRegistrationId,
                rand);
    }

    private String uploadIdCardPlaceholder(Long driverRegistrationId) {
        try {
            Map<?, ?> params = Collections.singletonMap("folder", "wtl/idcards");
            Map uploadResult = cloudinary.uploader().upload(SAMPLE_IDCARD_LOCAL_PATH, params);
            Object secure = uploadResult.get("secure_url");
            return secure != null ? secure.toString() : SAMPLE_IDCARD_LOCAL_PATH;
        } catch (Exception ex) {
            ex.printStackTrace();
            return SAMPLE_IDCARD_LOCAL_PATH;
        }
    }
}
