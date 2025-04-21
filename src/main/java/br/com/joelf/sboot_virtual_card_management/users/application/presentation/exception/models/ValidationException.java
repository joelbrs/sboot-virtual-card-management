package br.com.joelf.sboot_virtual_card_management.users.application.presentation.exception.models;

import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidationException extends ResponseException {
    private final List<FieldMessage> messages = new ArrayList<>();

    public ValidationException(Instant timestamp, Integer status, String message, String path) {
        super(timestamp, status, message, path);
    }

    public void add(String field, String message) {
        messages.add(new FieldMessage(field, message));
    }
}