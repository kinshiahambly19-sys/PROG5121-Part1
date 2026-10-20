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
        Scanner input = new Scanner(System.in);
        Login loginApp = new Login();

        // Step 1: User Registration & Login Demonstration
        System.out.println("=== USER REGISTRATION ===");
        System.out.print("Enter First Name: ");
        String firstName = input.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = input.nextLine();

        System.out.print("Enter Username (<= 5 chars with '_'): ");
        String username = input.nextLine();

        System.out.print("Enter Password (>= 8 chars, 1 Upper, 1 Digit, 1 Special): ");
        String password = input.nextLine();

        System.out.print("Enter Cell Phone Number (e.g. +2712345678): ");
        String cell = input.nextLine();

        String regStatus = loginApp.registerUser(username, password, firstName, lastName, cell);
        System.out.println(regStatus);

        if (!regStatus.equals("The username and password meets all requirements and the user has been registered successfully.")) {
            System.out.println("Registration failed. Application exiting.");
            return;
        }

        System.out.println("\n=== USER LOGIN ===");
        System.out.print("Enter Username: ");
        String loginUser = input.nextLine();

        System.out.print("Enter Password: ");
        String loginPass = input.nextLine();

        boolean isAuthenticated = loginApp.loginUser(loginUser, loginPass);
        System.out.println(loginApp.returnLoginStatus(isAuthenticated));

        // Part 2 Messaging application only starts if login succeeds
        if (isAuthenticated) {
            System.out.println("\nWelcome to QuickChat.");

            boolean running = true;
            int totalMessagesSent = 0;

            while (running) {
                System.out.println("\nPlease choose an option from the menu:");
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Option: ");

                int menuOption = input.nextInt();
                input.nextLine(); // Clear buffer

                switch (menuOption) {
                    case 1:
                        System.out.print("How many messages would you like to enter? ");
                        int numMessages = input.nextInt();
                        input.nextLine(); // Clear buffer

                        for (int i = 0; i < numMessages; i++) {
                            System.out.println("\n--- Entering Message " + (i + 1) + " of " + numMessages + " ---");

                            System.out.print("Enter Recipient Number: ");
                            String recipient = input.nextLine();

                            System.out.print("Enter Message Content: ");
                            String content = input.nextLine();

                            Message msg = new Message(i, recipient, content);

                            // Display Validation Feedback
                            System.out.println(msg.checkRecipientCell());
                            System.out.println(msg.checkMessageLength());

                            if (msg.checkMessageLength().equals("Message ready to send.")) {
                                System.out.println("\nChoose an action for this message:");
                                System.out.println("1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message");
                                System.out.print("Action choice: ");

                                int actionChoice = input.nextInt();
                                input.nextLine(); // Clear buffer

                                String actionResult = msg.SentMessage(actionChoice);
                                System.out.println(actionResult);

                                if (actionChoice == 1) {
                                    totalMessagesSent++;
                                }

                                System.out.println("\n--- Message Summary ---");
                                System.out.println(msg.printMessages());
                            }
                        }
                        System.out.println("\nTotal messages sent in this session: " + totalMessagesSent);
                        break;

                    case 2:
                        System.out.println("Coming Soon.");
                        break;

                    case 3:
                        running = false;
                        System.out.println("Exiting QuickChat. Total messages sent: " + totalMessagesSent);
                        break;

                    default:
                        System.out.println("Invalid option. Please enter 1, 2, or 3.");
                        break;
                }
            }
        }
        input.close();
    }
}