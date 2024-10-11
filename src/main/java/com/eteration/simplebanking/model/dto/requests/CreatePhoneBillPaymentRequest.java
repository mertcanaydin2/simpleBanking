package com.eteration.simplebanking.model.dto.requests;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePhoneBillPaymentRequest {

    @NotNull
    private String msisdn;

    @NotNull
    private String accountNumber;

    @NotNull
    private double amount;
}
