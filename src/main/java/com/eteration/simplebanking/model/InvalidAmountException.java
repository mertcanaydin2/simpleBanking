package com.eteration.simplebanking.model;

public class InvalidAmountException extends RuntimeException {

    private String code;
    private String message;

    public InvalidAmountException(String message) {
        this.message = message;
    }

    public InvalidAmountException(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
