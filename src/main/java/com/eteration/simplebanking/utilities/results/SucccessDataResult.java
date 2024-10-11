package com.eteration.simplebanking.utilities.results;

import com.eteration.simplebanking.model.dto.responses.AccountResponse;

import java.util.List;

public class SucccessDataResult<T> extends DataResult<T> {

    public SucccessDataResult(T data, boolean success) {
        super(data, success);
    }

    public SucccessDataResult(T data, boolean success, String message) {
        super(data, success, message);
    }

    public SucccessDataResult(List<AccountResponse> accountResponses) {
        super();
    }
}
