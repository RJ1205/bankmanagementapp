package com.bankapp;

import java.util.Scanner;

public class MenuHandler {
    private final Scanner scanner;

    public MenuHandler(Scanner scanner) {
        this.scanner = scanner;
    }
    /**
     * Run method to start the entire bank application.
     */
    public void run() {
        boolean running = true;

        while (running) {
            printMainMenu();
            String input = scanner.nextLine();
            clearConsole();
            switch (input) {
                case "1":
                    printImportMenu();
                    break;
                case "2":
                    printReportingMenu();
                    break;
                case "3":
                    printCreateAccMenu();
                    break;
                case "4":
                    printDepositMenu();
                    break;
                case "5":
                    printWithdrawalMenu();
                    break;
                case "6":
                    printShowBalanceMenu();
                    break;
                case "7":
                    printSearchMenu(scanner);
                    break;
                case "8":
                    running = false;
                    System.out.println("Exiting App! Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    // all methods below are for printing menus and handling user input
    private void printMainMenu() {
        System.out.println("Bankmanagement App");
        System.out.println("1. Import");
        System.out.println("2. Reporting");
        System.out.println("3. Open a bank account");
        System.out.println("4. Deposit");
        System.out.println("5. Withdrawal");
        System.out.println("6. Show balance");
        System.out.println("7. Search");
        System.out.println("8. Exit App");
        System.out.print("Enter your choice: ");
    }

    private void printImportMenu() {
        System.out.println("1. Import customers from file");
        System.out.println("2. Import accounts from file");
        System.out.println("3. Back to main menu");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        clearConsole();
        switch (choice) {
            case "1":
                System.out.println("Importing customers...");
                break;
            case "2":
                System.out.println("Importing accounts...");
                break;
            case "3":
                return;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void printReportingMenu() {
        System.out.println("1. Show all customers");
        System.out.println("2. Show account balance of a customer");
        System.out.println("3. Show all transactions of a customer");
        System.out.println("4. Back to main menu");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        clearConsole();
        switch (choice) {
            case "1":
                System.out.println("Displaying all customers...");
                break;
            case "2":
                System.out.println("Enter customer ID or name:");
                String customer = scanner.nextLine();
                System.out.println("Balance for " + customer + ": ...");
                break;
            case "3":
                System.out.println("Enter customer ID or name:");
                String customerForTx = scanner.nextLine();
                System.out.println("Transactions for " + customerForTx + ": ...");
                break;
            case "4":
                return;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void printCreateAccMenu() {
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        clearConsole();
        System.out.println("Please enter your address:");
        String address = scanner.nextLine();
        clearConsole();
        System.out.println("Your name is: " + name + " and you live in " + address);
    }

    private void printDepositMenu() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter the amount to deposit: ");
        String amount = scanner.nextLine();
        clearConsole();
        System.out.println("Depositing " + amount + " to account " + accountNumber + "...");
    }

    private void printWithdrawalMenu() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter the amount to withdraw: ");
        String amount = scanner.nextLine();
        clearConsole();
        System.out.println("Withdrawing " + amount + " from account " + accountNumber + "...");
    }

    private void printShowBalanceMenu() {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        clearConsole();
        System.out.println("Current balance for account " + accountNumber + ": ...");
    }

    private void printSearchMenu(Scanner scanner) {
        System.out.println("Search Menu");
        System.out.println("1. Search customer by name");
        System.out.println("2. Search customer by ID");
        System.out.println("3. Search account by account number");
        System.out.println("4. Search transaction by transaction ID");
        System.out.println("5. Back to main menu");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        clearConsole();

        switch (choice) {
            case "1":
                System.out.print("Enter the customer name: ");
                String name = scanner.nextLine();
                clearConsole();
                System.out.println("Searching for customer with name: " + name + "...");
                break;
            case "2":
                System.out.print("Enter the customer ID: ");
                String id = scanner.nextLine();
                clearConsole();
                System.out.println("Searching for customer with ID: " + id + "...");
                break;
            case "3":
                System.out.print("Enter the account number: ");
                String accNumber = scanner.nextLine();
                clearConsole();
                System.out.println("Searching for account with number: " + accNumber + "...");
                break;
            case "4":
                System.out.print("Enter the transaction ID: ");
                String txId = scanner.nextLine();
                clearConsole();
                System.out.println("Searching for transaction with ID: " + txId + "...");
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
