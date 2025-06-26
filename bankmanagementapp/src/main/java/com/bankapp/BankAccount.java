package com.bankapp;

import java.util.ArrayList;
import java.util.List;


public class BankAccount {
    private final int id;
    private final int customerId;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    public BankAccount(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
        this.balance = 0.0;
    }

    public int getId() {
        return id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Deposit amount must be positive.");
        balance += amount;
        transactions.add(new Transaction("deposit", amount));
    }

    public void withdraw(double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (amount > balance)
            throw new IllegalArgumentException("Insufficient funds.");
        balance -= amount;
        transactions.add(new Transaction("withdrawal", amount));
    }

}
