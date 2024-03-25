/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshoppingsystem;

/**
 *
 * @author ASUS
 */
// Define an abstract class named Product
public abstract class Product {
    // Declare private instance variables for product details
    private String productID;
    private String productName;
    private int items;
    private double price;
    private String category;
    
    
    // Constructor for the Product class, initializing the product details
    public Product(String productID,String productName,int items,double price,String category){
        this.productID = productID;
        this.productName = productName;
        this.items = items;
        this.price = price;
        this.category = category;    
    }
    
    // Setter method for updating the product ID
    public void setProductID(String productID){
        this.productID = productID;
    }
    // Getter method for retrieving the product ID
    public String getProductID(){
        return productID;
    }
    // Setter method for updating the product name
    public void setProductName(String productName){
        this.productName = productName;
    }
    // Getter method for retrieving the product name
    public String getProductName(){
        return productName;
    }
    // Setter method for updating the number of items
    public void setItems(int items){
        this.items = items;
    }
    // Getter method for retrieving the number of items
    public int getItems(){
        return items;
    }
    // Setter method for updating the price
    public void setPrice(double price){
        this.price = price;
    }
    // Getter method for retrieving the price
    public double getPrice(){
        return price;
    }
    // Getter method for retrieving the category
    public String getCategory() {
        return category;
    }
    
    // Override the toString method to provide a custom string representation of the Product object
    @Override
    public String toString() {
        return "Product ID: " + productID + "Product Name: " + productName + "Category: " + category + "Number of items: " + items + "Price: " + price;
    }

}