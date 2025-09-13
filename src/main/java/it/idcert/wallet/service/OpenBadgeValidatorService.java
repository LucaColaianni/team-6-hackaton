package it.idcert.wallet.service;

import it.idcert.wallet.utils.HashUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OpenBadgeValidatorService {

    private static final String VALIDATOR_URL = "https://validator.open-badge.eu/results";

    public String getJsonByPngCertification(MultipartFile file){
        if (file.isEmpty()){
            throw new IllegalArgumentException("Attenzione!! stai caricando un file vuoto");
        }

        if (!file.getContentType().equals("image/png")) {
            throw new IllegalArgumentException("Attenzione!! stai caricando un file non valido");
        }
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(VALIDATOR_URL);

            HttpEntity entity = MultipartEntityBuilder.create()
                    // campo data vuoto
                    .addTextBody("data", "", ContentType.TEXT_PLAIN)
                    // campo profile vuoto
                    .addTextBody("profile", "", ContentType.TEXT_PLAIN)
                    // campo image: il PNG
                    .addBinaryBody(
                            "image",
                            file.getInputStream(),
                            ContentType.create("image/png"),
                            file.getOriginalFilename()
                    )
                    .build();

            post.setEntity(entity);

            try (CloseableHttpResponse response = client.execute(post)) {
                return extractJsonFromHtml(EntityUtils.toString(response.getEntity()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String extractJsonFromHtml(String html) {
        // Cerca il contenuto dentro <textarea id="data">...</textarea>
        Pattern pattern = Pattern.compile("<textarea id=\"data\".*?>(.*?)</textarea>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            String responseWithoutHtml = matcher.group(1);
            //return matcher.group(1); // testo con entità HTML
            return StringEscapeUtils.unescapeHtml4(responseWithoutHtml);
            // Decodifica entità HTML (es. &#34; -> ")
            //return StringEscapeUtils.unescapeHtml4(jsonEscaped);
        } else {
            throw new IllegalArgumentException("JSON non trovato nella risposta HTML");
        }
    }

}
