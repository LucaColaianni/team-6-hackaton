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

    @Column(name = "openBadge_json", columnDefinition = "json", nullable = false)
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
}
