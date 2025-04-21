package br.com.joelf.sboot_virtual_card_management.users.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class User {
    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String password;
    private BigDecimal creditLimit;
    private Boolean isBlocked;
    private LocalDateTime createdAt;
}
