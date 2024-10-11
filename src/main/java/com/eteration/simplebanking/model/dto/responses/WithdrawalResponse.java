package com.eteration.simplebanking.model.dto.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WithdrawalResponse {

    private String status;
    private String approvalCode;
}
