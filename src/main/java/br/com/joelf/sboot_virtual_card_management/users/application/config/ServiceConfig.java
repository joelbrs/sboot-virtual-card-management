package br.com.joelf.sboot_virtual_card_management.users.application.config;

import br.com.joelf.sboot_virtual_card_management.users.domain.port.UserRepository;
import br.com.joelf.sboot_virtual_card_management.users.domain.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserService userService(
            UserRepository userRepository
    ) {
        return new UserService(userRepository);
    }
}
