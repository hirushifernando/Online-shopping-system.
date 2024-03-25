/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onlineshoppingsystem;

/**
 *
 * @author ASUS
 */
// Define a class named OnlineShoppingSystem
public class OnlineShoppingSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Create an instance of WestminsterShoppingManager with a capacity of 50 products
        ShoppingManager sys = new WestminsterShoppingManager(50);  
        // Initialize a boolean variable 'exit' to control the loop
        boolean exit = false; 
        
        // Start a loop that continues until 'exit' becomes true
        while (!exit){ 
            // Call the runMenu method of the ShoppingManager instance, and update the 'exit' variable based on its return value
            exit = sys.runMenu(); 
        }
       
    }
    
}
