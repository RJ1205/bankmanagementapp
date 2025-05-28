package com.bankapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class BankTest {

    @Test
    public void testCreateCustomer() {
        Bank bank = new Bank();

        Customer customer = bank.createCustomer("Alice", "Main Street 1");
        Customer customer2 = bank.createCustomer("Bob", "Second Street 2");
        assertNotNull(customer, "Customer should not be null");
        assertEquals("Alice", customer.getName(), "Customer name should match");
        assertEquals("Main Street 1", customer.getAddress(), "Customer address should match");
        assertEquals(1, customer.getId(), "First customer should have ID 1");
        assertEquals(2, customer2.getId(), "Second customer should have ID 2");
    }
}
