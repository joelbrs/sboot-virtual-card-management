package br.com.joelf.sboot_virtual_card_management.users.domain.service;

import br.com.joelf.sboot_virtual_card_management.users.domain.entities.User;
import br.com.joelf.sboot_virtual_card_management.users.domain.exception.BusinessRuleException;
import br.com.joelf.sboot_virtual_card_management.users.domain.port.UserRepository;
import br.com.joelf.sboot_virtual_card_management.users.domain.validator.Validator;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    private final Validator validator;

    private final BigDecimal minCreditLimit;
    private final BigDecimal maxCreditLimit;

    public UUID create(User user) {
        RuntimeException ex = validator.isValid(user);

        if (ex != null) {
            throw new BusinessRuleException(ex.getMessage(), ex);
        }

        Random random = new Random();

        //TODO: create a mock service to verify user's credit and calculate credit limit properly
        BigDecimal creditLimit =
                minCreditLimit.add(BigDecimal.valueOf(random.nextDouble() * maxCreditLimit.subtract(minCreditLimit).doubleValue()));
        user.setCreditLimit(creditLimit);
        user.setPassword(encryptionService.encrypt(user.getPassword()));
        return userRepository.create(user);
    }
}
