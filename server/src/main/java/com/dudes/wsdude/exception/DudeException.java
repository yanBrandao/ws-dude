package com.dudes.wsdude.exception;

public class DudeException extends RuntimeException{

    public DudeException(String message, String... args){
        super(message);
    }
}
