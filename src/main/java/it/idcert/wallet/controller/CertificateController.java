package it.idcert.wallet.controller;

import it.idcert.wallet.dto.CertificationOverview;
import it.idcert.wallet.dto.CertificationResponse;
import it.idcert.wallet.dto.InsertCertificationRequest;
import it.idcert.wallet.dto.NotarizationResponse;
import it.idcert.wallet.service.BlockchainNotarizationService;
import it.idcert.wallet.service.CertificateService;
import it.idcert.wallet.service.OpenBadgeValidatorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/certification")
public class CertificateController {

    private final BlockchainNotarizationService notarizationService;
    private final OpenBadgeValidatorService validatorService;
    private final CertificateService certificateService;

    public CertificateController(BlockchainNotarizationService notarizationService, OpenBadgeValidatorService validatorService, CertificateService certificateService) {
        this.notarizationService = notarizationService;
        this.validatorService = validatorService;
        this.certificateService = certificateService;
    }


    @PostMapping("/insert")
    public ResponseEntity<Long> insertCertification(@RequestParam("file") MultipartFile file) {

        try {
            return ResponseEntity.ok(certificateService.insertNewCertification(file));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/insertTextData")
    public ResponseEntity<Void> insertTextData(@RequestBody InsertCertificationRequest request){
        certificateService.insertTextData(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public NotarizationResponse notarize(@PathVariable("id") Long id) {
        return notarizationService.notarizeHash(id);
    }

    @GetMapping("/viewCertifications")
    public ResponseEntity<List<CertificationOverview>> viewCertifications(){
        return ResponseEntity.ok(certificateService.findAllCertification());
    }

    @GetMapping("/certificationsDetails/{id}")
    public ResponseEntity<CertificationResponse> certificationDetails(@PathVariable Long id){
        return ResponseEntity.ok(certificateService.findCertification(id));
    }

    @GetMapping("/test")
    public String testEndpoint() {
        return "success";
    }
}