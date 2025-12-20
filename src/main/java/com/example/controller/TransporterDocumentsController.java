package com.example.controller;

import com.example.dto.TransporterDocumentsDto;
import com.example.service.TransporterDocumentsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/transporter-documents")
@CrossOrigin
public class TransporterDocumentsController {

    private final TransporterDocumentsService service;

    public TransporterDocumentsController(TransporterDocumentsService service) {
        this.service = service;
    }

    @PostMapping("/upload/{transporterRegistrationId}")
    public ResponseEntity<TransporterDocumentsDto> upload(
            @PathVariable String transporterRegistrationId,
            @RequestParam MultipartFile aadharOriginal,
            @RequestParam MultipartFile panOriginal,
            @RequestParam MultipartFile licenceOriginal,
            @RequestParam MultipartFile gstCertificate,
            @RequestParam MultipartFile liveHomeOfficePhoto,
            @RequestParam MultipartFile stampLetterAgreement,
            @RequestParam MultipartFile transporterAccountPassbook,
            @RequestParam MultipartFile transporterAutoSignature,
            @RequestParam MultipartFile transporterSelfieLiveLocation
    ) throws Exception {

        return ResponseEntity.ok(
                service.uploadDocuments(
                        transporterRegistrationId,
                        aadharOriginal,
                        panOriginal,
                        licenceOriginal,
                        gstCertificate,
                        liveHomeOfficePhoto,
                        stampLetterAgreement,
                        transporterAccountPassbook,
                        transporterAutoSignature,
                        transporterSelfieLiveLocation
                )
        );
    }

    @GetMapping("/by-registration/{id}")
    public ResponseEntity<TransporterDocumentsDto> getByReg(@PathVariable String id) {
        return ResponseEntity.ok(service.getByRegistrationId(id));
    }
}
