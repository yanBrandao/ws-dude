package com.dudes.wsdude.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InvalidEntityException extends DudeException{
    public static final long serialVersionUID = 1L;

    public InvalidEntityException(String message, String... args) {
        super(message, args);
    }

}
