package br.com.joelf.sboot_virtual_card_management.users.domain.service;

import br.com.joelf.sboot_virtual_card_management.users.domain.entities.User;
import br.com.joelf.sboot_virtual_card_management.users.domain.port.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UUID create(User user) {
        return userRepository.create(user);
    }
}
