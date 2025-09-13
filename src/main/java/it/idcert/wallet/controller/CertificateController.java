package it.idcert.wallet.controller;

import it.idcert.wallet.service.CertificateService;
import it.idcert.wallet.service.OpenBadgeValidatorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/validator")
public class CertificateController {

    private final CertificateService certificateService;

    public CertificateController(CertificateService certificateService) {

        this.certificateService = certificateService;
    }


    @GetMapping
    public ResponseEntity<Long> validate(@RequestParam("file") MultipartFile file) {

        try {
            return ResponseEntity.ok(certificateService.insertNewCertification(file));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "success";
    }
}