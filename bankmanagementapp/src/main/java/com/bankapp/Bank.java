package com.bankapp;
import java.util.HashMap;
import java.util.Map;


public class Bank {
    private Map<Integer, Customer> customers = new HashMap();
    private Map<Integer, BankAccount> accounts = new HashMap<>();
    private int accountId = 1;
    private int customerId = 1;

    public Customer createCustomer(String name, String address) {
        Customer customer = new Customer(customerId++, name, address);
        customers.put(customer.getId(), customer);
        return customer;
    }
    
}
