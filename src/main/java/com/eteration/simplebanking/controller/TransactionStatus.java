package com.eteration.simplebanking.controller;


// This class is a place holder you can change the complete implementation

import java.util.UUID;

public class TransactionStatus {

    private String status;
    private String approvalCode = UUID.randomUUID().toString();

    public TransactionStatus(String status) {
        this.status = status;
    }

    public TransactionStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }
}
