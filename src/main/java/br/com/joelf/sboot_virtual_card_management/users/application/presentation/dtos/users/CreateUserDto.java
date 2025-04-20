package br.com.joelf.sboot_virtual_card_management.users.application.presentation.dtos.users;

import br.com.joelf.sboot_virtual_card_management.users.domain.entities.User;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

public record CreateUserDto(
        @NotBlank(message = "'name' attribute must not be blank.")
        @Size(min = 3, max = 255, message = "'name' attribute must have a minimum of 3 characters and max of 255.")
        String name,

        @CPF(message = "'cpf is not valid.")
        @NotBlank(message = "'cpf' attribute must not be blank.")
        @Size(min = 11, max = 11, message = "'cpf' attribute must have 11 characters.")
        String cpf,

        @NotBlank(message = "'email' attribute must not be blank.")
        @Email(message = "'email' attribute must be a valid email.")
        String email,

        @NotBlank(message = "'password' attribute must not be blank.")
        @Size(min = 8, max = 20, message = "'password' attribute must have a minimum of 8 characters and max of 20.")
        String password
) {
    public User domain() {
        return User.builder()
                .name(name)
                .cpf(cpf)
                .email(email)
                .password(password)
                .build();
    }
}
