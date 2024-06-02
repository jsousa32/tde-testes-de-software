package com.atividade.tde.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The Class ExceptionController
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/06/2024
 */
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorHandlingFields handleMethodArgumentException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        List<String> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .filter(fieldError -> !Objects.isNull(fieldError.getDefaultMessage()) && !fieldError.getDefaultMessage().isBlank())
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());

        String errorMessage = formatErrorMessage(errorMessages);

        return new ErrorHandlingFields(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),
                request.getServletPath(), request.getMethod(), errorMessage);
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorHandling> handleUserException(GlobalException exception, HttpServletRequest request) {
        return ResponseEntity.status(exception.getHttpStatus())
                .body(
                        new ErrorHandling(LocalDateTime.now(), exception.getHttpStatus().value(), request.getServletPath(), request.getMethod(),
                                exception.getMessage())
                );
    }


    private String formatErrorMessage(List<String> errorMessages) {
        if (errorMessages.isEmpty()) {
            return "";
        }

        return String.join(", ", errorMessages);
    }


    public record ErrorHandlingFields(LocalDateTime timestamps, Integer statusCode, String path, String method, String message) {
    }

    public record ErrorHandling(LocalDateTime timestamps, Integer statusCode, String path, String method, String message) {
    }
}
