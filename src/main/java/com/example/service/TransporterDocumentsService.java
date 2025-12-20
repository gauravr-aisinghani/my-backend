package com.example.service;

import com.example.dto.TransporterDocumentsDto;
import org.springframework.web.multipart.MultipartFile;

public interface TransporterDocumentsService {

    TransporterDocumentsDto uploadDocuments(
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
    ) throws Exception;

    TransporterDocumentsDto getByRegistrationId(String transporterRegistrationId);
}
