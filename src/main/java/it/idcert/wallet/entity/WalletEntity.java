package it.idcert.wallet.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class WalletEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "wallet_address", length = 100, nullable = false)
    private String walletAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}
