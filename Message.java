/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author Student
 */


import java.util.ArrayList;
import java.util.Random;

public class Message {

   





  




    private int messageNumber;
    private String recipientCell;
    private String messageBody;
    private String messageHash;
    private String flag; 

    // Storage Arrays
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> recipients = new ArrayList<>();
    public static ArrayList<String> flags = new ArrayList<>();

    // Constructor 1: 3 parameters (Part 2 compatibility)
    public Message(int messageNumber, String recipientCell, String messageBody) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageBody = messageBody;
        this.flag = "Sent";
        this.messageHash = generateHash();
    }

    // Constructor 2: 4 parameters (Part 3 Menu)
    public Message(int messageNumber, String recipientCell, String messageBody, String flag) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageBody = messageBody;
        this.flag = flag;
        this.messageHash = generateHash();
    }

    // Constructor 3: 5 parameters (Testing with custom hash)
    public Message(int messageNumber, String recipientCell, String messageBody, String flag, String customHash) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageBody = messageBody;
        this.flag = flag;
        this.messageHash = customHash;
    }

    public String checkRecipientCell() {
        if (recipientCell != null && recipientCell.startsWith("+") && recipientCell.length() == 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    public String checkMessageLength() {
        if (messageBody != null && messageBody.length() <= 250) {
            return "Message ready to send.";
        } else {
            int overflow = (messageBody != null) ? (messageBody.length() - 250) : 0;
            return "Message exceeds 250 characters by " + overflow + "; please reduce the size.";
        }
    }

    private String generateHash() {
        if (messageBody == null || messageBody.trim().isEmpty()) {
            return "00:" + messageNumber + ":INVALID";
        }
        String trimmed = messageBody.trim();
        String[] words = trimmed.split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        String combined = (words.length == 1) ? firstWord : firstWord + lastWord;
        combined = combined.replaceAll("[^A-Z0-9]", "");
        return String.format("%02d:%d:%s", 0, messageNumber, combined);
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                return "Message successfully sent.";
            case 2:
                return "Press 0 to delete the message.";
            case 3:
                return "Message successfully stored.";
            default:
                return "Invalid option selected.";
        }
    }

    public void populateArrays() {
        messageIDs.add(String.valueOf(messageNumber));
        recipients.add(recipientCell);
        messageHashes.add(messageHash);
        flags.add(flag);

        if ("Sent".equalsIgnoreCase(flag)) {
            sentMessages.add(messageBody);
        } else if ("Disregard".equalsIgnoreCase(flag) || "Disregarded".equalsIgnoreCase(flag)) {
            disregardedMessages.add(messageBody);
        } else if ("Stored".equalsIgnoreCase(flag)) {
            storedMessages.add(messageBody);
        }
    }

    public static void clearAllData() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        recipients.clear();
        flags.clear();
    }

    public static String getLongestMessage() {
        String longest = "";
        for (String msg : storedMessages) {
            if (msg.length() > longest.length()) {
                longest = msg;
            }
        }
        return longest;
    }

    public static String searchByMessageID(String id) {
        for (int i = 0; i < messageIDs.size(); i++) {
            if (messageIDs.get(i).equals(id)) {
                return storedMessages.get(i);
            }
        }
        return "Message not found.";
    }

    public static String searchByRecipient(String recipientCell) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < recipients.size(); i++) {
            if (recipients.get(i).equals(recipientCell)) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append("\"").append(getAssociatedMessageBody(i)).append("\"");
            }
        }
        return result.toString();
    }

    private static String getAssociatedMessageBody(int index) {
        String currentFlag = flags.get(index);
        if ("Sent".equalsIgnoreCase(currentFlag)) {
            return sentMessages.size() > index ? sentMessages.get(index) : "";
        } else if ("Stored".equalsIgnoreCase(currentFlag)) {
            return storedMessages.size() > index ? storedMessages.get(index) : "";
        } else {
            return disregardedMessages.size() > index ? disregardedMessages.get(index) : "";
        }
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < messageHashes.size(); i++) {
            if (messageHashes.get(i).equalsIgnoreCase(hash)) {
                String deletedMsg = storedMessages.get(i);
                storedMessages.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                recipients.remove(i);
                flags.remove(i);
                return "Message: \"" + deletedMsg + "\" successfully deleted.";
            }
        }
        return "Message hash not found.";
    }

    public static String displayReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== SENT MESSAGES REPORT ===\n");
        for (int i = 0; i < messageHashes.size(); i++) {
            if ("Sent".equalsIgnoreCase(flags.get(i))) {
                report.append("Message Hash: ").append(messageHashes.get(i)).append("\n");
                report.append("Recipient: ").append(recipients.get(i)).append("\n");
                report.append("Message: ").append(sentMessages.get(i)).append("\n");
                report.append("----------------------------\n");
            }
        }
        return report.toString();
    }

    public int getMessageNumber() { return messageNumber; }
    public String getRecipientCell() { return recipientCell; }
    public String getMessageBody() { return messageBody; }
    public String getFlag() { return flag; }
    
    public void printMessages() {
        System.out.println("Message ID: " + messageNumber);
        System.out.println("Recipient: " + recipientCell);
        System.out.println("Message: " + messageBody);
        System.out.println("Hash: " + messageHash);
        System.out.println("Flag: " + flag);
    }
}

    

    

