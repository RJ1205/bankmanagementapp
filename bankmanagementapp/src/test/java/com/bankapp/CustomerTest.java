package com.bankapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(1, "Max Mustermann", "Musterstraße 123");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1, customer.getId());
        assertEquals("Max Mustermann", customer.getName());
        assertEquals("Musterstraße 123", customer.getAddress());
        assertTrue(customer.getAccounts().isEmpty());
    }

    @Test
    void testSetters() {
        customer.setId(42);
        customer.setName("Erika Musterfrau");
        customer.setAddress("Beispielweg 456");

        assertEquals(42, customer.getId());
        assertEquals("Erika Musterfrau", customer.getName());
        assertEquals("Beispielweg 456", customer.getAddress());
    }

}
