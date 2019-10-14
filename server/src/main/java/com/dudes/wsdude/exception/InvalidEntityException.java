package com.dudes.wsdude.exception;

public class InvalidEntityException extends DudeException{
    public static final long serialVersionUID = 1L;

    public InvalidEntityException(String message, String... args) {
        super(message, args);
    }

}
