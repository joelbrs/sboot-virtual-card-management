package br.com.joelf.sboot_virtual_card_management.users.application.presentation.exception.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ResponseException {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}