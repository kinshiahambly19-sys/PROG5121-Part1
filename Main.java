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

        System.out.println("=== Part 1: Registration & Login ===");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();
  System.out.print("Enter First Name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ");
        String lastName = scanner.nextLine();

        if (!username.contains("_") || username.length() > 5) {
            System.out.println("Username is not correctly formatted.");
            return;
        }

        if (password.length() < 8 || !password.matches(".*[A-Z].*") || !password.matches(".*[0-9].*") || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            System.out.println("Password is not correctly formatted.");
            return;
        }

        System.out.println("User registered successfully!");

        System.out.println("\n--- Login ---");
        System.out.print("Enter Username: ");
        String loginUser = scanner.nextLine();

        System.out.print("Enter Password: ");
        String loginPass = scanner.nextLine();

        if (loginUser.equals(username) && loginPass.equals(password)) {
            System.out.println("Welcome " + firstName + " " + lastName + " it is great to see you again.");

            boolean running = true;
            int messageCounter = 0;

            while (running) {
                System.out.println("\n=== QuickChat Menu ===");
                System.out.println("1) Send / Create Message");
                System.out.println("2) Show Total Sent Messages");
                System.out.println("3) Stored Messages & Task Reports");
                System.out.println("4) Quit");
                System.out.print("Choose an option: ");

                String inputChoice = scanner.nextLine();

                switch (inputChoice) {
                    case "1":
                        messageCounter++;
                        System.out.print("Enter Recipient Cell Number (+27...): ");
                        String cell = scanner.nextLine();

                        System.out.print("Enter Message Text (Max 250 chars): ");
                        String text = scanner.nextLine();

                        System.out.println("Select Flag: 1) Sent  2) Disregard  3) Stored");
                        String flagChoice = scanner.nextLine();
                        String flag = "Sent";
                        if ("2".equals(flagChoice)) flag = "Disregard";
                        if ("3".equals(flagChoice)) flag = "Stored";

                        Message msg = new Message(messageCounter, cell, text, flag);
                        msg.populateArrays();

                        System.out.println(msg.checkRecipientCell());
                        System.out.println(msg.checkMessageLength());
                        System.out.println("Message Hash: " + msg.getMessageHash());
                        break;

                    case "2":
                        System.out.println("Total Messages Sent: " + Message.sentMessages.size());
                        break;

                    case "3":
                        System.out.println("\n--- Stored Messages Options ---");
                        System.out.println("a. Display sender and recipient of stored messages");
                        System.out.println("b. Display longest stored message");
                        System.out.println("c. Search by Message ID");
                        System.out.println("d. Search by Recipient");
                        System.out.println("e. Delete message by Hash");
                        System.out.println("f. Display Task Report");
                        System.out.print("Choose sub-option: ");
                        String subOption = scanner.nextLine().toLowerCase();

                        switch (subOption) {
                            case "a":
                                System.out.println("Stored Messages Count: " + Message.storedMessages.size());
                                break;
                            case "b":
                                System.out.println("Longest Stored Message: " + Message.getLongestMessage());
                                break;
                            case "c":
                                System.out.print("Enter Message ID: ");
                                String id = scanner.nextLine();
                                System.out.println("Result: " + Message.searchByMessageID(id));
                                break;
                            case "d":
                                System.out.print("Enter Recipient Number: ");
                                String recipient = scanner.nextLine();
                                System.out.println("Messages: " + Message.searchByRecipient(recipient));
                                break;
                            case "e":
                                System.out.print("Enter Message Hash to Delete: ");
                                String hash = scanner.nextLine();
                                System.out.println(Message.deleteByHash(hash));
                                break;
                            case "f":
                                System.out.println(Message.displayReport());
                                break;
                            default:
                                System.out.println("Invalid option.");
                        }
                        break;

                    case "4":
                        System.out.println("Exiting QuickChat. Goodbye!");
                        running = false;
                        break;

                    default:
                        System.out.println("Invalid menu option.");
                }
            }

        } else {
            System.out.println("Username or password incorrect.");
        }

        scanner.close();
    }
}

    
