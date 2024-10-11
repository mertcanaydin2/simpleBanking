package com.eteration.simplebanking.services.manager;

import com.eteration.simplebanking.enums.GeneralMessageEnumeration;
import com.eteration.simplebanking.enums.GeneralTypesEnumeration;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.dto.requests.CreatePhoneBillPaymentRequest;
import com.eteration.simplebanking.model.entity.*;
import com.eteration.simplebanking.repository.TransactionRepository;
import com.eteration.simplebanking.utilities.results.Result;
import com.eteration.simplebanking.utilities.results.SuccessResult;
import com.eteration.simplebanking.services.TransactionService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Result deposit(Account account, double amount) {
        String approvalCode = UUID.randomUUID().toString();
        Transaction transaction = new DepositTransaction(amount);
        transaction.setApprovalCode(approvalCode);
        transaction.setAccount(account);
        transaction.setType(GeneralTypesEnumeration.TransactionTypes.DEPOSIT.getName());
        account.setBalance(account.getBalance() + amount);
        transactionRepository.save(transaction);

        return new SuccessResult(GeneralMessageEnumeration.transactionMessages.DEPOSIT_TRANSACTION_SUCCESS.getMessage());
    }

    @Override
    public Result withdraw(Account account, double amount) {
        if(account.getBalance() < amount) {
            throw new InsufficientBalanceException(GeneralMessageEnumeration.AccountMessages.INSUFFICIENT_BALANCE.getMessage());
        }

        String approvalCode = UUID.randomUUID().toString();
        Transaction transaction = new WithdrawalTransaction(amount);
        transaction.setApprovalCode(approvalCode);
        transaction.setAccount(account);
        transaction.setType(GeneralTypesEnumeration.TransactionTypes.WITHDRAW.getName());
        account.setBalance(account.getBalance() - amount);
        transactionRepository.save(transaction);

        return new SuccessResult(GeneralMessageEnumeration.transactionMessages.WITHDRAW_TRANSACTION_SUCCESS.getMessage());
    }

    @Override
    public Result payment(Account account, CreatePhoneBillPaymentRequest request) {
        String approvalCode = UUID.randomUUID().toString();
        Transaction transaction = new PhoneBillPaymentTransaction(request.getAccountNumber(), request.getMsisdn(), request.getAmount());
        transaction.setApprovalCode(approvalCode);
        transaction.setAccount(account);
        transaction.setType(GeneralTypesEnumeration.TransactionTypes.PHONE_BILL_PAYMENT.getName());
        transactionRepository.save(transaction);

        return new SuccessResult(GeneralMessageEnumeration.transactionMessages.PHONE_BILL_PAYMENT_SUCCESS.getMessage());

    }


}
