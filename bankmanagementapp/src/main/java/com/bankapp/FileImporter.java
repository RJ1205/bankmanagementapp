package com.bankapp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileImporter {

    public static List<Customer> importCustomersFromCSV(String filepath) {
        List<Customer> customers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    Customer customer = new Customer(0, "", ""); // Dummy-Werte initial
                    customer.setName(parts[0].trim());
                    customer.setAddress(parts[1].trim());
                    customers.add(customer);
                }
            }

        } catch (IOException e) {
            System.err.println("Fehler beim Einlesen der Kunden-Datei: " + e.getMessage());
        }

        return customers;
    }

    public static List<BankAccount> importAccountsFromCSV(String filepath, Map<Integer, Customer> customerMap) {
        List<BankAccount> accounts = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    int accountId = Integer.parseInt(parts[0].trim());
                    int customerId = Integer.parseInt(parts[1].trim());
                    double balance = Double.parseDouble(parts[2].trim());

                    if (!customerMap.containsKey(customerId)) {
                        throw new IllegalStateException("Kunde mit ID " + customerId + " existiert nicht.");
                    }

                    BankAccount account = new BankAccount(accountId, customerId);
                    if (balance > 0) {
                        account.deposit(balance);
                    }
                    accounts.add(account);
                }
            }

        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Konten-Datei: " + e.getMessage());
        }

        return accounts;
    }
}
