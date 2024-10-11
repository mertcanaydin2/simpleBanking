package com.eteration.simplebanking.enums;

public class GeneralStatusEnumeration {

    public enum transactionStatus{
        TRANSACTION_OK("OK"),
        TRANSACTION_FAILED("FAILED");


        private String statusCode;

        transactionStatus(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getStatusCode() {
            return statusCode;
        }
    }
}
