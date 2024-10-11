package com.eteration.simplebanking.utilities.results;

import com.eteration.simplebanking.enums.GeneralMessageEnumeration;

public class ErrorResult extends Result{
    public ErrorResult(boolean success) {
        super(success);
    }

    public ErrorResult(boolean success, String message) {
        super(success, message);
    }

    public ErrorResult(GeneralMessageEnumeration.AccountMessages accountMessages) {
    }
}
