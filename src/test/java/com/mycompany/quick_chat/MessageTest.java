/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.quick_chat;

import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class MessageTest {
    
    // Message within 250 characters should return ready to send
    @Test
    public void testMessageLengthValid() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    // Message over 250 characters should return the exceeded message
    @Test
    public void testMessageLengthExceeded() {
        String longMessage = "A".repeat(260);
        Message msg = new Message(1, "+27718693002", longMessage);
        assertTrue(msg.checkMessageLength().contains("exceeds 250 characters"));
    }

    // A correctly formatted number should return success
    @Test
    public void testRecipientCellCorrect() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    // A number without international code should return failure
    @Test
    public void testRecipientCellIncorrect() {
        Message msg = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
    }

    // Hash for test case 1 should end with HITONIGHT
    @Test
    public void testMessageHashCorrect() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        String hash = msg.getMessageHash();
        assertTrue(hash.endsWith("HITONIGHT"));
    }

    // Choosing send should return the sent confirmation
    @Test
    public void testSentMessageSend() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }

    // Choosing disregard should return the delete prompt
    @Test
    public void testSentMessageDisregard() {
        Message msg = new Message(1, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
    }

    // Choosing store should return the stored confirmation
    @Test
    public void testSentMessageStore() {
        Message msg = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }
}