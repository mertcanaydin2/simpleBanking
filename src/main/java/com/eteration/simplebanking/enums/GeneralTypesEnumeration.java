package com.eteration.simplebanking.enums;

public class GeneralTypesEnumeration {

    public enum TransactionTypes{
        DEPOSIT("DEPOSIT", "Deposit Transaction"),
        WITHDRAW("WITHDRAW", "Withdraw Transaction"),
        PHONE_BILL_PAYMENT("PHONE_BILL_PAYMENT", "Phone Bill Payment"),
        ;


        private String name;
        private String description;

        TransactionTypes(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }
    }
}
