/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author Student
 */


import java.util.Random;

public class Message {

    private String messageID;
    private String recipientCell;
    private String messageContent;
    private String messageHash;
    private String messageStatus;
    private int messageNumber;

    // Constructor
    public Message(int messageNumber, String recipientCell, String messageContent) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageContent = messageContent;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }

    // Constructor for testing with custom ID
    public Message(int messageNumber, String recipientCell, String messageContent, String customID) {
        this.messageNumber = messageNumber;
        this.recipientCell = recipientCell;
        this.messageContent = messageContent;
        this.messageID = customID;
        this.messageHash = createMessageHash();
    }

    // Generates random 10-digit Message ID
    private String generateMessageID() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // 1. Validates Message ID length (<= 10)
    public boolean checkMessageID() {
        return this.messageID != null && this.messageID.length() <= 10;
    }

    // 2. Validates Recipient Cell (starts with "+" and <= 10 chars)
    public String checkRecipientCell() {
        if (recipientCell != null && recipientCell.startsWith("+") && recipientCell.length() <= 10) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    // 3. Validates Message Length (<= 250 chars)
    public String checkMessageLength() {
        if (messageContent != null && messageContent.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = messageContent.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // 4. Creates Message Hash: [First 2 ID digits]:[Msg Num]:[First & Last Word UpperCase]
    public String createMessageHash() {
        if (messageContent == null || messageContent.trim().isEmpty() || messageID == null || messageID.length() < 2) {
            return "";
        }
        String idPrefix = messageID.substring(0, 2);
        String[] words = messageContent.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "").toUpperCase();

        return idPrefix + ":" + messageNumber + ":" + firstWord + lastWord;
    }

    // 5. Handles Action Selection (Send / Disregard / Store)
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                this.messageStatus = "Sent";
                return "Message successfully sent.";
            case 2:
                this.messageStatus = "Disregarded";
                return "Press 0 to delete the message.";
            case 3:
                this.messageStatus = "Stored";
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    // 6. Prints full message details
    public String printMessages() {
        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipientCell + "\n"
                + "Message: " + messageContent;
    }

    // Getters
    public String getMessageID() {
        return messageID;
    }

    public String getMessageHash() {
        return messageHash;
    }

    public String getMessageStatus() {
        return messageStatus;
    }

    public String getRecipientCell() {
        return recipientCell;
    }

    public String getMessageContent() {
        return messageContent;
    }

    
}
