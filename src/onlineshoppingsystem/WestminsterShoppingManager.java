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
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.SwingUtilities;


/**
 *
 * @author ASUS
 */
public class WestminsterShoppingManager implements ShoppingManager {
    // Declare private instance variables
    private List<Product> productList;
    private int numProduct;
    private ShoppingGUI shoppingGUI;
    public JComboBox<String> menuItemsDropdown;
    public JTable table;
    // Constructor for WestminsterShoppingManager, initializing the number of products and an empty product list
    public WestminsterShoppingManager(int numProduct){
        this.numProduct = numProduct;
        this.productList = new ArrayList<>();
    }
    // Getter method for retrieving the product list
    public List<Product> getProductList() {
        return productList;
    }
    // Method to notify the GUI of changes and update the table data
    public void notifyGUI(JComboBox<String> menuItemsDropdown, JTable table) {
        // Check if the shoppingGUI instance is not null before calling the updateTableData method
        if (shoppingGUI != null) {
            shoppingGUI.updateTableData(menuItemsDropdown, table, productList);
        }
    }

    @Override
    // Method to add a product to the product list
    public void addProduct (Product product){
        // Check if the product list has reached its maximum limit
        if (productList.size()< numProduct){
            // Add the product to the list and print a success message
            productList.add(product);
            System.out.println("Product added successfully.");
        }
        else{
            // Print an error message if the maximum limit is reached
            System.out.println("Maximum limit of products reached. Cannot add more products.");
        }
        // Notify the GUI of changes after adding the product
        notifyGUI(menuItemsDropdown, table);
    }
    // Method to add a new Electronics product to the product list
    private void addNewElectronicsProduct() {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Prompt the user to enter Electronics product details
        System.out.println("Enter Electronics Product Details");

        // Common attributes for all products
        System.out.print("Enter Product ID: ");
        String productID = scanner.next();

        System.out.print("Enter Product Name: ");
        String productName = scanner.next();

        System.out.print("Enter Number of Items: ");
        int items = scanner.nextInt();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        // Electronics-specific attributes
        System.out.print("Enter Brand: ");
        String brand = scanner.next();

        System.out.print("Enter Warranty Period (in months): ");
        int warrantyPeriod = scanner.nextInt();
        // Prompt the user to choose the category
        System.out.print("Which category do you want to add to: ");
        String category = scanner.next();
         // Create an instance of Electronics product
        Electronics electronicsProduct = new Electronics(productID, productName, items, price, category, brand, warrantyPeriod);
        // Call the addProduct method to add it to the product list
        addProduct(electronicsProduct);
        
    }
    // Method to add a new Clothing product to the product list
    private void addNewClothingProduct() {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Prompt the user to enter Clothing product details
        System.out.println("Enter Clothing Product Details:");

        // Common attributes for all products
        System.out.print("Enter Product ID: ");
        String productID = scanner.next();

        System.out.print("Enter Product Name: ");
        String productName = scanner.next();

        System.out.print("Enter Number of Items: ");
        int items = scanner.nextInt();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        // Clothing-specific attributes
        System.out.print("Enter Size: ");
        String size = scanner.next();

        System.out.print("Enter Color: ");
        String color = scanner.next();
        // Prompt the user to choose the category
        System.out.print("Which category do you want to add to: ");
        String category = scanner.next();

        // Create an instance of Clothing
        Clothing clothingProduct = new Clothing(productID, productName, items, price,category, size, color);

        // Call the addProduct method to add it to the product list
        addProduct(clothingProduct);
    }
    
    @Override
    // Method to delete a product from the product list based on its product ID
    public void deleteProduct(String productID) {
        // Initialize a variable to hold the deleted product (if found)
        Product deletedProduct = null;
        // Iterate through the productList to find the product with the specified productID
        for (Product product : productList) {
             // Check if the current product's productID matches the target productID
            if (product.getProductID().equals(productID)) {
                // Set deletedProduct to the current product, remove it from the list, and print a success message
                deletedProduct = product;
                productList.remove(product);
                System.out.println("Product deleted successfully.");
                break; // Exit the loop once the product is found and removed
            }
        }
        // Check if a product was deleted and print relevant information
        if (deletedProduct != null) {
            // Print information about the deleted product using its toString method
            System.out.println("Deleted Product Information:");
            System.out.println(deletedProduct.toString()); // Use toString method
            // Print the total number of products left in the list
            System.out.println("Total number of products left: " + productList.size());
        } else {
            // Print a message if the product with the specified ID was not found
            System.out.println("Product with ID " + productID + " not found.");
        }
        // Notify the GUI of changes after deleting the product
        notifyGUI(menuItemsDropdown, table);
    }
    
