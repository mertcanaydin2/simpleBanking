package com.eteration.simplebanking.model.dto.requests;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCreditRequest {

    @NotNull
    private String accountNumber;

    @NotNull
    private double amount;
}
