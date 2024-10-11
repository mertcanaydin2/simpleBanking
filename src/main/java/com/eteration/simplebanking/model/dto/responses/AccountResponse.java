package com.eteration.simplebanking.model.dto.responses;

import com.eteration.simplebanking.model.entity.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private String accountNumber;
    private String owner;
    private double balance;
    private String createDate;
    private List<Transaction> transactions;

    public AccountResponse(String owner, String number) {
    }
}
