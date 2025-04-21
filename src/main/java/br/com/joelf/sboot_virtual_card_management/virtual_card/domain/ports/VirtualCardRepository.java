package br.com.joelf.sboot_virtual_card_management.virtual_card.domain.ports;

import br.com.joelf.sboot_virtual_card_management.virtual_card.domain.entities.VirtualCard;

public interface VirtualCardRepository {
    VirtualCard create(VirtualCard virtualCard);
}
