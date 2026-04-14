/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quick_chat;

import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class Quick_chat {

    public static void main(String[] args) {
        Scanner scanner = new Scanner (System.in);
        
        System.out.println("QuickChat Registration");
        
        System.out.println("Enter First Name");
        String firstName = scanner.nextLine();
        
        System.out.println("Enter Last Name ");
        String lastName = scanner.nextLine();
        
        System.out.println("Enter Username");
        String username =  scanner.nextLine();
        
        System.out.println("Enter Password");
        String password = scanner.nextLine();
        
        System.out.println("Enter Cell Phone Number");
        String cellNumber = scanner.nextLine();
        
        Login login = new Login(firstName, lastName, username, password, cellNumber);
        
        System.out.println("\n" + login.registerUser());
        
        if (login.checkUserName() && login.checkPasswordComplexity()&& login.checkCellPhoneNumber()){
            System.out.println("\n Quickchat Login");
            
            System.out.print("Enter Username: ");
            String enteredUsername = scanner.nextLine();

            System.out.print("Enter Password: ");
            String enteredPassword = scanner.nextLine();

            System.out.println("\n" + login.returnLoginStatus(enteredUsername, enteredPassword));
        }

         // ===== MAIN MENU =====
            // Only show the menu if the login credentials were correct
            if (login.loginUser(enteredUsername, enteredPassword)) {

                System.out.println("\nHow many messages do you want to send?");
                int numMessages = Integer.parseInt(scanner.nextLine());

                int menuChoice = 0;

                // Keep showing the menu until the user picks quit
                while (menuChoice != 3) {
                    System.out.println("\nWelcome to QuickChat.");
                    System.out.println("1) Send Messages");
                    System.out.println("2) Show recently sent messages");
                    System.out.println("3) Quit");
                    System.out.print("Choose an option: ");
                    menuChoice = Integer.parseInt(scanner.nextLine());

                    if (menuChoice == 1) {
                        // Loop runs once for each message the user wants to send
                        for (int i = 1; i <= numMessages; i++) {
                            System.out.println("\n--- Message " + i + " of " + numMessages + " ---");

                            System.out.print("Enter recipient cell number: ");
                            String recipient = scanner.nextLine();

                            System.out.print("Enter your message: ");
                            String messageText = scanner.nextLine();

                            // Create the message object — ID and hash are auto-generated
                            Message msg = new Message(i, recipient, messageText);

                            // Show validation results for recipient and message length
                            System.out.println(msg.checkRecipientCell());
                            System.out.println(msg.checkMessageLength());
                            
                            // Only continue if the message is within the character limit
                            if (messageText.length() <= 250) {

                                // Display full message details before sending
                                System.out.println("\nMessage ID: " + msg.getMessageID());
                                System.out.println("Message Hash: " + msg.getMessageHash());
                                System.out.println("Recipient: " + msg.getRecipient());
                                System.out.println("Message: " + msg.getMessage());

                                // Let the user decide what to do with the message
                                System.out.println("\n1) Send Message");
                                System.out.println("2) Disregard Message");
                                System.out.println("3) Store Message to send later");
                                System.out.print("Choose an option: ");
                                int sendChoice = Integer.parseInt(scanner.nextLine());

                                System.out.println(msg.SentMessage(sendChoice));
                            }
                        }

                    }
        
    }
}
        
        
        
        
        
    

