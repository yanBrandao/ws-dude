package com.dudes.wsdude.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidValueException extends DudeException {
    private static final long serialVersionUID = 1L;

    public InvalidValueException(String message, String... args) {
        super(message, args);
    }
}
