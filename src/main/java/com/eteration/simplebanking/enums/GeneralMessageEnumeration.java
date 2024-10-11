package com.eteration.simplebanking.enums;

public class GeneralMessageEnumeration {

    public enum AccountMessages{
        ACCOUNT_CREATED("Account created"),
        ACCOUNT_NOT_CREATED("Account not created"),
        ACCOUNT_ALREADY_EXISTS("Account already exists"),
        CREDIT_SUCCESSFUL("Credit successful"),
        CREDIT_NOT_SUCCESSFUL("Credit not successful"),
        INVALID_AMOUNT("Invalid amount"),
        INSUFFICIENT_BALANCE("Insufficient balance"),
        ACCOUNT_NOT_FOUND("Account not found"),
        ;


        private String message;

        AccountMessages(String message) {
            this.message = message;
        }
        public String getMessage() {return message;}
    }

    public enum transactionMessages{
        TRANSACTION_CREATED("Transaction created"),
        TRANSACTION_NOT_CREATED("Transaction not created"),
        DEPOSIT_TRANSACTION_SUCCESS("Deposit transaction successful"),
        DEPOSIT_TRANSACTION_FAILED("Deposit transaction failed"),
        WITHDRAW_TRANSACTION_SUCCESS("Withdraw transaction successful"),
        WITHDRAW_TRANSACTION_FAILED("Withdraw transaction failed"),
        PHONE_BILL_PAYMENT_SUCCESS("Phone bill payment successful"),
        PHONE_BILL_PAYMENT_FAILED("Phone bill payment failed"),
        ;


        private String message;

        transactionMessages(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
