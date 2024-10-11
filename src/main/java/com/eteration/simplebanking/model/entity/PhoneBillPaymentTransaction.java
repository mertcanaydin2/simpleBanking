package com.eteration.simplebanking.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneBillPaymentTransaction extends Transaction {

    private String phoneNumber;

    public PhoneBillPaymentTransaction(String accountNumber, String phoneNumber, double amount) {
        super(amount);
        this.phoneNumber = phoneNumber;
    }

}
