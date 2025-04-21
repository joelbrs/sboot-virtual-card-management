package br.com.joelf.sboot_virtual_card_management.virtual_card.application.config;

import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.factories.EncryptionServiceFactory;
import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.factories.VirtualCardServiceFactory;
import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.ports.VirtualCardRepository;
import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.service.EncryptionService;
import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.service.VirtualCardService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public EncryptionService encryptionService(
            @Value("${app.encryption.algorithm}") String algorithm,
            @Value("${app.encryption.secret-key}") String secretKey
    ) {
        return EncryptionServiceFactory.create(algorithm, secretKey);
    }

    @Bean
    public VirtualCardService virtualCardService(
            VirtualCardRepository virtualCardRepository,
            EncryptionService encryptionService,
            @Value("${app.card.number-prefix}") String cardNumberPrefix,
            @Value("${app.card.expiration-offset}") Integer cardExpirationOffset
    ) {
        return VirtualCardServiceFactory.create(virtualCardRepository, encryptionService, cardNumberPrefix, cardExpirationOffset);
    }
}
