package com.example.service;

import com.example.dto.ApproveRequestTransporterDto;
import com.example.dto.PendingTransporterDto;
import com.example.entity.TransporterDocuments;
import com.example.entity.TransporterVerification;
import com.example.entity.YfsTransporterDetails;
import com.example.repository.TransporterDocumentsRepository;
import com.example.repository.TransporterVerificationRepository;
import com.example.repository.YfsTransporterDetailsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransporterVerificationServiceImpl implements TransporterVerificationService {

    private final TransporterDocumentsRepository documentsRepository;
    private final TransporterVerificationRepository verificationRepository;
    private final YfsTransporterDetailsRepository detailsRepository;

    public TransporterVerificationServiceImpl(
            TransporterDocumentsRepository documentsRepository,
            TransporterVerificationRepository verificationRepository,
            YfsTransporterDetailsRepository detailsRepository
    ) {
        this.documentsRepository = documentsRepository;
        this.verificationRepository = verificationRepository;
        this.detailsRepository = detailsRepository;
    }

    @Override
    public List<PendingTransporterDto> getPendingTransporters() {

        List<String> regIds = documentsRepository.findDistinctRegistrationIds();
        List<PendingTransporterDto> result = new ArrayList<>();

        for (String regId : regIds) {

            Optional<TransporterVerification> verificationOpt =
                    verificationRepository.findByTransporterRegistrationId(regId);

            if (verificationOpt.isPresent()
                    && "APPROVED".equalsIgnoreCase(verificationOpt.get().getFinalStatus())) {
                continue;
            }

            TransporterDocuments docs =
                    documentsRepository.findByTransporterRegistrationId(regId);

            if (docs == null) continue;

            Optional<YfsTransporterDetails> detailsOpt =
                    detailsRepository.findByTransporterRegistrationId(regId);

            PendingTransporterDto dto = new PendingTransporterDto();
            dto.setTransporterRegistrationId(regId);
            dto.setCompanyName(
                    detailsOpt.map(YfsTransporterDetails::getTransportCompanyName).orElse("N/A")
            );
            dto.setOwnerMobile(
                    detailsOpt.map(YfsTransporterDetails::getOwnerMobileNumber).orElse("N/A")
            );

            int count = 0;
            if (notEmpty(docs.getTransporterSelfieLiveLocationUrl())) count++;
            if (notEmpty(docs.getLiveHomeOfficePhotoUrl())) count++;
            if (notEmpty(docs.getGstCertificateUrl())) count++;
            if (notEmpty(docs.getTransporterAccountPassbookUrl())) count++;
            if (notEmpty(docs.getAadharOriginalPhotoUrl())) count++;
            if (notEmpty(docs.getPanOriginalPhotoUrl())) count++;
            if (notEmpty(docs.getLicenceOriginalPhotoUrl())) count++;
            if (notEmpty(docs.getStampLetterAgreementUrl())) count++;
            if (notEmpty(docs.getTransporterAutoSignatureUrl())) count++;

            dto.setTotalDocs(count);
            result.add(dto);
        }

        return result;
    }

    @Override
    public Map<String, String> getTransporterDocuments(String transporterRegistrationId) {

        TransporterDocuments d =
                documentsRepository.findByTransporterRegistrationId(transporterRegistrationId);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("transporter_selfie_live_location_url", safe(d, TransporterDocuments::getTransporterSelfieLiveLocationUrl));
        map.put("live_home_office_photo_url", safe(d, TransporterDocuments::getLiveHomeOfficePhotoUrl));
        map.put("gst_certificate_url", safe(d, TransporterDocuments::getGstCertificateUrl));
        map.put("transporter_account_passbook_url", safe(d, TransporterDocuments::getTransporterAccountPassbookUrl));
        map.put("aadhar_original_photo_url", safe(d, TransporterDocuments::getAadharOriginalPhotoUrl));
        map.put("pan_original_photo_url", safe(d, TransporterDocuments::getPanOriginalPhotoUrl));
        map.put("licence_original_photo_url", safe(d, TransporterDocuments::getLicenceOriginalPhotoUrl));
        map.put("stamp_letter_agreement_url", safe(d, TransporterDocuments::getStampLetterAgreementUrl));
        map.put("transporter_auto_signature_url", safe(d, TransporterDocuments::getTransporterAutoSignatureUrl));

        return map;
    }

    @Override
    @Transactional
    public void approveTransporter(ApproveRequestTransporterDto request) {

        TransporterVerification verification =
                verificationRepository.findByTransporterRegistrationId(
                        request.getTransporterRegistrationId()
                ).orElse(new TransporterVerification());

        verification.setTransporterRegistrationId(request.getTransporterRegistrationId());
        verification.setFinalStatus("APPROVED");
        verification.setVerifiedBy(
                request.getApprovedBy() == null ? "SYSTEM_ADMIN" : request.getApprovedBy()
        );
        verification.setVerifiedAt(LocalDateTime.now());
        verification.setRemarks(request.getRemarks());
        verification.setUpdatedAt(LocalDateTime.now());

        if (verification.getCreatedAt() == null) {
            verification.setCreatedAt(LocalDateTime.now());
        }

        verificationRepository.save(verification);
    }

    @Override
    @Transactional
    public void rejectTransporter(ApproveRequestTransporterDto request) {

        TransporterVerification verification =
                verificationRepository.findByTransporterRegistrationId(
                        request.getTransporterRegistrationId()
                ).orElse(new TransporterVerification());

        verification.setTransporterRegistrationId(request.getTransporterRegistrationId());
        verification.setFinalStatus("REJECTED");
        verification.setVerifiedBy(
                request.getApprovedBy() == null ? "SYSTEM_ADMIN" : request.getApprovedBy()
        );
        verification.setVerifiedAt(LocalDateTime.now());
        verification.setRemarks(request.getRemarks());
        verification.setUpdatedAt(LocalDateTime.now());

        if (verification.getCreatedAt() == null) {
            verification.setCreatedAt(LocalDateTime.now());
        }

        verificationRepository.save(verification);
    }

    @Override
    public List<Map<String, Object>> getApprovedTransporters() {
        return verificationRepository.findApprovedTransporters();
    }

    private boolean notEmpty(String s) {
        return s != null && !s.trim().isEmpty();
    }

    private <T> String safe(T obj, java.util.function.Function<T, String> fn) {
        if (obj == null) return "";
        String val = fn.apply(obj);
        return val == null ? "" : val;
    }
}
