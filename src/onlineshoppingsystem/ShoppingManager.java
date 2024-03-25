/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package onlineshoppingsystem;

/**
 *
 * @author ASUS
 */
// Define an interface named ShoppingManager
public interface ShoppingManager {
    // Method to add a product to the shopping manager
    void addProduct(Product product);
    // Method to delete a product based on its productID from the shopping manager
    void deleteProduct(String productID);
    // Method to print the list of products managed by the shopping manager
    void printProductList();
    // Method to save the product list to a file with the specified fileName
    void saveToFile(String fileName);
    // Method to read product information from a file with the specified fileName
    void readFromFile(String fileName);
    // Method to execute the menu functionality, returning a boolean indicating whether the program should exit
    boolean runMenu();
    
}