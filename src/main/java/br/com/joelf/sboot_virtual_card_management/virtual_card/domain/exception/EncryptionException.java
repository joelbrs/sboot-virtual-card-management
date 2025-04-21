package br.com.joelf.sboot_virtual_card_management.virtual_card.domain.exception;

public class EncryptionException extends RuntimeException {
    public EncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
