package br.com.joelf.sboot_virtual_card_management.virtual_card.domain.service;

import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.entities.VirtualCard;
import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.entities.VirtualCardStatus;
import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.ports.VirtualCardRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Random;

@RequiredArgsConstructor
public class VirtualCardService {

    private final VirtualCardRepository virtualCardRepository;
    private final EncryptionService encryptionService;

    private final String cardNumberPrefix;
    private final Integer cardExpirationOffset;

    public VirtualCard create(VirtualCard virtualCard) {
        //TODO: validate user's total limit available
        //TODO: update schema.sql to set status active as default
        Random random = new Random();
        String cvv = String.format("%03d", random.nextInt(1000));

        virtualCard.setCardNumber(this.getRandomCardNumber());
        virtualCard.setCvv(encryptionService.encrypt(cvv));
        virtualCard.setExpirationDate(LocalDate.now().plusMonths(cardExpirationOffset));
        virtualCard.setStatus(VirtualCardStatus.ACTIVE);
        return virtualCardRepository.create(virtualCard);
    }

    private String getRandomCardNumber() {
        StringBuilder complementaryNumbers = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            complementaryNumbers.append(random.nextInt(10));
        }

        return cardNumberPrefix + complementaryNumbers;
    }
}
