package br.com.joelf.sboot_virtual_card_management.users.domain.service;

import br.com.joelf.sboot_virtual_card_management.users.domain.exception.EncryptionException;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
public class EncryptionService {

    private final String algorithm;

    public String encrypt(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return this.bytesToHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptionException(e.getMessage(), e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
