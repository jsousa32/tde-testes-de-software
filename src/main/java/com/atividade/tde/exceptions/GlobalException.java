package com.atividade.tde.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The Class GlobalException
 *
 * @author João Lucas Silva de Sousa
 * @sincer 01/06/2024
 */
@Getter
public class GlobalException extends RuntimeException {

    private final String message;

    private final HttpStatus httpStatus;

    public GlobalException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
