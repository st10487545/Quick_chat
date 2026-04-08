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
        
        System.out.println("Enter Cell Phone Number");
        String cellNumber = scanner.nextLine();
        
        Login login = new Login(firstName,lastName,username,cellNumber);
        
        System.out.println("\n + login.registerUser()");
        
        if (login.checkUserName() && login.checkPasswordComplexity()&& login.checkCellPhoneNumber()){
            System.out.println("\n Quickchat Login");
        }
        
        
        
        
    }
}
