package com.eteration.simplebanking;

import com.eteration.simplebanking.controller.AccountController;
import com.eteration.simplebanking.controller.TransactionStatus;
import com.eteration.simplebanking.model.InsufficientBalanceException;
import com.eteration.simplebanking.model.dto.requests.CreateCreditRequest;
import com.eteration.simplebanking.model.dto.requests.CreateWithdrawalRequest;
import com.eteration.simplebanking.model.dto.responses.AccountResponse;
import com.eteration.simplebanking.model.entity.Account;
import com.eteration.simplebanking.services.manager.AccountServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
class ControllerTests  {

    @Spy
    @InjectMocks
    private AccountController controller;
 
    @Mock
    private AccountServiceImpl service;

    
    @Test
    public void givenId_Credit_thenReturnJson()
    throws Exception {
        CreateCreditRequest request = new CreateCreditRequest();
        request.setAmount(1000.0);
        request.setAccountNumber("737-6118");
        AccountResponse accountResponse = new AccountResponse("Kerem Karaca", "737-6118");
        accountResponse.setOwner("Kerem Karaca");
        accountResponse.setAccountNumber("737-6118");


        doReturn(accountResponse).when(service).findByAccountNumber( "737-6118");
        ResponseEntity<TransactionStatus> result = controller.credit(request);
        verify(service, times(1)).findByAccountNumber("737-6118");
        assertEquals("OK", result.getBody().getStatus());
    }

    @Test
    public void givenId_CreditAndThenDebit_thenReturnJson()
    throws Exception {

        Account account = new Account("Kerem Karaca", "737-6118");
        AccountResponse accountResponse = new AccountResponse("Kerem Karaca", "737-6118");
        CreateCreditRequest creditRequest = new CreateCreditRequest();
        CreateWithdrawalRequest withdrawalRequest = new CreateWithdrawalRequest();

        creditRequest.setAccountNumber("737-6118");
        creditRequest.setAmount(1000.0);

        withdrawalRequest.setAccountNumber("737-6118");
        withdrawalRequest.setAmount(1000.0);

        doReturn(accountResponse).when(service).findByAccountNumber( "737-6118");
        ResponseEntity<TransactionStatus> result = controller.credit(creditRequest);
        ResponseEntity<TransactionStatus> result2 = controller.debit(withdrawalRequest);

        verify(service, times(2)).findByAccountNumber("737-6118");
        assertEquals("OK", result.getBody().getStatus());
        assertEquals("OK", result2.getBody().getStatus());
        assertEquals(950.0, account.getBalance(),0.001);
    }

    @Test
    public void givenId_CreditAndThenDebitMoreGetException_thenReturnJson()
    throws Exception {
        Assertions.assertThrows( InsufficientBalanceException.class, () -> {
            Account account = new Account("Kerem Karaca", "737-6118");

            CreateCreditRequest creditRequest = new CreateCreditRequest();
            CreateWithdrawalRequest withdrawalRequest = new CreateWithdrawalRequest();

            creditRequest.setAccountNumber("737-6118");
            creditRequest.setAmount(1000.0);

            withdrawalRequest.setAccountNumber("737-6118");
            withdrawalRequest.setAmount(1000.0);

            doReturn(account).when(service).findByAccountNumber( "737-6118");
            ResponseEntity<TransactionStatus> result = controller.credit(creditRequest);
            ResponseEntity<TransactionStatus> result2 = controller.debit(withdrawalRequest);

            assertEquals("OK", result.getBody().getStatus());
            assertEquals("OK", result2.getBody().getStatus());
            assertEquals(1000.0, account.getBalance(),0.001);

            verify(service, times(1)).findByAccountNumber("737-6118");

        });
    }

    @Test
    public void givenId_GetAccount_thenReturnJson()
    throws Exception {
        
        Account account = new Account("Kerem Karaca", "737-6118");

        doReturn(account).when(service).findByAccountNumber( "737-6118");
        AccountResponse result = controller.getAccount( "737-6118");
        verify(service, times(1)).findByAccountNumber("737-6118");
        assertEquals(account, result);
    }

}
