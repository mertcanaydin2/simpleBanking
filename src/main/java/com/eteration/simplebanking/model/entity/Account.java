package com.eteration.simplebanking.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Account")
@AllArgsConstructor
// This class is a place holder you can change the complete implementation
public class Account extends AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "OWNER")
    private String owner;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "BALANCE")
    private double balance = 0.0;

    @OneToMany(mappedBy = "account")
    private List<Transaction> transactions;

    public Account() {
    }

    public Account(String owner, String number) {
        this.owner = owner;
        this.accountNumber = number;
    }

    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void post(Transaction transaction) {
        this.transactions.add(transaction);
    }

}
