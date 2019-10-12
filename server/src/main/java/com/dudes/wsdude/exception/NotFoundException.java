package com.dudes.wsdude.exception;

public final class NotFoundException extends DudeException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(String message, String... args) {
        super(message, args);
    }

    public NotFoundException(String message, Throwable cause, String... args) {
        super(message, cause, args);
    }
}

