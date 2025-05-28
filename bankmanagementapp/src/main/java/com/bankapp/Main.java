package com.bankapp;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuHandler menuHandler = new MenuHandler(scanner);
        menuHandler.run(); // zentrale Menüsteuerung
    }
}
