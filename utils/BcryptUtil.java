package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class BcryptUtil {
    private final int SALT_LENGTH = 16;
    private final int ITERATIONS = 10_000;

    public String hashPassword(String password) {
        byte[] salt = generateSalt();
        byte[] hashedBytes = performHash(password, salt);
        return Base64.getEncoder().encodeToString(concatenateByteArrays(salt, hashedBytes));
    }

    public boolean verifyPassword(String candidatePassword, String hashedPassword) {
        byte[] decodedHashed = Base64.getDecoder().decode(hashedPassword);
        byte[] salt = new byte[SALT_LENGTH];
        System.arraycopy(decodedHashed, 0, salt, 0, SALT_LENGTH);
        byte[] computedHash = performHash(candidatePassword, salt);
        return MessageDigest.isEqual(decodedHashed, concatenateByteArrays(salt, computedHash));
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    private byte[] performHash(String password, byte[] salt) {
        byte[] combined = concatenateByteArrays(salt, password.getBytes());
        for (int i = 0; i < ITERATIONS; i++) {
            combined = sha256(combined);
        }
        return combined;
    }

    private byte[] sha256(byte[] input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available.");
        }
    }

    private byte[] concatenateByteArrays(byte[] a, byte[] b) {
        byte[] result = new byte[a.length + b.length];
        System.arraycopy(a, 0, result, 0, a.length);
        System.arraycopy(b, 0, result, a.length, b.length);
        return result;
    }

}
