/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.main;

/**
 *
 * @author Student
 */
class Login {
    
    


    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    // Checks if username contains '_' and is <= 5 characters
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    // Validates password complexity requirements
    public boolean checkPasswordComplexity(String password) {
        if (password.length() < 8) return false;
        boolean hasCap = !password.equals(password.toLowerCase());
        boolean hasNum = password.matches(".*\\d.*");
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
        return hasCap && hasNum && hasSpecial;
    }

    // Validates international country code format (e.g., +27...)
    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        String regex = "^\\+\\d{1,3}\\d{1,9}$"; 
        return cellPhoneNumber.matches(regex) && cellPhoneNumber.length() <= 13;
    }

    // Aggregates checks and returns exact registration status messages
    public String registerUser(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        
        return "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.";
    }

    // Verifies stored credentials against user input
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername != null && enteredPassword != null &&
               enteredUsername.equals(this.username) && enteredPassword.equals(this.password);
    }

    // Returns exact authentication message
    public String returnLoginStatus(boolean isLoggedIn) {
        if (isLoggedIn) {
            return "Welcome " + firstName + " " + lastName + " it is great to see you.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}



