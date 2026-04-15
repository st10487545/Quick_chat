/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quick_chat;

import java.io.FileWriter;
import java.io.IOException;
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
    // Builds the message hash using the first 2 digits of the ID,
    // the message number, and the first and last words of the message
    public String createMessageHash() {
        String[] words = message.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        String firstTwoID = messageID.substring(0, 2);
        return (firstTwoID + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // Checks the message is within the 250 character limit
    public String checkMessageLength() {
        if (message.length() > 250) {
            int excess = message.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
        return "Message ready to send.";
    }

    // Handles what happens based on what the user picks — send, disregard or store
    public String SentMessage(int choice) {
        switch (choice) {
            case 1:
                // Add message details to the session lists and bump the counter
                sentMessages.add(message);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2:
                // User chose to disregard — message is not saved
                return "Press 0 to delete the message.";
            case 3:
                // Save the message to a JSON file for later
                storeMessage();
                return "Message successfully stored.";
            default:
                return "Invalid option, please try again.";
        }
    }
// Goes through the sent messages list and returns them all as a single string
    public String printMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (String msg : sentMessages) {
            sb.append(msg).append("\n");
        }
        return sb.toString();
    }

    // Returns how many messages have been sent so far
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    // Writes the message details to a JSON file
    // FileWriter reference: https://www.w3schools.com/java/java_files_create.asp
    public void storeMessage() {
        String jsonMessage = "{\n" +
            "  \"messageID\": \"" + messageID + "\",\n" +
            "  \"messageHash\": \"" + messageHash + "\",\n" +
            "  \"recipient\": \"" + recipient + "\",\n" +
            "  \"message\": \"" + message + "\"\n" +
            "},\n";

        try (FileWriter file = new FileWriter("storedMessages.json", true)) {
            file.write(jsonMessage);
            file.flush();
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // Getters for accessing private fields outside the class
    public String getMessageID() { return messageID; }
    public String getMessageHash() { return messageHash; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public static ArrayList<String> getSentMessages() { return sentMessages; }
}
