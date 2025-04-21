package br.com.joelf.sboot_virtual_card_management.virtual_card.domain.entities;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class VirtualCard {
    private UUID id;
    private UUID userId;
    private String cardNumber;
    private String cvv;
    private LocalDate expirationDate;
    private String alias;
    private BigDecimal creditLimit;
    private BigDecimal availableLimit;
    private VirtualCardStatus status;
    private LocalDateTime createdAt;
}
