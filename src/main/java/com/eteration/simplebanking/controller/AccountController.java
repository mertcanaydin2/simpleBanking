package com.eteration.simplebanking.controller;

// This class is a place holder you can change the complete implementation

import com.eteration.simplebanking.model.dto.requests.CreateAccountRequest;
import com.eteration.simplebanking.model.dto.requests.CreateCreditRequest;
import com.eteration.simplebanking.model.dto.requests.CreatePhoneBillPaymentRequest;
import com.eteration.simplebanking.model.dto.requests.CreateWithdrawalRequest;
import com.eteration.simplebanking.model.dto.responses.AccountResponse;
import com.eteration.simplebanking.model.dto.responses.CreditResponse;
import com.eteration.simplebanking.model.dto.responses.WithdrawalResponse;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.utilities.results.DataResult;
import com.eteration.simplebanking.utilities.results.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account/v1")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/createAccount")
    public Result createAccount(@RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @GetMapping("/{accountNumber}")
    public AccountResponse getAccount(@PathVariable String accountNumber) {
        return accountService.findByAccountNumber(accountNumber);
    }

    @PostMapping("/credit")
    public ResponseEntity<TransactionStatus> credit(@RequestBody CreateCreditRequest request){
        return accountService.credit(request);
    }

    @PostMapping("/debit")
    public ResponseEntity<TransactionStatus> debit(@RequestBody CreateWithdrawalRequest request){
        return accountService.debit(request);
    }

    @PostMapping("/billPayment")
    public ResponseEntity<TransactionStatus> payment(@RequestBody CreatePhoneBillPaymentRequest request){
        return accountService.payment(request);
    }
}