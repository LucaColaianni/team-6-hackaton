package it.idcert.wallet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "certification")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CertificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blob_id_pdf")
    private String blobIdPdf;

    @Column(name = "openbadge_json", columnDefinition = "json", nullable = false)
    private String openBadgeJson;

    @Column(name = "hash", length = 64, nullable = false)
    private String hash;

    @Column(name = "issued_at")
    private LocalDateTime issuedAt;

    @Column(name = "blockchain_tx_id", length = 100, unique = true)
    private String blockchainTxId;
    @Column(name = "name", length = 255, nullable = false)
    private String name;
    @Column(name = "description", length = 255, nullable = false)
    private String description;
    @Column(name = "release_date")
    private LocalDate releaseDate;

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

    public String getBlockchainTxId() {
        return blockchainTxId;
    }

    public void setBlockchainTxId(String blockchainTxId) {
        this.blockchainTxId = blockchainTxId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
