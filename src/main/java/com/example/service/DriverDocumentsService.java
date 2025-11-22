package com.example.service;

import com.example.dto.DriverDocumentsDto;
import org.springframework.web.multipart.MultipartFile;

public interface DriverDocumentsService {

    DriverDocumentsDto uploadDocuments(
            Long driverRegistrationId,
            MultipartFile driverSelfie,
            MultipartFile homePhoto,
            MultipartFile sarpanchLetter,
            MultipartFile bankAccountDetails,
            MultipartFile passbookPhoto,
            MultipartFile aadharPhoto,
            MultipartFile panPhoto,
            MultipartFile licencePhoto,
            MultipartFile paymentProofUpi,
            MultipartFile driverSignature
    ) throws Exception;

    DriverDocumentsDto getByRegistrationId(Long driverRegistrationId);
}
