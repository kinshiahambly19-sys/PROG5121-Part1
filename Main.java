/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

import java.util.Scanner;

/**
 *
 * @author Student
 */
public class Main {

    public static void main(String[] args) {
        


        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== USER REGISTRATION ===");
        System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Cell Phone Number (with int'l code, e.g., +27838968976): ");
        String cellPhone = scanner.nextLine();

        // Register user and output result
        String registrationMessage = login.registerUser(username, password, cellPhone, firstName, lastName);
        System.out.println("\n" + registrationMessage);

        // Proceed to login only if registration succeeded
        if (registrationMessage.contains("successfully captured")) {
            System.out.println("\n=== USER LOGIN ===");
            System.out.print("Enter Username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String loginPassword = scanner.nextLine();

            boolean loginSuccess = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(loginSuccess));
        }

        scanner.close();
    }

    }

