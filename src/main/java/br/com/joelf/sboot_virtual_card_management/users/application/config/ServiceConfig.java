package br.com.joelf.sboot_virtual_card_management.users.application.config;

import br.com.joelf.sboot_virtual_card_management.users.domain.factories.UserServiceFactory;
import br.com.joelf.sboot_virtual_card_management.users.domain.port.UserRepository;
import br.com.joelf.sboot_virtual_card_management.users.domain.service.EncryptionService;
import br.com.joelf.sboot_virtual_card_management.users.domain.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ServiceConfig {

    @Bean
    public EncryptionService encryptionService(
            @Value("${app.encryption.algorithm}") String encryptionAlgorithm
    ) {
        return new EncryptionService(encryptionAlgorithm);
    }

    @Bean
    public UserService userService(
            UserRepository userRepository,
            EncryptionService encryptionService,
            @Value("${app.credit.min-limit}") BigDecimal minCreditLimit,
            @Value("${app.credit.max-limit}") BigDecimal maxCreditLimit
    ) {
        return UserServiceFactory.create(userRepository, encryptionService, minCreditLimit, maxCreditLimit);
    }
}
