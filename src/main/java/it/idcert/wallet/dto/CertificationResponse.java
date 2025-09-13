package it.idcert.wallet.dto;

import org.apache.hc.client5.http.entity.mime.MultipartPart;
import org.springframework.web.multipart.MultipartFile;

public class CertificationResponse {
    private Long id;
    private MultipartFile certificationImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getCertificationImage() {
        return certificationImage;
    }

    public void setCertificationImage(MultipartFile certificationImage) {
        this.certificationImage = certificationImage;
    }
}
