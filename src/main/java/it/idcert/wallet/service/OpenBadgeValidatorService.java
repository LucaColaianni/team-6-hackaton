package it.idcert.wallet.service;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OpenBadgeValidatorService {

    private static final String VALIDATOR_URL = "https://validator.open-badge.eu/results";

    public String validateBadge(String badgeFilePath) throws Exception {
        File file = new File(badgeFilePath);
        if (!file.exists()) {
            throw new IllegalArgumentException("File non trovato: " + badgeFilePath);
        }

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(VALIDATOR_URL);

            HttpEntity entity = MultipartEntityBuilder.create()
                    .addBinaryBody("file", file)
                    .build();

            post.setEntity(entity);

            try (CloseableHttpResponse response = client.execute(post)) {
                return EntityUtils.toString(response.getEntity());
            }
        }
    }

}
