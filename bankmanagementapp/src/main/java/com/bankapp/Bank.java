package com.bankapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Customer> importCustomers(String filePath) throws FileNotFoundException {

        if (!Files.exists(Paths.get(filePath))) {
            throw new FileNotFoundException("Datei nicht gefunden: " + filePath);
        }

        List<Customer> importedCustomers = FileImporter.importCustomersFromCSV(filePath);
        List<Customer> addedCustomers = new ArrayList<>();

        for (Customer temp : importedCustomers) {
            Customer created = createCustomer(temp.getName(), temp.getAddress());
            addedCustomers.add(created);
        }

        return addedCustomers;
    }

    public List<BankAccount> importAccounts(String filepath) throws IOException {
        if (!Files.exists(Paths.get(filepath))) {
            throw new FileNotFoundException("Datei nicht gefunden: " + filepath);
        }

        List<BankAccount> importedAccounts = FileImporter.importAccountsFromCSV(filepath, customers);
        List<BankAccount> addedAccounts = new ArrayList<>();

        for (BankAccount account : importedAccounts) {
            if (!customers.containsKey(account.getCustomerId())) {
                throw new IllegalStateException("Kunde mit ID " + account.getCustomerId() + " existiert nicht.");
            }
            if (accounts.containsKey(account.getId())) {
                throw new IllegalStateException("Konto mit ID " + account.getId() + " existiert bereits.");
            }

            accounts.put(account.getId(), account);
            accountId = Math.max(accountId, account.getId() + 1);
            customers.get(account.getCustomerId()).getAccounts().add(account);

            addedAccounts.add(account);
        }

        return addedAccounts;
    }

}
