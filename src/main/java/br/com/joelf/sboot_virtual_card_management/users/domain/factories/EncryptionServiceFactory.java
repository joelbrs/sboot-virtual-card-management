package br.com.joelf.sboot_virtual_card_management.users.domain.factories;

import br.com.joelf.sboot_virtual_card_management.users.domain.service.EncryptionService;

public class EncryptionServiceFactory {
    public static EncryptionService create(
            String algorithm
    ) {
        return new EncryptionService(algorithm);
    }
}
