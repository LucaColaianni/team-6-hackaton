package it.idcert.wallet.utils;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashUtils {

    private static final String TEST_JSON = "{ \"name\": \"Luca\", \"age\": 27 }";

    public static String sha256(String data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
