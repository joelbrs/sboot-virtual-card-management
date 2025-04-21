package br.com.joelf.sboot_virtual_card_management.virtual_card.domain.factories;

import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.ports.VirtualCardRepository;
import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.service.EncryptionService;
import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.service.VirtualCardService;

public class VirtualCardServiceFactory {
    public static VirtualCardService create(
            VirtualCardRepository virtualCardRepository,
            EncryptionService encryptionService,
            String cardNumberPrefix,
            Integer cardExpirationOffset
    ) {
        return new VirtualCardService(virtualCardRepository, encryptionService, cardNumberPrefix, cardExpirationOffset);
    }
}
