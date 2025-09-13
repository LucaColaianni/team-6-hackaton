package it.idcert.wallet.dto;

import org.springframework.core.io.Resource;

public class CertificationOverview {
    private Long id;
    private Resource certificationImage;

    public CertificationOverview(Long id, Resource file) {
        this.id = id;
        this.certificationImage = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Resource getCertificationImage() {
        return certificationImage;
    }

    public void setCertificationImage(Resource certificationImage) {
        this.certificationImage = certificationImage;
    }
}
