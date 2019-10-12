package com.dudes.wsdude.exception;

public class DudeException extends RuntimeException{
    private final String[] args;

    public DudeException(String message, String... args){
        super(message);
        this.args = args;
    }

    public DudeException(String message, Throwable cause, String... args){
        super(message, cause);
        this.args = args;
    }
}
