package com.bredex.bredex_demo.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class JobsValidationException extends RuntimeException {
    public JobsValidationException(final String message) {
        super(message);
    }
}
