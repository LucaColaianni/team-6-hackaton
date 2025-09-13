package it.idcert.wallet.service;


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
    private static final String WITNESSCHAIN_URL = "https://api.witnesschain.com/v1/timestamp";

    public BlockchainNotarizationService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
    }

    public String notarizeHash(Long certificateId) {
        // Recupero certificazione dal DB
        CertificationEntity certificationEntity = certificateRepository.findById(certificateId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Certificazione con ID " + certificateId + " non trovata"));

        String certificateHash = certificationEntity.getHash();

        // HttpClient 5.x
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(WITNESSCHAIN_URL);
            post.setHeader("Content-Type", "application/json");

            // Payload JSON
            String jsonPayload = "{ \"hash\": \"" + certificateHash + "\" }";
            post.setEntity(new StringEntity(jsonPayload, ContentType.APPLICATION_JSON));

            try (CloseableHttpResponse response = client.execute(post)) {
                int statusCode = response.getCode(); // HttpClient 5.x
                String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);

                if (statusCode >= 200 && statusCode < 300) {
                    return responseBody; // ✅ ritorno la proof
                } else {
                    throw new RuntimeException("Request fallita con status: " + statusCode + " e body: " + responseBody);
                }
            }
        } catch (IOException | ParseException e) {
            throw new RuntimeException("Errore durante la notarizzazione", e);
        }
    }
}
