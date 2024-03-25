/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshoppingsystem;

/**
 *
 * @author ASUS
 */
// Define a class named Clothing that extends the Product class
public class Clothing extends Product {
    // Declare private instance variables for size and color
    private String size;
    private String color;
    
    // Constructor for the Clothing class, taking parameters for product details, size, and color
    public Clothing(String productID, String productName, int items, double price, String category, String size, String color) {
        // Call the constructor of the superclass (Product) using the super keyword
        super(productID, productName, items, price, category);
        // Initialize the size and color specific to Clothing
        this.size = size;
        this.color = color;
    }
    // Setter method for updating the size of the clothing
    public void setSize(String size) {
        this.size = size;
    }
    // Getter method for retrieving the size of the clothing
    public String getSize() {
        return size;
    }
    // Setter method for updating the color of the clothing
    public void setColor(String color) {
        this.color = color;
    }
    // Getter method for retrieving the color of the clothing
    public String getColor() {
        return color;
    }
    // Override the toString method to provide a custom string representation of the Clothing object
    @Override
    public String toString() {
        return String.format("Clothes - Product ID: %s, Product Name: %s, Items: %d, Price: %.2f, Color: %s, Size: %s",
                getProductID(), getProductName(), getItems(), getPrice(), getColor(), getSize());
    }   
}