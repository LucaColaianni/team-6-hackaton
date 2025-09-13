package it.idcert.wallet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Table(name = "certifications")
@Entity
@Data
public class CertificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blob_id_pdf")
    private String blobIdPdf;

    @Column(name = "openBadge_json", columnDefinition = "json", nullable = false)
    private String openBadgeJson;

    @Column(name = "hash", length = 64, nullable = false)
    private String hash;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlobIdPdf() {
        return blobIdPdf;
    }

    public void setBlobIdPdf(String blobIdPdf) {
        this.blobIdPdf = blobIdPdf;
    }

    public String getOpenBadgeJson() {
        return openBadgeJson;
    }

    public void setOpenBadgeJson(String openBadgeJson) {
        this.openBadgeJson = openBadgeJson;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }
}
