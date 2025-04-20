package br.com.joelf.sboot_virtual_card_management.users.application.presentation.exception.handlers;

import br.com.joelf.sboot_virtual_card_management.users.application.presentation.exception.models.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidationException validationException(
            HttpServletRequest request,
            ConstraintViolationException ex
    ) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        String message = "Some fields are not valid.";
        ValidationException exception = new ValidationException(Instant.now(), status.value(), message, request.getRequestURI());

        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            exception.add(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return exception;
    }
}
