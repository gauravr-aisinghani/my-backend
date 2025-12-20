package com.example.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dto.TransporterDocumentsDto;
import com.example.entity.TransporterDocuments;
import com.example.repository.TransporterDocumentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class TransporterDocumentsServiceImpl implements TransporterDocumentsService {

    private final TransporterDocumentsRepository repository;
    private final Cloudinary cloudinary;

    public TransporterDocumentsServiceImpl(
            TransporterDocumentsRepository repository,
            Cloudinary cloudinary
    ) {
        this.repository = repository;
        this.cloudinary = cloudinary;
    }

    @Override
    public TransporterDocumentsDto uploadDocuments(
            String transporterRegistrationId,
            MultipartFile aadharOriginal,
            MultipartFile panOriginal,
            MultipartFile licenceOriginal,
            MultipartFile gstCertificate,
            MultipartFile liveHomeOfficePhoto,
            MultipartFile stampLetterAgreement,
            MultipartFile transporterAccountPassbook,
            MultipartFile transporterAutoSignature,
            MultipartFile transporterSelfieLiveLocation
    ) throws Exception {

        TransporterDocuments doc = new TransporterDocuments();
        doc.setTransporterRegistrationId(transporterRegistrationId);

        doc.setAadharOriginalPhotoUrl(upload(transporterRegistrationId, "aadhar_original", aadharOriginal));
        doc.setPanOriginalPhotoUrl(upload(transporterRegistrationId, "pan_original", panOriginal));
        doc.setLicenceOriginalPhotoUrl(upload(transporterRegistrationId, "licence_original", licenceOriginal));
        doc.setGstCertificateUrl(upload(transporterRegistrationId, "gst_certificate", gstCertificate));
        doc.setLiveHomeOfficePhotoUrl(upload(transporterRegistrationId, "live_home_office_photo", liveHomeOfficePhoto));
        doc.setStampLetterAgreementUrl(upload(transporterRegistrationId, "stamp_letter_agreement", stampLetterAgreement));
        doc.setTransporterAccountPassbookUrl(upload(transporterRegistrationId, "transporter_account_passbook", transporterAccountPassbook));
        doc.setTransporterAutoSignatureUrl(upload(transporterRegistrationId, "transporter_auto_signature", transporterAutoSignature));
        doc.setTransporterSelfieLiveLocationUrl(upload(transporterRegistrationId, "transporter_selfie_live_location", transporterSelfieLiveLocation));

        return toDto(repository.save(doc));
    }

    private String upload(String regId, String field, MultipartFile file) throws Exception {
        String publicId = "transporter_documents/" + regId + "/" + field;

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "public_id", publicId,
                        "overwrite", true
                )
        );

        return uploadResult.get("secure_url").toString();
    }

    private TransporterDocumentsDto toDto(TransporterDocuments d) {
        TransporterDocumentsDto dto = new TransporterDocumentsDto();

        dto.setTransporterDocumentId(d.getTransporterDocumentId());
        dto.setTransporterRegistrationId(d.getTransporterRegistrationId());

        dto.setAadharOriginalPhotoUrl(d.getAadharOriginalPhotoUrl());
        dto.setPanOriginalPhotoUrl(d.getPanOriginalPhotoUrl());
        dto.setLicenceOriginalPhotoUrl(d.getLicenceOriginalPhotoUrl());
        dto.setGstCertificateUrl(d.getGstCertificateUrl());
        dto.setLiveHomeOfficePhotoUrl(d.getLiveHomeOfficePhotoUrl());
        dto.setStampLetterAgreementUrl(d.getStampLetterAgreementUrl());
        dto.setTransporterAccountPassbookUrl(d.getTransporterAccountPassbookUrl());
        dto.setTransporterAutoSignatureUrl(d.getTransporterAutoSignatureUrl());
        dto.setTransporterSelfieLiveLocationUrl(d.getTransporterSelfieLiveLocationUrl());

        return dto;
    }

    @Override
    public TransporterDocumentsDto getByRegistrationId(String transporterRegistrationId) {
        TransporterDocuments d =
                repository.findByTransporterRegistrationId(transporterRegistrationId);
        return d != null ? toDto(d) : null;
    }
}
