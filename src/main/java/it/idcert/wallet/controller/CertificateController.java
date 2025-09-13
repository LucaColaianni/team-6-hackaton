package it.idcert.wallet.controller;

import it.idcert.wallet.service.OpenBadgeValidatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/validator")
public class CertificateController {

    private final OpenBadgeValidatorService validatorService;

    public CertificateController(OpenBadgeValidatorService validatorService) {
        this.validatorService = validatorService;
    }


    @GetMapping
    public String validate(@RequestParam("file") MultipartFile file) {

        try {
            return validatorService.getJsonByPngCertification(file);
        } catch (Exception e) {
            e.printStackTrace();
            return "Errore nella validazione: " + e.getMessage();
        }
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "success";
    }
}