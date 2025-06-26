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

    /**
     * Creates a new customer and adds them to the customers map.
     * @param name name of the customer
     * @param address address of the customer
     */

    public Customer createCustomer(String name, String address) {
        Customer customer = new Customer(customerId++, name, address);
        customers.put(customer.getId(), customer);
        return customer;
    }
    /**
     * Creates a new banking account with no money on it for the given customer id
     * @param customerId id of the customer
     * @throws IllegalArgumentExceptions when the customer id is not valid
     */

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

    /**
     * imports customers from a given csv file 
     * @param filePath path to the file to be imported
     * @throws FileNotFoundException if the file does not exist
     */

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

    /**
     * imports bank accounts from the given csv file
     * @param filepath path of the file to be imported
     * @throws FileNotFoundException when the file does not exist
     * @throws IllegalArgumentException when either the account already exists or when the customer does not exist
     */

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
