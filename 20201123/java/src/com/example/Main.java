package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("How old are you?");

        String ageAsString = scanner.nextLine();
        int age = Integer.parseInt(ageAsString);

        if (age < 20) {
            System.out.println("You're young!");
        }
        if (age > 10000) {
            System.out.println("Are you god?");
        }

        System.out.println("What's your name?");

        String name = scanner.nextLine();

        System.out.println("Hello, " + name);
    }
}
