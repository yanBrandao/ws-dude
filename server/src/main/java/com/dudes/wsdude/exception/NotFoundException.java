package com.dudes.wsdude.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public final class NotFoundException extends DudeException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message, String... args) {
        super(message, args);
    }
}

