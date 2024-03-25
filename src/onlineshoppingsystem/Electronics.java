/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshoppingsystem;

/**
 *
 * @author ASUS
 */
// Define a class named Electronics that extends the Product class
public class Electronics extends Product {
    // Declare private instance variables for brand and warranty period
    private String brand;
    private int warrantyPeriod;
    
    // Constructor for the Electronics class, taking parameters for product details, brand, and warranty period
    public Electronics(String productID, String productName, int items, double price, String category, String brand, int warrantyPeriod) {
        // Call the constructor of the superclass (Product) using the super keyword
        super(productID, productName, items, price, category);
        // Initialize the brand and warranty period specific to Electronics
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }
    // Setter method for updating the brand of the electronics
    public void setBrand(String brand){
        this.brand = brand;
    }
    // Getter method for retrieving the brand of the electronics
    public String getBrand(){
        return brand;
    }
    // Setter method for updating the warranty period of the electronics
    public void setWarrantyPeriod(int warrantyPeriod){
        this.warrantyPeriod = warrantyPeriod;
    }
    // Getter method for retrieving the warranty period of the electronics
    public int getWarrantyPeriod(){
        return warrantyPeriod;
    }
    // Override the toString method to provide a custom string representation of the Electronics object
    @Override
    public String toString() {
        return String.format("Electronics - Product ID: %s, Product Name: %s,  Items: %d, Price: %.2f, Brand: %s, Warranty Period: %d months",
                getProductID(), getProductName(), getItems(), getPrice(), getBrand(), getWarrantyPeriod());
    }    
}
