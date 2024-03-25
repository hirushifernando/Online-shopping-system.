/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshoppingsystem;

/**
 *
 * @author ASUS
 */
// Define a class named User
public class User {
    // Declare private instance variables for username and password
    private String username;
    private String password;
    
    // Constructor for the User class, initializing the username and password
    public User(String username,String password){
        this.username = username;
        this.password = password;
    }
    // Setter method for updating the username
    public void setUsername(String username) {
        this.username = username;
    }
    // Getter method for retrieving the username
    public String getUsername() {
        return username;
    }
    // Setter method for updating the password
    public void setPassword(String password) {
        this.password = password;
    }
    // Getter method for retrieving the password
    public String getPassword() {
        return password;
    }
    // Override the toString method to provide a custom string representation of the User object
    @Override
    public String toString() {
        return "Customer:" + "user_name='" + username + '\'' +", Password=" + password + ".";
    }
    
}
