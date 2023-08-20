package utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class JwtUtil {

    private static final String ALGORITHM = "HmacSHA256";
    private static final String SECRET_KEY = "JWT_SECRET";

    public static String generateToken(String subject, long expirationTime) {
        String header = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String payload = "{\"sub\":\"" + subject + "\",\"exp\":" + expirationTime + "}";

        String base64Header = base64UrlEncode(header);
        String base64Payload = base64UrlEncode(payload);

        String dataToSign = base64Header + "." + base64Payload;
        String signature = generateSignature(dataToSign);

        return dataToSign + "." + signature;
    }

    public static boolean verifyToken(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return false;
        }

        String dataToVerify = parts[0] + "." + parts[1];
        String expectedSignature = generateSignature(dataToVerify);
        String actualSignature = parts[2];

        return expectedSignature.equals(actualSignature);
    }
    public static String parseToken(String token) {
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid token format");
        }
        String payloadJson = new String(Base64.getDecoder().decode(parts[1]));
        return extractSubject(payloadJson);
    }
    private static String extractSubject(String payloadJson) {
        int subStartIndex = payloadJson.indexOf("\"sub\":") + "\"sub\":".length();
        int subEndIndex = payloadJson.indexOf(",\"exp\":");
        if (subStartIndex >= 0 && subEndIndex >= 0) {
            String subValue = payloadJson.substring(subStartIndex, subEndIndex);
            return subValue.substring(1, subValue.length() - 1);
        }
        return null;
    }
    private static String base64UrlEncode(String input) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    private static String generateSignature(String data) {
        try {
            Mac hmacSHA256 = Mac.getInstance(ALGORITHM);
            SecretKeySpec secretKey = new SecretKeySpec(SECRET_KEY.getBytes(StandardCharsets.UTF_8), ALGORITHM);
            hmacSHA256.init(secretKey);
            byte[] signatureBytes = hmacSHA256.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return base64UrlEncode(new String(signatureBytes, StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException("Error generating signature: " + e.getMessage());
        }
    }

}
