package it.idcert.wallet.controller;

import it.idcert.wallet.service.BlockchainNotarizationService;
import it.idcert.wallet.service.OpenBadgeValidatorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/validator")
public class CertificateController {

    private final BlockchainNotarizationService notarizationService;
    private final OpenBadgeValidatorService validatorService;

    public CertificateController(BlockchainNotarizationService notarizationService, OpenBadgeValidatorService validatorService) {
        this.notarizationService = notarizationService;
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

    @GetMapping("/{id}")
    public String notarize(@PathVariable("id") Long id) {
        return notarizationService.notarizeHash(id);
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "success";
    }
}