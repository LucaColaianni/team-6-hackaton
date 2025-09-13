package it.idcert.wallet.entity;

import jakarta.persistence.*;
import lombok.Data;

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
}
