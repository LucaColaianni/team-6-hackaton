package it.idcert.wallet.service;

import it.idcert.wallet.dto.CertificationResponse;
import it.idcert.wallet.entity.CertificationEntity;
import it.idcert.wallet.repository.CertificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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

        CertificationEntity certificationEntity = new CertificationEntity();
        certificationEntity.setOpenBadgeJson(openBadge);
        certificationEntity.setHash(hash);
        certificationEntity.setIssuedAt(LocalDateTime.now());
        certificationEntity.setBlobIdPdf(blobIdPdf);


        return certificationRepository.save(certificationEntity).getId();
    }

    public List<CertificationResponse> findAllCertification(){

        List<CertificationEntity> certificationEntity = certificationRepository.findAll();

        return null;
    }

    public CertificationResponse findCertification(){

        return null;
    }
}
