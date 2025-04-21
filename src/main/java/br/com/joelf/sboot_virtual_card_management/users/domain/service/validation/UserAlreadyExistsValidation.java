package br.com.joelf.sboot_virtual_card_management.users.domain.service.validation;

import br.com.joelf.sboot_virtual_card_management.users.domain.entities.User;
import br.com.joelf.sboot_virtual_card_management.users.domain.exception.BusinessRuleException;
import br.com.joelf.sboot_virtual_card_management.users.domain.port.UserRepository;
import br.com.joelf.sboot_virtual_card_management.users.domain.validator.Validator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserAlreadyExistsValidation implements Validator {

    private final UserRepository userRepository;

    @Override
    public <T> RuntimeException isValid(T object) {
        User user = (User) object;
        boolean exists = userRepository.exists(user.getCpf(), user.getEmail());

        return exists ? new BusinessRuleException("An user with that informations already exists.") : null;
    }
}
