package com.bredex.bredex_demo.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ClientValidationException extends RuntimeException {
    public ClientValidationException(final String message, final String email) {
        super(message);
    }
}
