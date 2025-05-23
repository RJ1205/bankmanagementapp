package com.bankapp;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMainMenu();
            String input = scanner.nextLine();
            switch (input) {
                case "1":
                    printImportMenu(scanner);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
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
    
    private static void clearConsole(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
