package com.eteration.simplebanking.model;

public class CheckAccountException extends RuntimeException {

    public CheckAccountException(String code) {
        super(code);
    }
}
