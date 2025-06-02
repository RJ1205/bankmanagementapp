package com.bankapp;

import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class Bank {
    private Map<Integer, Customer> customers = new HashMap();
    private Map<Integer, BankAccount> accounts = new HashMap<>();
    private int accountId = 1;
    private int customerId = 1;

    public Map<Integer, BankAccount> getAccounts() {
        return accounts;
    }

    public Customer createCustomer(String name, String address) {
        Customer customer = new Customer(customerId++, name, address);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public BankAccount createAccount(int customerId) {
        Customer customer = customers.get(customerId);

        if (customer == null) {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist.");
        }

        BankAccount account = new BankAccount(accountId++, customerId);
        accounts.put(account.getId(), account);

        customer.getAccounts().add(account);

        return account;
    }
    
    public List<Customer> importCustomers(String filePath){
        // Muss noch implementiert werden 
    }

    public List<BankAccount> importAccounts(String filePath){
        // Muss noch implementiert werden
    }
}
