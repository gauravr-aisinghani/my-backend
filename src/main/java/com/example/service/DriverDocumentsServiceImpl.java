//package com.example.service;
//
//import com.cloudinary.Cloudinary;
//import com.cloudinary.utils.ObjectUtils;
//import com.example.dto.DriverDocumentsDto;
//import com.example.entity.DriverDocuments;
//import com.example.repository.DriverDocumentsRepository;
//import com.example.service.DriverDocumentsService;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Map;
//
//@Service
//public class DriverDocumentsServiceImpl implements DriverDocumentsService {
//
//    private final DriverDocumentsRepository repository;
//    private final Cloudinary cloudinary;
//
//    public DriverDocumentsServiceImpl(
//            DriverDocumentsRepository repository,
//            Cloudinary cloudinary
//    ) {
//        this.repository = repository;
//        this.cloudinary = cloudinary;
//    }
//
//    @Override
//    public DriverDocumentsDto uploadDocuments(
//            Long driverRegistrationId,
//            MultipartFile driverSelfie,
//            MultipartFile homePhoto,
//            MultipartFile sarpanchLetter,
//            MultipartFile bankAccountDetails,
//            MultipartFile passbookPhoto,
//            MultipartFile aadharPhoto,
//            MultipartFile panPhoto,
//            MultipartFile licencePhoto,
//            MultipartFile paymentProofUpi,
//            MultipartFile driverSignature
//    ) throws Exception {
//
//        DriverDocuments doc = new DriverDocuments();
//        doc.setDriverRegistrationId(driverRegistrationId);
//
//        doc.setDriverSelfie(upload(driverSelfie));
//        doc.setHomePhoto(upload(homePhoto));
//        doc.setSarpanchLetter(upload(sarpanchLetter));
//        doc.setBankAccountDetails(upload(bankAccountDetails));
//        doc.setPassbookPhoto(upload(passbookPhoto));
//        doc.setAadharPhoto(upload(aadharPhoto));
//        doc.setPanPhoto(upload(panPhoto));
//        doc.setLicencePhoto(upload(licencePhoto));
//        doc.setPaymentProofUpi(upload(paymentProofUpi));
//        doc.setDriverSignature(upload(driverSignature));
//
//        DriverDocuments saved = repository.save(doc);
//
//        return toDto(saved);
//    }
//
//    private String upload(MultipartFile file) throws Exception {
//        Map uploadResult = cloudinary.uploader().upload(
//                file.getBytes(),
//                ObjectUtils.asMap("resource_type", "auto")
//        );
//        return uploadResult.get("secure_url").toString();
//    }
//
//    private DriverDocumentsDto toDto(DriverDocuments d) {
//        DriverDocumentsDto dto = new DriverDocumentsDto();
//
//        dto.setDriverDocumentId(d.getDriverDocumentId());
//        dto.setDriverRegistrationId(d.getDriverRegistrationId());
//
//        dto.setDriverSelfie(d.getDriverSelfie());
//        dto.setHomePhoto(d.getHomePhoto());
//        dto.setSarpanchLetter(d.getSarpanchLetter());
//        dto.setBankAccountDetails(d.getBankAccountDetails());
//        dto.setPassbookPhoto(d.getPassbookPhoto());
//        dto.setAadharPhoto(d.getAadharPhoto());
//        dto.setPanPhoto(d.getPanPhoto());
//        dto.setLicencePhoto(d.getLicencePhoto());
//        dto.setPaymentProofUpi(d.getPaymentProofUpi());
//        dto.setDriverSignature(d.getDriverSignature());
//
//        return dto;
//    }
//
//    @Override
//    public DriverDocumentsDto getByRegistrationId(Long driverRegistrationId) {
//        DriverDocuments d = repository.findByDriverRegistrationId(driverRegistrationId);
//        return d != null ? toDto(d) : null;
//    }
//}


package com.example.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.dto.DriverDocumentsDto;
import com.example.entity.DriverDocuments;
import com.example.repository.DriverDocumentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
public class DriverDocumentsServiceImpl implements DriverDocumentsService {

    private final DriverDocumentsRepository repository;
    private final Cloudinary cloudinary;

    public DriverDocumentsServiceImpl(
            DriverDocumentsRepository repository,
            Cloudinary cloudinary
    ) {
        this.repository = repository;
        this.cloudinary = cloudinary;
    }

    @Override
    public DriverDocumentsDto uploadDocuments(
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
    ) throws Exception {

        DriverDocuments doc = new DriverDocuments();
        doc.setDriverRegistrationId(driverRegistrationId);

        doc.setDriverSelfie(upload(driverRegistrationId, "driver_selfie", driverSelfie));
        doc.setHomePhoto(upload(driverRegistrationId, "home_photo", homePhoto));
        doc.setSarpanchLetter(upload(driverRegistrationId, "sarpanch_letter", sarpanchLetter));
        doc.setBankAccountDetails(upload(driverRegistrationId, "bank_account_details", bankAccountDetails));
        doc.setPassbookPhoto(upload(driverRegistrationId, "passbook_photo", passbookPhoto));
        doc.setAadharPhoto(upload(driverRegistrationId, "aadhar_photo", aadharPhoto));
        doc.setPanPhoto(upload(driverRegistrationId, "pan_photo", panPhoto));
        doc.setLicencePhoto(upload(driverRegistrationId, "licence_photo", licencePhoto));
        doc.setPaymentProofUpi(upload(driverRegistrationId, "payment_proof_upi", paymentProofUpi));
        doc.setDriverSignature(upload(driverRegistrationId, "driver_signature", driverSignature));

        DriverDocuments saved = repository.save(doc);

        return toDto(saved);
    }

    // NEW: Upload with folder + public_id pattern
    private String upload(Long driverId, String fieldName, MultipartFile file) throws Exception {

        String publicId = "driver_documents/" + driverId + "/" + fieldName;

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "resource_type", "auto",
                        "public_id", publicId,
                        "overwrite", true     // IMPORTANT
                )
        );

        return uploadResult.get("secure_url").toString();
    }

    private DriverDocumentsDto toDto(DriverDocuments d) {
        DriverDocumentsDto dto = new DriverDocumentsDto();

        dto.setDriverDocumentId(d.getDriverDocumentId());
        dto.setDriverRegistrationId(d.getDriverRegistrationId());

        dto.setDriverSelfie(d.getDriverSelfie());
        dto.setHomePhoto(d.getHomePhoto());
        dto.setSarpanchLetter(d.getSarpanchLetter());
        dto.setBankAccountDetails(d.getBankAccountDetails());
        dto.setPassbookPhoto(d.getPassbookPhoto());
        dto.setAadharPhoto(d.getAadharPhoto());
        dto.setPanPhoto(d.getPanPhoto());
        dto.setLicencePhoto(d.getLicencePhoto());
        dto.setPaymentProofUpi(d.getPaymentProofUpi());
        dto.setDriverSignature(d.getDriverSignature());

        return dto;
    }

    @Override
    public DriverDocumentsDto getByRegistrationId(Long driverRegistrationId) {
        DriverDocuments d = repository.findByDriverRegistrationId(driverRegistrationId);
        return d != null ? toDto(d) : null;
    }
}

