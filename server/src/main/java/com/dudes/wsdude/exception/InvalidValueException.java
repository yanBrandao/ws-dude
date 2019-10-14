package com.dudes.wsdude.exception;

public class InvalidValueException extends DudeException {
    private static final long serialVersionUID = 1L;

    public InvalidValueException(String message, String... args) {
        super(message, args);
    }
}
