package com.eteration.simplebanking.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// This class is a place holder you can change the complete implementation

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public abstract class Transaction extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    private Long id;

    @Column(name = "DATE")
    private String date;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "APPROVAL_CODE")
    private String approvalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Account account;

    public Transaction(double amount) {
        this.amount = amount;
        this.date = LocalDateTime.now().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
