/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package onlineshoppingsystem;

/**
 *
 * @author ASUS
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Define a class representing the shopping cart dialog that extends JFrame
public class ShoppingCartDialog extends JFrame {
    
    // Create a DefaultTableModel for managing the data in the shopping cart
    private final DefaultTableModel shoppingcartTableModel = new DefaultTableModel();
    // Use a Map to store the quantity of each product in the shopping cart
    private final Map<String, Integer> productQuantityMap = new HashMap<>();
    // Flag to track if it's the first purchase (for initialization purposes)
    private boolean isFirstPurchase = true;
    // JTextArea to display details about the items in the shopping cart
    private JTextArea cartDetailsArea;
    
    // Constructor for the ShoppingCartDialog class
    public ShoppingCartDialog() {
        
        // Create a JPanel with a null layout
        JPanel panel = new JPanel(null);

        // Create a table model with columns: Product, Quantity, Price
        shoppingcartTableModel.addColumn("Product");
        shoppingcartTableModel.addColumn("Quantity");
        shoppingcartTableModel.addColumn("Price");
        
        // Create a JTable using the shoppingcartTableModel
        JTable shoppingCartTable = new JTable(shoppingcartTableModel);
        // Create a scroll pane for the shopping cart table
        JScrollPane cartTableScrollPane = new JScrollPane(shoppingCartTable);
        cartTableScrollPane.setPreferredSize(new Dimension(400, 150));
        
        // Create a text area to display details about the items in the shopping cart
        JTextArea detailsTextArea = new JTextArea();
        JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
        detailsScrollPane.setPreferredSize(new Dimension(400, 100));
        // Create a text area to display cart details
        cartDetailsArea = new JTextArea();
        cartDetailsArea.setEditable(false);
        cartDetailsArea.setLineWrap(true);
        cartDetailsArea.setWrapStyleWord(true);
        cartDetailsArea.setPreferredSize(new Dimension(400, 50));
        
        // Add components to the panel
        panel.add(cartTableScrollPane);
        panel.add(detailsScrollPane);
        panel.add(cartDetailsArea);
        
        // Set the layout of the frame
        setLayout(new BorderLayout());

        // Add the table and details scroll panes to the frame
        add(cartTableScrollPane, BorderLayout.CENTER);
        add(detailsScrollPane, BorderLayout.SOUTH);
        add(cartDetailsArea, BorderLayout.PAGE_END);
        

        // Set the default close operation to dispose when closed
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // Set the size, title, and initial visibility of the frame
        setSize(600, 400);
        setTitle("Shopping Cart");
        setVisible(false);
    }
    
    // Method to calculate the total cost of items in the shopping cart
    private double calculateTotalCost() {
        // Initialize the total cost to zero
        double totalCost = 0;
        // Iterate over the rows in the shopping cart table model
        for (int rowIndex = 0; rowIndex < shoppingcartTableModel.getRowCount(); rowIndex++) {
            // Get the value in the "Price" column of the current row
            Object value = shoppingcartTableModel.getValueAt(rowIndex, 2);
            // Check if the value is an instance of Double
            if (value instanceof Double) {
                // Add the double value to the total cost
                totalCost += (double) value;
            }
        }
        // Return the calculated total cost
        return totalCost;
    }
    
    // Method to update the details of the shopping cart based on the added or updated product
    public void updateCartDetails(Product product, double totalPrice) {
        // Convert the product ID to a string for mapping purposes
        String productID = String.valueOf(product.getProductID());

        // Check if the product is already in the shopping cart
        if (productQuantityMap.containsKey(productID)) {
            
            // If the product is in the cart, update its quantity
            int currentQuantity = productQuantityMap.get(productID);
            int updatedQuantity = currentQuantity + 1;
            productQuantityMap.put(productID, updatedQuantity);

            // Find the row index of the product in the shopping cart table
            int rowIndex = findProductRowIndex(productID);
            
            // Check if the product is found in the table
            if (rowIndex != -1) {
                // Update the quantity in the shopping cart table
                shoppingcartTableModel.setValueAt(updatedQuantity, rowIndex, 1);

                // Get the unit price from the table (assuming it's in column index 2)
                double unitPrice = (double) shoppingcartTableModel.getValueAt(rowIndex, 2); 
                // Calculate the updated total price based on the new quantity
                double updatedTotalPrice = updatedQuantity * unitPrice;
                // Update the total price in the shopping cart table
                shoppingcartTableModel.setValueAt(updatedTotalPrice, rowIndex, 2); 
            }
        } else {
            // If the product is not in the cart, add a new row to the shopping cart table
            productQuantityMap.put(productID, 1);
            shoppingcartTableModel.addRow(new Object[]{getProductDetails(product), 1, totalPrice});
        }
        // Update the details area of the shopping cart dialog
        updateCartDetailsArea();
    }
    // Method to find the row index of a product in the shopping cart table
    private int findProductRowIndex(String productID) {
        // Iterate over the rows in the shopping cart table model
        for (int rowIndex = 0; rowIndex < shoppingcartTableModel.getRowCount(); rowIndex++) {
            // Check if the product ID in the current row matches the specified productID
            if (productID.equals(shoppingcartTableModel.getValueAt(rowIndex, 0))) {
                // Return the index if a match is found
                return rowIndex;
            }
        }
        // Return -1 if the product is not found in the table
        return -1; 
    }
    // Method to get a formatted string representing the details of a product
    private String getProductDetails(Product product) {
        // Retrieve product information (ID, name, additional info)
        String productID = String.valueOf(product.getProductID());
        String productName = product.getProductName();
        String productInfo = getProductInfo(product);
        
        // Format the details into a single string
        return String.format("%s - %s (%s)", productID, productName, productInfo);
    }
    
    // Method to get additional information about a product
    private String getProductInfo(Product product) {
        // Check if the product is an instance of Clothing or Electronics
        if (!(product instanceof Electronics electronicsProduct)) if (product instanceof Clothing clothingProduct) {
            return "Size: " + clothingProduct.getSize() +
                    ", Color: " + clothingProduct.getColor();
        } else {
            // If it's neither Clothing nor Electronics, return a default message
            return "No additional info";
        } else {
            // If it's neither Clothing nor Electronics, return a default message
            return "Warranty Period: " + electronicsProduct.getWarrantyPeriod() +
                    ", Brand: " + electronicsProduct.getBrand();
        }
    }
    // Method to update the details area of the shopping cart dialog
    private void updateCartDetailsArea() {
       // Calculate the total cost of items in the shopping cart
        double total = calculateTotalCost();
        // Set the text in the cart details area with the total cost
        cartDetailsArea.setText("Total = Rs" + total);

        // Apply discounts and get the discounted total cost
        double discountedTotal = applyDiscounts(total);

       // Append the discounted total to the cart details area
        cartDetailsArea.append("\nFinal Cost after Discounts = Rs" + discountedTotal);
    }
    // Method to apply discounts to the total cost
    private double applyDiscounts(double total) {
        // Check if it's the first purchase
        if (isFirstPurchase) {
            // Apply a 10% discount for the first purchase
            total *= 0.9; 
            // Set isFirstPurchase to false to indicate that discounts have been applied
            isFirstPurchase = false;
        }

        // Check if the total quantity of items in the cart is 3 or more
        if (totalQuantityInCart() >= 3) {
            // Apply a 20% discount if the total quantity is 3 or more
            total *= 0.8; 
        }
        // Apply a 20% discount if the total quantity is 3 or more
        return total;
    }
    // Method to calculate the total quantity of items in the shopping cart
    private int totalQuantityInCart() {
        // Initialize the total quantity
        int totalQuantity = 0;
        // Iterate over the rows in the shopping cart table model
        for (int rowIndex = 0; rowIndex < shoppingcartTableModel.getRowCount(); rowIndex++) {
            // Get the quantity value from the second column (index 1) in each row
            Object value = shoppingcartTableModel.getValueAt(rowIndex, 1);
            // Check if the value is an integer and add it to the total quantity
            if (value instanceof Integer) {
                totalQuantity += (int) value;
            }
        }
        // Return the total quantity of items in the cart
        return totalQuantity;
    }
    
    // Method to update the quantity information for a specific product in the shopping cart table
    public void updateProductInfo(Product product, int quantity) {
        
         // Find the row index of the product in the shopping cart table
        int rowIndex = findProductRowIndex(String.valueOf(product.getProductID()));
        
        // Check if the product was found in the table
        if (rowIndex != -1) {
            // Update the quantity in the second column (index 1) of the corresponding row
            shoppingcartTableModel.setValueAt(quantity, rowIndex, 1);

            // Update the details area of the shopping cart dialog
            updateCartDetailsArea();
        }
    }
    
    // Method to get the quantity of a specific product in the shopping cart
    public int getProductQuantity(Product product) {
        // Get the product ID as a string
        String productID = String.valueOf(product.getProductID());
        // Return the quantity of the specified product from the productQuantityMap
        return productQuantityMap.getOrDefault(productID, 0);
    }
}
