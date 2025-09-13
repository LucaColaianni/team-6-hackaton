package it.idcert.wallet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "wallet_certification")
@Data
public class WalletCertification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private WalletEntity wallet;

    @ManyToOne
    @JoinColumn(name = "certification_id", nullable = false)
    private CertificationEntity certificationEntity;

    @Column(name = "blockchain_tx_id", length = 100, unique = true, nullable = false)
    private String blockchainTxId;
}
