package br.com.joelf.sboot_virtual_card_management.virtual_card.domain.factories;

import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.service.EncryptionService;

public class EncryptionServiceFactory {
    public static EncryptionService create(
            String algorithm,
            String secret
    ) {
        return new EncryptionService(algorithm, secret);
    }
}
