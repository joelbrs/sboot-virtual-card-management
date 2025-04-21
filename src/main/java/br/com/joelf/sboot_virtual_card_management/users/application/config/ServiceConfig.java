package br.com.joelf.sboot_virtual_card_management.users.application.config;

import br.com.joelf.sboot_virtual_card_management.users.domain.port.UserRepository;
import br.com.joelf.sboot_virtual_card_management.users.domain.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(
            UserRepository userRepository,
            @Value("${app.credit.min-limit}") BigDecimal minCreditLimit,
            @Value("${app.credit.max-limit}") BigDecimal maxCreditLimit
    ) {
        return new UserService(userRepository, minCreditLimit, maxCreditLimit);
    }
}
