package it.idcert.wallet.repository;

import it.idcert.wallet.entity.CertificationEntity;
import it.idcert.wallet.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<CertificationEntity, Long> {
}
