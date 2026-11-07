/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Student
 */


public class MessageTest {

   

    @BeforeEach
    public void setUp() {
        Message.clearAllData();

        // Populate Test Data 1-5 from Rubric
        Message msg1 = new Message(1, "+27834557896", "Did you get the cake?", "Sent", "00:1:DIDCAKE");
        msg1.populateArrays();

        Message msg2 = new Message(2, "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored", "00:2:WHERETIME");
        msg2.populateArrays();

        Message msg3 = new Message(3, "+27834484567", "Yohoooo, I am at your gate.", "Disregard", "00:3:YOHOOOOgate");
        msg3.populateArrays();

        Message msg4 = new Message(4, "0838884567", "It is dinner time !", "Sent", "00:4:ITTIME");
        msg4.populateArrays();

        Message msg5 = new Message(5, "+27838884567", "Ok, I am leaving without you.", "Stored", "00:5:OKYOU");
        msg5.populateArrays();
    }

    @Test
    public void testSentMessagesArrayPopulated() {
        assertTrue(Message.sentMessages.contains("Did you get the cake?"));
        assertTrue(Message.sentMessages.contains("It is dinner time !"));
    }

    @Test
    public void testDisplayLongestMessage() {
        String expected = "Where are you? You are late! I have asked you to be on time.";
        assertEquals(expected, Message.getLongestMessage());
    }

    @Test
    public void testSearchByMessageID() {
        String expected = "It is dinner time !";
        assertEquals(expected, Message.searchByMessageID("4"));
    }

    @Test
    public void testSearchByRecipient() {
        String result = Message.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteByHash() {
        String expected = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        assertEquals(expected, Message.deleteByHash("00:2:WHERETIME"));
    }

    @Test
    public void testDisplayReport() {
        String report = Message.displayReport();
        assertTrue(report.contains("Message Hash: 00:1:DIDCAKE"));
        assertTrue(report.contains("Recipient: +27834557896"));
        assertTrue(report.contains("Message: Did you get the cake?"));
    }
}


