package ch.altruce.challenge;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {
    private byte[] obtainSHA(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(s.getBytes(StandardCharsets.UTF_8));
    }

    public String generateHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder stringBuilder = new StringBuilder(number.toString(16));
        while (stringBuilder.length() < 32) {
            stringBuilder.insert(0, '0');
        }
        return stringBuilder.toString();
    }

    public String getSHA(String message) {
        try {
            return generateHexString(obtainSHA(message));
        } catch (Exception e) {
            System.out.printf("An error has occured: " + e.getMessage());
        }
        return "";
    }
}  