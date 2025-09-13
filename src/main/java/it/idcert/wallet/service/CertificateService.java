package it.idcert.wallet.service;

import it.idcert.wallet.dto.InsertCertificationRequest;
import it.idcert.wallet.entity.CertificationEntity;
import it.idcert.wallet.repository.CertificationRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class CertificateService {
    private final OpenBadgeValidatorService openBadgeValidatorService;
    private final CertificationRepository certificationRepository;
    private final FileService fileService;

    public CertificateService(OpenBadgeValidatorService openBadgeValidatorService, CertificationRepository certificationRepository, FileService fileService) {
        this.openBadgeValidatorService = openBadgeValidatorService;
        this.certificationRepository = certificationRepository;
        this.fileService = fileService;
    }

    public Long insertNewCertification(MultipartFile file, InsertCertificationRequest request) throws IOException {
        String openBadge = openBadgeValidatorService.getJsonByPngCertification(file);
        //TODO chiamata per creare hash
        String hash = "hash";
        String blobIdPdf = fileService.saveFile(file);
        CertificationEntity certificationEntity = CertificationEntity.builder()
                .openBadgeJson(openBadge)
                .hash(hash)
                .issuedAt(LocalDateTime.now()).
                blobIdPdf(blobIdPdf)
                .name(request.getName())
                .description(request.getDescription())
                .releaseDate(request.getReleaseDate()).
                build();

        return certificationRepository.save(certificationEntity).getId();
    }


}
