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
    void testImportCustomersNormalCase() {
        Bank bank = new Bank();
        List<Customer> imported = bank.importCustomers("src/test/resources/test_customers.csv");

        assertEquals(2, imported.size());
        assertEquals("Max Mustermann", imported.get(0).getName());
        assertEquals("Musterstraße 1", imported.get(0).getAddress());
    }

    @Test
    void testImportAccountsNormalCase() {
        Bank bank = new Bank();

        Customer c1 = bank.createCustomer("Max Mustermann", "Musterstraße 1");
        Customer c2 = bank.createCustomer("Erika Musterfrau", "Hauptstraße 99");

        List<BankAccount> imported = bank.importAccounts("src/test/resources/test_accounts.csv");

        assertEquals(2, imported.size());

        BankAccount a1 = imported.get(0);
        assertEquals(1, a1.getId());
        assertEquals(1, a1.getCustomerId());
        assertEquals(1000.0, a1.getBalance());

        BankAccount a2 = imported.get(1);
        assertEquals(2, a2.getId());
        assertEquals(2, a2.getCustomerId());
        assertEquals(2500.5, a2.getBalance());

        assertEquals(1, c1.getAccounts().size());
        assertEquals(a1, c1.getAccounts().get(0));

        assertEquals(1, c2.getAccounts().size());
        assertEquals(a2, c2.getAccounts().get(0));
    }

    @Test
    void testImportAccountsWithMissingFile() {
        Bank bank = new Bank();
        assertThrows(FileNotFoundException.class, () -> {
            bank.importAccounts("src/test/resources/does_not_exist.csv");
        });
    }

    @Test
    void testImportAccountsWithMissingCustomer() throws IOException {
        Bank bank = new Bank(); // keine Kunden angelegt
        Files.writeString(Path.of("src/test/resources/bad_accounts.csv"), "1,42,500.0");

        assertThrows(IllegalStateException.class, () -> {
            bank.importAccounts("src/test/resources/bad_accounts.csv");
        });
    }

    @Test
    void testImportAccountsWithDuplicateAccount() throws IOException {
        Bank bank = new Bank();
        Customer c = bank.createCustomer("Max", "Adresse");

        bank.createAccount(c.getId()); // Account 1 schon existiert

        Files.writeString(Path.of("src/test/resources/dupe_accounts.csv"), "1," + c.getId() + ",1000.0");

        assertThrows(IllegalStateException.class, () -> {
            bank.importAccounts("src/test/resources/dupe_accounts.csv");
        });
    }

}
