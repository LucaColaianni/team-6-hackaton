package it.idcert.wallet.service;

import it.idcert.wallet.dto.CertificationOverview;
import it.idcert.wallet.dto.CertificationResponse;
import it.idcert.wallet.dto.InsertCertificationRequest;
import it.idcert.wallet.entity.CertificationEntity;
import it.idcert.wallet.repository.CertificationRepository;
import it.idcert.wallet.utils.HashUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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

    public Long insertNewCertification(MultipartFile file, InsertCertificationRequest request) throws Exception {
        String openBadge = openBadgeValidatorService.getJsonByPngCertification(file);
        String hash = HashUtils.sha256(openBadge);
        String blobIdPdf = fileService.saveFile(file);

        CertificationEntity certificationEntity = new CertificationEntity();
        certificationEntity.setOpenBadgeJson(openBadge);
        certificationEntity.setHash(hash);
        certificationEntity.setIssuedAt(LocalDateTime.now());
        certificationEntity.setBlobIdPdf(blobIdPdf);
        certificationEntity.setDescription(request.getDescription());
        certificationEntity.setName(request.getName());
        return certificationRepository.save(certificationEntity).getId();
    }

    public List<CertificationOverview> findAllCertification(){

        List<CertificationEntity> certificationEntityList = certificationRepository.findAll();


        return certificationEntityList.stream().map(value -> {
            try {
                return new CertificationOverview(value.getId(),
                        fileService.getFile(value.getBlobIdPdf()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    public CertificationResponse findCertification(Long id){
        CertificationEntity certificationEntity = certificationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Attenzione!! Non esiste la certificazione con id " + id));

        //return new CertificationResponse(certificationEntity.getName(), certificationEntity.getDescription();
        return null;
    }
}