    @Override
    // Method to print the list of products in the system
    public void printProductList() {
         // Check if the productList is empty
        if (productList.isEmpty()) {
            System.out.println("No products in the system.");
            return;// Exit the method if there are no products
        }

        // Sort the product list alphabetically based on the product ID
        Collections.sort(productList, Comparator.comparing(Product::getProductID));
        // Print a header for the list of products
        System.out.println("List of Products in the System:");
        // Iterate through the productList to print information about each product
        for (Product product : productList) {
            // Print the product type based on whether it's an instance of Electronics or Clothing
            System.out.println("Product Type: " + (product instanceof Electronics ? "Electronics" : "Clothing"));
            // Print information about the product using its toString method
            System.out.println(product.toString()); // Use toString method
            // Print an empty line for better readability
            System.out.println();
        }
    }
    
    @Override
    // Method to save the product list to a file
    public void saveToFile(String fileName) {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            // Write a header to the file
            fileWriter.write("Product list: ");
             // Iterate through the productList and write each product's information to the file
            for (Product product : productList) {
                fileWriter.write(product.toString() + "\n");
            }
            // Print a success message to the console
            System.out.println("Product list saved to file: " + fileName);
        } catch (IOException e) {
            // Print an error message if an IOException occurs
            System.out.println("Error saving product list to file: " + e.getMessage());
        }
    }

    // Method to read the product list from a file
    @Override
    public void readFromFile(String fileName) {
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            // Read each line from the file
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Print each line to the console (can be replaced with actual processing logic)
                System.out.println(line);
                // Process each line as needed 
            }
            // Print a success message to the console
            System.out.println("Product list loaded from file: " + fileName);
        } catch (IOException e) {
            // Print an error message if an IOException occurs
            System.out.println("Error loading product list from file: " + e.getMessage());
        }
    }
    // Method to set the ShoppingGUI instance for the WestminsterShoppingManager
    public void setShoppingGUI(ShoppingGUI shoppingGUI) {
        // Set the private shoppingGUI variable to the provided ShoppingGUI instance
        this.shoppingGUI = shoppingGUI;
    }
    // Method to retrieve the current ShoppingGUI instance associated with the WestminsterShoppingManager
    public ShoppingGUI getShoppingGUI() {
         // Return the current ShoppingGUI instance
        return shoppingGUI;
    }
    // Method to open the graphical user interface (GUI)
    void openGUI() {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Prompt the user to enter their username
        System.out.print("Enter Username: ");
        // Prompt the user to enter their password
        String username = scanner.next();
        System.out.print("Enter Password: ");
        String password = scanner.next();
 
        // Optionally, you can use println to add a line break for better readability
        System.out.println("Logging in...");
        
        // Use SwingUtilities.invokeLater to ensure GUI updates happen on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            // Check if the shoppingGUI instance is null
            if (shoppingGUI == null) {
                // If null, create a new ShoppingGUI instance and make it visible
                shoppingGUI = new ShoppingGUI(this, getProductList());
                setShoppingGUI(shoppingGUI);
                shoppingGUI.setVisible(true);
            } else {
                // If not null, make the existing shoppingGUI instance visible
                shoppingGUI.setVisible(true);
            }
        });
    }
    
    @Override
    // Method to run the console menu and handle user input
    public boolean runMenu() {
        // Create a Scanner object for user input
        Scanner scanner = new Scanner(System.in);
        // Declare and initialize the choice variable
        int choice;
        
        // Use a do-while loop to continuously display the menu until the user chooses to exit
        do {
            // Display the console menu options
            System.out.println("-------- Console Menu --------");
            System.out.println("1. Add a new Electronics product");
            System.out.println("2. Add a new Clothing product");
            System.out.println("3. Delete a product");
            System.out.println("4. Print product list");
            System.out.println("5. Save to file");
            System.out.println("6. Read from file");
            System.out.println("7. Open GUI");
            System.out.println("8. Exit");
            
            // Prompt the user to enter their choice
            System.out.print("Enter your choice (1-8): ");
            choice = scanner.nextInt();
            
            // Use a switch statement to execute the corresponding action based on the user's choice
            switch (choice) {
                case 1 -> addNewElectronicsProduct();
                case 2 -> addNewClothingProduct();
                case 3 -> {
                    // Prompt the user to enter the product ID to delete
                    System.out.print("Enter the product ID to delete: ");
                    String productIDToDelete = scanner.next();
                    // Call the deleteProduct method to delete the specified product
                    deleteProduct(productIDToDelete);
                }
                case 4 -> printProductList();
                case 5 -> {
                    // Prompt the user to enter the file name to save
                    System.out.print("Enter the file name to save: ");
                    String saveFileName = scanner.next();
                    // Call the saveToFile method to save the product list to a file
                    saveToFile(saveFileName);
                }
                case 6 -> {
                    // Prompt the user to enter the file name to read
                    System.out.print("Enter the file name to read: ");
                    String readFileName = scanner.next();
                    // Call the readFromFile method to read the product list from a file
                    readFromFile(readFileName);
                }
                case 7 -> openGUI();
                case 8 -> {
                // Display an exit message and exit the program
                System.out.println("Exiting Westminster Shopping Manager. Goodbye!");
                System.exit(0);
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 8.");
            }
        } while (choice != 8); // Continue the loop until the user chooses to exit

        return true;  // Return true to indicate that the program should continue running
    }
}
