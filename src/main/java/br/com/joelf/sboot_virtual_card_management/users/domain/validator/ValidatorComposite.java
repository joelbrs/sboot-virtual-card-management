package br.com.joelf.sboot_virtual_card_management.users.domain.validator;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ValidatorComposite implements Validator {

    private final List<Validator> validators;

    @Override
    public <T> RuntimeException isValid(T object) {
        for (Validator validator : validators) {
            RuntimeException result = validator.isValid(object);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
