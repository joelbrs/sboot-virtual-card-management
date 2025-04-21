package br.com.joelf.sboot_virtual_card_management.users.domain.validator;

public interface Validator {
    <T> RuntimeException isValid(T object);
}
