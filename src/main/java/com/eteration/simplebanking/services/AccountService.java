package com.eteration.simplebanking.services;

import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.dto.requests.CreateAccountRequest;
import com.eteration.simplebanking.model.dto.requests.CreateCreditRequest;
import com.eteration.simplebanking.model.dto.requests.CreatePhoneBillPaymentRequest;
import com.eteration.simplebanking.model.dto.requests.CreateWithdrawalRequest;
import com.eteration.simplebanking.model.dto.responses.AccountResponse;
import com.eteration.simplebanking.model.dto.responses.CreditResponse;
import com.eteration.simplebanking.model.dto.responses.PhoneBillPaymentResponse;
import com.eteration.simplebanking.model.dto.responses.WithdrawalResponse;
import com.eteration.simplebanking.model.entity.Account;
import com.eteration.simplebanking.utilities.results.DataResult;
import com.eteration.simplebanking.utilities.results.Result;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AccountService {

    Result createAccount(CreateAccountRequest createAccountRequest);

    AccountResponse findByAccountNumber(String accountNumber);

    ResponseEntity<TransactionStatus> credit(CreateCreditRequest createCreditRequest);

    ResponseEntity<TransactionStatus> debit(CreateWithdrawalRequest createWithdrawalRequest);

    ResponseEntity<TransactionStatus> payment(CreatePhoneBillPaymentRequest request);
}
