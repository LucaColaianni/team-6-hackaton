package it.idcert.wallet.service;

import it.idcert.wallet.entity.CertificationEntity;
import it.idcert.wallet.repository.CertificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class CertificateService {
    private final OpenBadgeValidatorService openBadgeValidatorService;
    private final CertificationRepository certificationRepository;

    public CertificateService(OpenBadgeValidatorService openBadgeValidatorService, CertificationRepository certificationRepository) {
        this.openBadgeValidatorService = openBadgeValidatorService;
        this.certificationRepository = certificationRepository;
    }

    public Long insertNewCertification(MultipartFile file){
        String openBadge = openBadgeValidatorService.getJsonByPngCertification(file);
        //TODO chiamata per creare hash
        String hash = "hash";
        String blobIdPdf = "blobId";
        CertificationEntity certificationEntity = CertificationEntity.builder()
                .openBadgeJson(openBadge)
                .hash(hash)
                .issuedAt(LocalDateTime.now()).
                blobIdPdf(blobIdPdf).
                build();

        return certificationRepository.save(certificationEntity).getId();
    }
}
