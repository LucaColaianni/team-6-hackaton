package it.idcert.wallet.service;


import it.idcert.wallet.dto.NotarizationResponse;
import it.idcert.wallet.entity.CertificationEntity;
import it.idcert.wallet.repository.CertificateRepository;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.hc.core5.http.ContentType;

@Service
public class BlockchainNotarizationService {

    private final CertificateRepository certificateRepository;

    public BlockchainNotarizationService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public NotarizationResponse notarizeHash(Long certificateId) {
        CertificationEntity certificationEntity = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Certificazione con ID " + certificateId + " non trovata"));

        String certificateHash = certificationEntity.getHash();

        // Mock della response
        String mockProof = "mock-blockchain-proof-" + certificateHash.substring(0, 8);
        return new NotarizationResponse(certificateHash, true, mockProof);
    }
}