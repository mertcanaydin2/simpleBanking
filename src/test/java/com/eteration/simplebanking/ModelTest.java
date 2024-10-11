package com.eteration.simplebanking;



import static org.junit.jupiter.api.Assertions.assertTrue;

import com.eteration.simplebanking.model.entity.Account;
import com.eteration.simplebanking.model.entity.DepositTransaction;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.entity.WithdrawalTransaction;

import com.eteration.simplebanking.services.AccountService;
import com.eteration.simplebanking.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ModelTest {
	TransactionService transactionService;
	AccountService accountService;

	@BeforeEach
	void setTransactionService(){
		transactionService = Mockito.mock(TransactionService.class);
		accountService = Mockito.mock(AccountService.class);
	}

	@Test
	public void testCreateAccountAndSetBalance0() {
		Account account = new Account("Kerem Karaca", "17892");
		assertTrue(account.getOwner().equals("Kerem Karaca"));
		assertTrue(account.getAccountNumber().equals("17892"));
		assertTrue(account.getBalance() == 0);
	}

	@Test
	public void testDepositIntoBankAccount() {
		Account account = new Account("Demet Demircan", "526-7602");
		transactionService.deposit(account,100);
		assertTrue(account.getBalance() == 100);
	}

	@Test
	public void testWithdrawFromBankAccount() throws InsufficientBalanceException {
		Account account = new Account("Demet Demircan", "526-7602");
		transactionService.deposit(account,100);
		assertTrue(account.getBalance() == 100);
		transactionService.withdraw(account,50);
		assertTrue(account.getBalance() == 50);
	}

	@Test
	public void testWithdrawException() {
		Assertions.assertThrows( InsufficientBalanceException.class, () -> {
			Account account = new Account("Demet Demircan", "9834");
			transactionService.deposit(account,100);
			transactionService.withdraw(account,50);
		  });

	}
	
	@Test
	public void testTransactions() throws InsufficientBalanceException {
		// Create account
		Account account = new Account("Canan Kaya", "1234");
		assertTrue(account.getTransactions().size() == 0);

		// Deposit Transaction
		DepositTransaction depositTrx = new DepositTransaction(100);
		assertTrue(depositTrx.getDate() != null);
		account.post(depositTrx);
		assertTrue(account.getBalance() == 100);
		assertTrue(account.getTransactions().size() == 1);

		// Withdrawal Transaction
		WithdrawalTransaction withdrawalTrx = new WithdrawalTransaction(60);
		assertTrue(withdrawalTrx.getDate() != null);
		account.post(withdrawalTrx);
		assertTrue(account.getBalance() == 40);
		assertTrue(account.getTransactions().size() == 2);
	}
}
