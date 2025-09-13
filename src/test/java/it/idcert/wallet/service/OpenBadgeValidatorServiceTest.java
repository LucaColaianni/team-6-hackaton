package it.idcert.wallet.service;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class OpenBadgeValidatorServiceTest {

    private OpenBadgeValidatorService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new OpenBadgeValidatorService();
    }

    @Test
    void testEmptyFileThrowsException() {
        MockMultipartFile emptyFile = new MockMultipartFile("file", new byte[0]);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                service.getJsonByPngCertification(emptyFile));
        assertEquals("Attenzione!! stai caricando un file vuoto", ex.getMessage());
    }

    @Test
    void testWrongContentTypeThrowsException() {
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test".getBytes());
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                service.getJsonByPngCertification(file));
        assertEquals("Attenzione!! stai caricando un file non valido", ex.getMessage());
    }

    @Test
    void testValidPngFileReturnsJson() throws Exception {
        // Mock file PNG
        byte[] content = "fake-png-content".getBytes();
        MockMultipartFile pngFile = new MockMultipartFile("file", "badge.png", "image/png", content);

        OpenBadgeValidatorService spyService = spy(service);
        doReturn("{\"valid\": true}").when(spyService).extractJsonFromHtml(anyString());

        String json = spyService.getJsonByPngCertification(pngFile);
        assertEquals("{\"valid\": true}", json);
    }

    @Test
    void testExtractJsonFromHtmlSuccess() {
        String html = "<html><body><textarea id=\"data\">{\"valid\":true}</textarea></body></html>";
        String json = service.extractJsonFromHtml(html);
        assertEquals("{\"valid\":true}", json);
    }

    @Test
    void testExtractJsonFromHtmlWithHtmlEntities() {
        String html = "<textarea id=\"data\">{&quot;valid&quot;:true}</textarea>";
        String json = service.extractJsonFromHtml(html);
        assertEquals("{\"valid\":true}", json);
    }

    @Test
    void testExtractJsonFromHtmlNotFound() {
        String html = "<html><body>No textarea here</body></html>";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () ->
                service.extractJsonFromHtml(html));
        assertEquals("JSON non trovato nella risposta HTML", ex.getMessage());
    }
}