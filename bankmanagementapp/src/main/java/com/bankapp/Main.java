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
    }


    private static void printImportMenu(Scanner scanner) {
        
    }

    private  static void printReportingMenu(Scanner scanner) {
    

    }   
    
    private static void printCreateAccMenu(Scanner scanner) {

    }
    
    private static void printDepositMenu(Scanner scanner) {

    }

    private static void printWithdrawalMenu(Scanner scanner) {

    }

    private static void printShowBalanceMenu(Scanner scanner) {
        
    }

    private static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
