package com.eteration.simplebanking.services.manager;


import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.enums.GeneralMessageEnumeration;
import com.eteration.simplebanking.enums.GeneralStatusEnumeration;
import com.eteration.simplebanking.model.CheckAccountException;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.InvalidAmountException;
import com.eteration.simplebanking.model.dto.requests.CreateAccountRequest;
import com.eteration.simplebanking.model.dto.requests.CreateCreditRequest;
import com.eteration.simplebanking.model.dto.requests.CreatePhoneBillPaymentRequest;
import com.eteration.simplebanking.model.dto.requests.CreateWithdrawalRequest;
import com.eteration.simplebanking.model.dto.responses.AccountResponse;
import com.eteration.simplebanking.model.entity.Account;
import com.eteration.simplebanking.repository.AccountRepository;
import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.services.TransactionService;
import com.eteration.simplebanking.utilities.mapping.ModelMapperService;
import com.eteration.simplebanking.utilities.results.Result;
import com.eteration.simplebanking.utilities.results.SuccessResult;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

// This class is a place holder you can change the complete implementation
@Service
public class AccountServiceImpl implements AccountService {
    ModelMapperService modelMapperService;
    private AccountRepository accountRepository;
    TransactionService transactionService;
    Random random = new Random();

    public AccountServiceImpl(ModelMapperService modelMapperService, AccountRepository accountRepository, TransactionService transactionService) {
        this.modelMapperService = modelMapperService;
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    @Override
    public Result createAccount(CreateAccountRequest request) {
        Account account = this.modelMapperService.forRequest()
                .map(request, Account.class);
        account.setAccountNumber(createAccountNumber());
        accountRepository.save(account);

        return new SuccessResult(GeneralMessageEnumeration.AccountMessages.ACCOUNT_CREATED.getMessage());
    }

    @Override
    public AccountResponse findByAccountNumber(String accountNumber) {

        List<Account> accounts = accountRepository.findByAccountNumber(accountNumber);

        if (accounts != null && !accounts.isEmpty()) {
            List<AccountResponse> accountResponses = accounts.stream()
                    .map(account -> modelMapperService.forDto().map(account, AccountResponse.class))
                    .collect(Collectors.toList());

            return accountResponses.get(0);
        }
        return null;
    }

    @Override
    public ResponseEntity<TransactionStatus> credit(CreateCreditRequest request) {
        TransactionStatus transactionStatus = new TransactionStatus();
        Account account = accountRepository.findAllByAccountNumber(request.getAccountNumber());
        if (request.getAmount() <= 0) {
            throw new InvalidAmountException(GeneralMessageEnumeration.AccountMessages.INVALID_AMOUNT.getMessage());
        }
        transactionService.deposit(account, request.getAmount());
        transactionStatus.setStatus(GeneralStatusEnumeration.transactionStatus.TRANSACTION_OK.getStatusCode());
        transactionStatus.setApprovalCode(UUID.randomUUID().toString());

        return ResponseEntity.ok(transactionStatus);
    }

    @Override
    public ResponseEntity<TransactionStatus> debit(CreateWithdrawalRequest request) {
        Account account = accountRepository.findAllByAccountNumber(request.getAccountNumber());
        TransactionStatus transactionStatus = new TransactionStatus();
        if (request.getAmount() <= 0) {
            throw new InvalidAmountException(GeneralMessageEnumeration.AccountMessages.INVALID_AMOUNT.getMessage());
        }

        transactionService.withdraw(account, request.getAmount());
        transactionStatus.setStatus(GeneralStatusEnumeration.transactionStatus.TRANSACTION_OK.getStatusCode());
        transactionStatus.setApprovalCode(UUID.randomUUID().toString());
        return ResponseEntity.ok(transactionStatus);
    }

    @Override
    public ResponseEntity<TransactionStatus> payment(CreatePhoneBillPaymentRequest request) {
        Account account = accountRepository.findAllByAccountNumber(request.getAccountNumber());
        if (Objects.nonNull(account)) {
            if (account.getBalance() < request.getAmount()) {
                throw new InsufficientBalanceException(GeneralMessageEnumeration.AccountMessages.INSUFFICIENT_BALANCE.getMessage());
            }

            account.setBalance(account.getBalance() - request.getAmount());
            accountRepository.save(account);
            transactionService.payment(account, request);
            return ResponseEntity.ok(new TransactionStatus(GeneralStatusEnumeration.transactionStatus.TRANSACTION_OK.getStatusCode()));
        }
        throw new CheckAccountException(GeneralMessageEnumeration.AccountMessages.ACCOUNT_NOT_FOUND.getMessage());
    }

    private String createAccountNumber() {
        int firstPart = random.nextInt(900) + 100;
        int secondPart = random.nextInt(9000) + 1000;
        return firstPart + "-" + secondPart;

    }
}
