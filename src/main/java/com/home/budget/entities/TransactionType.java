package com.home.budget.entities;

public enum TransactionType {

    EXPENSE("EXPENSE"),
    LONG_EXPENSE("LONG_EXPENSE"),
    INCOME("INCOME"),
    INVESTMENT("INVESTMENT"),
    LOAN("LOAN");

    private String name;

    TransactionType(String name) {
        this.name = name;
    }

    public static TransactionType getType(String name) {
        for (TransactionType value : TransactionType.values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return null;
    }
}
