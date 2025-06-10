package com.bankapp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BankTest {

    @Test
    void testCreateCustomer() {
        Bank bank = new Bank();

        Customer customer = bank.createCustomer("Alice", "Main Street 1");
        Customer customer2 = bank.createCustomer("Bob", "Second Street 2");
        assertNotNull(customer, "Customer should not be null");
        assertEquals("Alice", customer.getName(), "Customer name should match");
        assertEquals("Main Street 1", customer.getAddress(), "Customer address should match");
        assertEquals(1, customer.getId(), "First customer should have ID 1");
        assertEquals(2, customer2.getId(), "Second customer should have ID 2");
    }

    @Test
    void testCreateAccountForExistingCustomer() {
        Bank bank = new Bank();

        Customer customer = bank.createCustomer("Max Mustermann", "Musterstraße 1");
        int customerId = customer.getId();
        BankAccount account = bank.createAccount(customerId);
        assertNotNull(account, "Account should not be null");
        assertEquals(customerId, account.getCustomerId(), "Customer ID should match");
        assertTrue(bank.getAccounts().containsKey(account.getId()), "Account should be in the accounts map");
        assertTrue(customer.getAccounts().contains(account), "Customer should have the account in their list");
    }

    @Test
    void testCreateAccountForNonexistentCustomerThrowsException() {
        Bank bank = new Bank();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            bank.createAccount(999);
        });

        assertEquals("Customer with ID 999 does not exist.", exception.getMessage());
    }

    @Test
    void testCreateCustomerNormalCase() throws IOException {
        Bank bank = new Bank();

        Path tempFile = Files.createTempFile("temp_customers", ".csv");
        Files.writeString(tempFile,
                "Alice,Main Street 1\n" +
                        "Bob,Second Street 2");

        List<Customer> customers = bank.importCustomers(tempFile.toString());

        assertEquals(2, customers.size(), "Es sollten zwei Kunden importiert werden");

        Customer customer1 = customers.get(0);
        Customer customer2 = customers.get(1);

        assertEquals("Alice", customer1.getName());
        assertEquals("Main Street 1", customer1.getAddress());
        assertEquals(1, customer1.getId());

        assertEquals("Bob", customer2.getName());
        assertEquals("Second Street 2", customer2.getAddress());
        assertEquals(2, customer2.getId());

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testCreateAccountForImportedCustomer() throws IOException {
        Bank bank = new Bank();

        Path customerFile = Files.createTempFile("temp_customers", ".csv");
        Files.writeString(customerFile, "Max Mustermann,Musterstraße 1");
        List<Customer> customers = bank.importCustomers(customerFile.toString());

        Customer customer = customers.get(0);
        int customerId = customer.getId();

        Path accountFile = Files.createTempFile("temp_accounts", ".csv");
        Files.writeString(accountFile, "1," + customerId + ",1000.0");

        List<BankAccount> accounts = bank.importAccounts(accountFile.toString());

        assertEquals(1, accounts.size());
        BankAccount account = accounts.get(0);

        assertEquals(customerId, account.getCustomerId());
        assertTrue(bank.getAccounts().containsKey(account.getId()));
        assertTrue(customer.getAccounts().contains(account));

        Files.deleteIfExists(customerFile);
        Files.deleteIfExists(accountFile);
    }

    @Test
    void testImportAccountsWithMissingFile() {
        Bank bank = new Bank();
        assertThrows(FileNotFoundException.class, () -> {
            bank.importAccounts("src/test/resources/does_not_exist.csv");
        });
    }

    @Test
    void testImportCustomerWithMissingFile(){
        Bank bank = new Bank();
        assertThrows(FileNotFoundException.class, () -> {
            bank.importAccounts("src/test/resources/does_not_exist.csv");
        });
    }

    @Test
    void testImportAccountsWithMissingCustomer() throws IOException {
        Bank bank = new Bank();

        Path tempFile = Files.createTempFile("bad_accounts", ".csv");
        Files.writeString(tempFile, "1,42,500.0");

        assertThrows(IllegalStateException.class, () -> {
            bank.importAccounts(tempFile.toString());
        });

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testImportAccountsWithDuplicateAccount() throws IOException {
        Bank bank = new Bank();
        Customer c = bank.createCustomer("Max", "Adresse");
        bank.createAccount(c.getId());

        Path tempFile = Files.createTempFile("dupe_accounts", ".csv");
        Files.writeString(tempFile, "1," + c.getId() + ",1000.0");

        assertThrows(IllegalStateException.class, () -> {
            bank.importAccounts(tempFile.toString());
        });

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testImportAccountsWithInvalidCSVFormat() throws IOException {
        Bank bank = new Bank();
        Path tempFile = Files.createTempFile("invalid_accounts", ".csv");
        Files.writeString(tempFile, "not,a,number");

        assertThrows(Exception.class, () -> {
            bank.importAccounts(tempFile.toString());
        });

        Files.deleteIfExists(tempFile);
    }

}
