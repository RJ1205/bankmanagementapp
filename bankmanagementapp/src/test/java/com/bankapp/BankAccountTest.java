package com.bankapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class BankAccountTest {
    
    @Test
    void testDepositNormalAmount() {
        BankAccount account = new BankAccount(1, 1);
        account.deposit(100.0);
        assertEquals(100.0, account.getBalance());
    }

    @Test
    void testWithdrawNormalAmount() {
        BankAccount account = new BankAccount(1, 1);
        account.deposit(100.0);
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance());
    }

    @Test
    void testDepositLessThanZero() {
        BankAccount account = new BankAccount(1, 1);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-10.0);
        });
        assertEquals("Deposit amount must be positive.", exception.getMessage());
    }

    @Test
    void testWithdrawLessThanZero(){
        BankAccount account = new BankAccount(1, 1);
        account.deposit(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw( -10.0 );
        });
        assertEquals("Withdrawal amount must be positive.", exception.getMessage());
    }

    @Test 
    void testWithdrawMoreThanBalance(){
        BankAccount account = new BankAccount(1, 1);
        account.deposit(100.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.withdraw(200.0);
        });
        assertEquals("Insufficient funds.", exception.getMessage());
    }
}
