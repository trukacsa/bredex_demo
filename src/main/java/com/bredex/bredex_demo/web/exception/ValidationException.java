package com.bredex.bredex_demo.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidationException extends RuntimeException {
    public ValidationException(final String message) {
        super(message);
    }
    public ValidationException(final String message, final String variableName) {
        super(message + " (" + variableName + ")");
    }
}
