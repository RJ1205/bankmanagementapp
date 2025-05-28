package com.bankapp;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMainMenu();
            String input = scanner.nextLine();
            clearConsole();
            switch (input) {
                case "1":
                    printImportMenu(scanner);
                    break;
                case "2":
                    printReportingMenu(scanner);
                    break;
                case "3":
                    printCreateAccMenu(scanner);
                    break;
                case "4":
                    printDepositMenu(scanner);
                    break;
                case "5":
                    printWithdrawalMenu(scanner);
                    break;
                case "6":
                    printShowBalanceMenu(scanner);
                    break;
                case "7":
                    running = false;
                    System.out.println("Exiting App! Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("Bankmanagementapp");
        System.out.println("1. Import");
        System.out.println("2. Reporting");
        System.out.println("3. Open a bank account");
        System.out.println("4. Deposit");
        System.out.println("5. Withdrawal");
        System.out.println("6. Show balance");
        System.out.println("7. Exit App");
        System.out.print("Enter your choice:");
    }


    private static void printImportMenu(Scanner scanner) {
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

    private  static void printReportingMenu(Scanner scanner) {
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
    
    private static void printCreateAccMenu(Scanner scanner) {
        System.out.println("Please enter your name:");
        String name = scanner.nextLine();
        clearConsole();
        System.out.println("Please enter your address:");
        String address = scanner.nextLine();
        clearConsole();
        System.out.println("Your name is : " + name + " and you live in " + address);
    }
    
    private static void printDepositMenu(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter the amount to deposit: ");
        String amount = scanner.nextLine();
        clearConsole();
        System.out.println("Depositing " + amount + " to account " + accountNumber + "...");
        
    }

    private static void printWithdrawalMenu(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter the amount to withdraw: ");
        String amount = scanner.nextLine();
        clearConsole();
        System.out.println("Withdrawing " + amount + " from account " + accountNumber + "...");
       
    }

    private static void printShowBalanceMenu(Scanner scanner) {
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        clearConsole();
        System.out.println("Current balance for account " + accountNumber + ": ...");
        
    }

    private static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
