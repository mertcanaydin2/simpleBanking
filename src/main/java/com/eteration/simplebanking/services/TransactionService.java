package com.eteration.simplebanking.services;

import com.eteration.simplebanking.model.dto.requests.CreatePhoneBillPaymentRequest;
import com.eteration.simplebanking.model.entity.Account;
import com.eteration.simplebanking.utilities.results.Result;

public interface TransactionService {

    Result deposit(Account account, double amount);

    Result withdraw(Account account, double amount);

    Result payment(Account account, CreatePhoneBillPaymentRequest request);

}
