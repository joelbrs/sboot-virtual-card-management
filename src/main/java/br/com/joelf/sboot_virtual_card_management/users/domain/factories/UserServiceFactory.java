package br.com.joelf.sboot_virtual_card_management.users.domain.factories;

import br.com.joelf.sboot_virtual_card_management.users.domain.port.UserRepository;
import br.com.joelf.sboot_virtual_card_management.users.domain.service.EncryptionService;
import br.com.joelf.sboot_virtual_card_management.users.domain.service.UserService;
import br.com.joelf.sboot_virtual_card_management.users.domain.service.validation.UserAlreadyExistsValidation;
import br.com.joelf.sboot_virtual_card_management.users.domain.validator.Validator;
import br.com.joelf.sboot_virtual_card_management.users.domain.validator.ValidatorComposite;

import java.math.BigDecimal;
import java.util.List;

public class UserServiceFactory {
    public static UserService create(
            UserRepository userRepository,
            EncryptionService encryptionService,
            BigDecimal minCreditLimit,
            BigDecimal maxCreditLimit
    ) {
        Validator userAlreadyExists = new UserAlreadyExistsValidation(userRepository);
        ValidatorComposite validations = new ValidatorComposite(List.of(userAlreadyExists));

        return new UserService(userRepository, encryptionService, validations, minCreditLimit, maxCreditLimit);
    }
}
