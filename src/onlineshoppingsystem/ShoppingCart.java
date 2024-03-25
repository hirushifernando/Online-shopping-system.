/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshoppingsystem;

/**
 *
 * @author ASUS
 */
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author ASUS
 */
// ShoppingCart class represents a shopping cart that holds information about products and their quantities
public class ShoppingCart {
    // Map to keep track of the count of each product category in the cart
    private Map<String, Integer> categoryCount;
    // Boolean flag to indicate whether it's the user's first purchase
    private boolean firstPurchase;
    // Map to store the quantity of each product in the cart
    private final Map<Product, Integer> productQuantityMap;
    // Map to store the total price of each product in the cart
    private final Map<Product, Double> productTotalPriceMap;
    // Total quantity of items in the cart
    private int quantity;
    
    // Constructor to initialize the ShoppingCart object
    public ShoppingCart(){
        // Initialize the category count map
        this.categoryCount = new HashMap<>();
        // Set the flag to true indicating it's the first purchase
        this.firstPurchase = true;
        // Initialize the product quantity map
        this.productQuantityMap = new HashMap<>();
        // Initialize the product total price map
        this.productTotalPriceMap = new HashMap<>();
    }
    
    // Method to add a product to the shopping cart
    public void addProduct(Product product) {
        // Increment the quantity of the specified product in the productQuantityMap
        productQuantityMap.put(product, productQuantityMap.getOrDefault(product, 0) + 1);
        // Get the price of the product
        double totalPrice = product.getPrice();
        // Update the total price of the specified product in the productTotalPriceMap
        productTotalPriceMap.put(product, productTotalPriceMap.getOrDefault(product, 0.0) + totalPrice);

        // Update the count of the product category in the categoryCount map
        String category = product.getCategory();
        categoryCount.put(category, categoryCount.getOrDefault(category, 0) + 1);
    }
    
    // Method to remove a product from the shopping cart
    public void removeProduct(Product product) {
        // Get the current quantity of the specified product in the cart
        int quantity = productQuantityMap.getOrDefault(product, 0);
        // Check if there is more than one of the specified product
        if (quantity > 1) {
            // If so, decrement the quantity in the productQuantityMap
            productQuantityMap.put(product, quantity - 1);
        } else {
            // If there is only one, remove the product from the cart
            productQuantityMap.remove(product);
        }

        // Update the count of the product category in the categoryCount map
        String category = product.getCategory();
        categoryCount.put(category, categoryCount.get(category) - 1);
    }
    
    // Method to get the quantity of a specific product in the shopping cart
    public int getProductQuantity(Product product) {
         // Return the quantity of the specified product or 0 if it's not in the cart
        return productQuantityMap.getOrDefault(product, 0);
    }
    
    // Method to calculate the total cost of all products in the shopping cart
    public double calculateTotalCost() {
        double totalCost = 0;
        // Iterate over each entry in the productQuantityMap
        for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();
            // Calculate the total cost by multiplying the product's price by its quantity
            totalCost += product.getPrice() * quantity;
        }
        return totalCost;
    }

    // Method to get a list of products in the shopping cart
    public List<Product> getProducts() {
         // Return a new ArrayList containing all unique products in the cart
         return new ArrayList<>(productQuantityMap.keySet());
    
    }
    
    // Method to apply discounts to the total cost based on the quantity of products in each category
    public double applyDiscount(double totalCost) {
        // Iterate over the counts of products in each category
        for (int count : categoryCount.values()) {
           // If there are 3 or more products in a category, apply a 20% discount
            if (count >= 3) {
                totalCost *= 0.8; // 20% discount
                break;
            }
        }
        
        // If it's the first purchase, apply a 10% discount
        if (firstPurchase) {
            totalCost *= 0.9; // 10% discount
            firstPurchase = false;
        }
        
        // Return the total cost after applying discounts
        return totalCost;
    }
    
    // Method to clear all items from the shopping cart
    public void clearCart() {
        // Clear all items from the cart
        productQuantityMap.clear();
    }
    // Method to check if the shopping cart contains a specific product
    public boolean containsProduct(Product product) {
        // Check if the productQuantityMap contains the given product
        return productQuantityMap.containsKey(product);
    }
    
    // Method to update the quantity of a specific product in the shopping cart
    public void updateProductQuantity(Product product, int quantity) {
        // Update the quantity property with the provided quantity
        this.quantity = quantity;
        // Check if the shopping cart contains the specified product
        if (productQuantityMap.containsKey(product)) {
            // Update the quantity in the productQuantityMap
            productQuantityMap.put(product, quantity);

            // Update the corresponding total price in productTotalPriceMap
            double unitPrice = product.getPrice();
            double updatedTotalPrice = unitPrice * quantity;
            productTotalPriceMap.put(product, updatedTotalPrice);
        }
    }
}
