package br.com.joelf.sboot_virtual_card_management.users.domain.port;

import br.com.joelf.sboot_virtual_card_management.users.domain.entities.User;

import java.util.UUID;

public interface UserRepository {
    UUID create(User user);
    boolean exists(String cpf, String email);
}
