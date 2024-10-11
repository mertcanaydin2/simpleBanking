package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation
public class InsufficientBalanceException extends RuntimeException {

    private String code;

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public InsufficientBalanceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}

