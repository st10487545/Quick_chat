/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quick_chat;

import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Pattern;

/**
 *
 * @author lab_services_student
 */
public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    // These lists keep track of all messages during the session
    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static ArrayList<String> messageHashes = new ArrayList<>();
    private static ArrayList<String> messageIDs = new ArrayList<>();
    private static int totalMessagesSent = 0;

    // Constructor sets up the message and auto-generates the ID and hash
    public Message(int messageNumber, String recipient, String message) {
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
    // Builds a random 10-digit number to use as the message ID
    private String generateMessageID() {
        Random random = new Random();
        long id = (long) (random.nextDouble() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(id);
    }

    // Makes sure the message ID does not go over 10 characters
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    // Checks the recipient number starts with a + and international code
    // Regex reference: https://stackoverflow.com/questions/6478875/regular-expression-matching-e-164-formatted-phone-numbers
    public String checkRecipientCell() {
        String regex = "^\\+[0-9]{1,3}[0-9]{6,9}$";
        if (Pattern.matches(regex, recipient) && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
}
