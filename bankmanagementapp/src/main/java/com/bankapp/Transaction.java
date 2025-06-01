package com.bankapp;

import java.util.Date;

public class Transaction {
    private static int nextId = 1;

    private final int id;
    private final String type;
    private final double amount;
    private final Date timestamp;

    public Transaction(String type, double amount) {
        this.id = nextId++;
        this.type = type;
        this.amount = amount;
        this.timestamp = new Date();
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
