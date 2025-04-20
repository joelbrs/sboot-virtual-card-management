package br.com.joelf.sboot_virtual_card_management.users.application.presentation;

import br.com.joelf.sboot_virtual_card_management.users.application.presentation.dtos.users.CreateUserDto;
import br.com.joelf.sboot_virtual_card_management.users.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody CreateUserDto user) {
        return userService.create(user.domain());
    }
}
