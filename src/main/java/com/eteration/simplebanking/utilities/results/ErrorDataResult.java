package com.eteration.simplebanking.utilities.results;

public class ErrorDataResult<T> extends DataResult<T> {
    public ErrorDataResult(T data, boolean success) {
        super(data, success);
    }

    public ErrorDataResult(T data, boolean success, String message) {
        super(data, success, message);
    }
}
