package it.idcert.wallet.controller;

import it.idcert.wallet.service.OpenBadgeValidatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validator")
public class CertificateController {

    private final OpenBadgeValidatorService validatorService;

    public CertificateController(OpenBadgeValidatorService validatorService) {
        this.validatorService = validatorService;
    }


    @GetMapping
    public String validate() {
        String path = "/Users/lucacolaianni/Developer/team-6-hackaton/src/main/resources/badges/spring-certified-professional-2024-v2.png";
        try {
            return validatorService.validateBadge(path);
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