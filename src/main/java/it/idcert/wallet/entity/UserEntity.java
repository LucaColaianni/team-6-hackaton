package it.idcert.wallet.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Table(name = "users")
@Entity
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 256)
    private String firstName;

    @Column(name = "last_name", length = 256 )
    private String lastName;

    @Column(name = "email", length = 320, nullable = false)
    private String email;

    @Column(name = "password_hash", length = 256, nullable = false)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private WalletEntity wallet;
}
