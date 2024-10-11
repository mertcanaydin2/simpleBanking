package com.eteration.simplebanking.utilities.results;

import com.eteration.simplebanking.enums.GeneralMessageEnumeration;

public class SuccessResult extends Result{

    public SuccessResult() {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }

}
