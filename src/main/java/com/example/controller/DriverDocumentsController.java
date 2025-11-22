package com.example.controller;

import com.example.dto.DriverDocumentsDto;
import com.example.service.DriverDocumentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/driver-documents")
@CrossOrigin
public class DriverDocumentsController {

    private final DriverDocumentsService service;

    public DriverDocumentsController(DriverDocumentsService service) {
        this.service = service;
    }

    // ============================
    // 1️⃣ Upload Using Query Param
    // ============================
    @PostMapping("/upload")
    public ResponseEntity<DriverDocumentsDto> uploadDocumentsQueryParam(
            @RequestParam Long driverRegistrationId,
            @RequestParam("driverSelfie") MultipartFile driverSelfie,
            @RequestParam("homePhoto") MultipartFile homePhoto,
            @RequestParam("sarpanchLetter") MultipartFile sarpanchLetter,
            @RequestParam("bankAccountDetails") MultipartFile bankAccountDetails,
            @RequestParam("passbookPhoto") MultipartFile passbookPhoto,
            @RequestParam("aadharPhoto") MultipartFile aadharPhoto,
            @RequestParam("panPhoto") MultipartFile panPhoto,
            @RequestParam("licencePhoto") MultipartFile licencePhoto,
            @RequestParam("paymentProofUpi") MultipartFile paymentProofUpi,
            @RequestParam("driverSignature") MultipartFile driverSignature
    ) throws Exception {

        DriverDocumentsDto saved = service.uploadDocuments(
                driverRegistrationId,
                driverSelfie,
                homePhoto,
                sarpanchLetter,
                bankAccountDetails,
                passbookPhoto,
                aadharPhoto,
                panPhoto,
                licencePhoto,
                paymentProofUpi,
                driverSignature
        );

        return ResponseEntity.ok(saved);
    }


    // ============================
    // 2️⃣ Upload Using Path Variable
    // ============================
    @PostMapping("/upload/{driverRegistrationId}")
    public ResponseEntity<DriverDocumentsDto> uploadDocumentsPathVariable(
            @PathVariable Long driverRegistrationId,
            @RequestParam("driverSelfie") MultipartFile driverSelfie,
            @RequestParam("homePhoto") MultipartFile homePhoto,
            @RequestParam("sarpanchLetter") MultipartFile sarpanchLetter,
            @RequestParam("bankAccountDetails") MultipartFile bankAccountDetails,
            @RequestParam("passbookPhoto") MultipartFile passbookPhoto,
            @RequestParam("aadharPhoto") MultipartFile aadharPhoto,
            @RequestParam("panPhoto") MultipartFile panPhoto,
            @RequestParam("licencePhoto") MultipartFile licencePhoto,
            @RequestParam("paymentProofUpi") MultipartFile paymentProofUpi,
            @RequestParam("driverSignature") MultipartFile driverSignature
    ) throws Exception {

        DriverDocumentsDto saved = service.uploadDocuments(
                driverRegistrationId,
                driverSelfie,
                homePhoto,
                sarpanchLetter,
                bankAccountDetails,
                passbookPhoto,
                aadharPhoto,
                panPhoto,
                licencePhoto,
                paymentProofUpi,
                driverSignature
        );

        return ResponseEntity.ok(saved);
    }


    // ============================
    // 3️⃣ Get by Registration ID
    // ============================
    @GetMapping("/by-registration/{id}")
    public ResponseEntity<DriverDocumentsDto> getByReg(@PathVariable Long id) {
        return ResponseEntity.ok(service.getByRegistrationId(id));
    }
}
