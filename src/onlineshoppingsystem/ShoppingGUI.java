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
import javax.swing.table.TableRowSorter;
import javax.swing.event.ListSelectionEvent;
import java.util.List;

// ShoppingGUI class extending JFrame to create the graphical user interface
public class ShoppingGUI extends JFrame {
    // List to store product data
    private final List<Product> productList;
    // DefaultTableModel for managing data in the table
    private final DefaultTableModel shoppingTableModel = new DefaultTableModel();
    // JTextArea for displaying details
    private final JTextArea detailsTextArea = new JTextArea();
     // ShoppingCart instance to manage the shopping cart
    private final ShoppingCart shoppingCart = new ShoppingCart();
    // JComboBox for menu items
    private JComboBox<String> menuItemsDropdown;
    // JTable for displaying product data
    private JTable table;
    // ShoppingCartDialog for displaying the shopping cart dialog
    private final ShoppingCartDialog shoppingCartDialog = new ShoppingCartDialog();
    
    // Constructor for the ShoppingGUI class, taking a WestminsterShoppingManager and a productList as parameters
    public ShoppingGUI(WestminsterShoppingManager shoppingManager,List<Product> productList) {
        // Set the title of the JFrame
        setTitle("Westminster Shopping Center");
        // Set the size of the JFrame
        setSize(800, 600);
        // Set the default close operation for the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the product list with the provided productList
        this.productList = productList;
       
        // Create a JPanel with a null layout
        JPanel panel = new JPanel(null);
        
        // Add columns to the shoppingTableModel for the JTable
        shoppingTableModel.addColumn("Product ID");
        shoppingTableModel.addColumn("Name");
        shoppingTableModel.addColumn("Category");
        shoppingTableModel.addColumn("Price");
        shoppingTableModel.addColumn("Info");
        
        // Create a JTable with the shoppingTableModel
        JTable table = new JTable(shoppingTableModel);
        // Create a JScrollPane to contain the JTable for scrolling
        JScrollPane tableScrollPane = new JScrollPane(table);
        // Set the bounds for the JScrollPane (x, y, width, height)
        tableScrollPane.setBounds(150, 80, 500, 300); 
        // Create a JButton for shopping cart
        JButton shoppingButton = new JButton("Shopping cart");
        // Set the bounds for the button (x, y, width, height)
        shoppingButton.setBounds(630, 20, 120, 30);
        // Define an array of menu items
        String[] menuItems = {"All", "Electronics", "Clothing"};
        // Create a JComboBox with the menuItems array
        JComboBox<String>menuItemsDropdown = new JComboBox<>(menuItems);
        menuItemsDropdown.setPreferredSize(new Dimension(150, 30));
         // Set the bounds for the JComboBox (x, y, width, height)
        menuItemsDropdown.setBounds(300, 20, 150, 30); 
        
        // Make the detailsTextArea non-editable and create a JScrollPane for scrolling
        detailsTextArea.setEditable(false);
        JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
        // Set the bounds for the JScrollPane (x, y, width, height)
        detailsScrollPane.setBounds(150, 400, 500, 100); 
        
        // Create a JButton for adding products to the shopping cart
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        // Set the bounds for the button (x, y, width, height)
        addToCartButton.setBounds(350, 510, 120, 30);
        
        // Create a JLabel for displaying a text label
        JLabel textLabel;
        textLabel = new JLabel("Select product category");
        // Set the bounds for the JLabel (x, y, width, height)
        textLabel.setBounds(30, 20, 200, 30);
        // Add the JLabel to the current container (presumably the JFrame)
        this.add(textLabel);
        
        // Add an ActionListener to the menuItemsDropdown to update table data when the selection changes
        menuItemsDropdown.addActionListener(e -> updateTableData(menuItemsDropdown, table, productList));
        // Add an ActionListener to the addToCartButton to add selected products to the shopping cart
        addToCartButton.addActionListener(e -> addToShoppingCart(table));
        // Add an ActionListener to the shoppingButton to open the shopping cart dialog
        shoppingButton.addActionListener(e -> openShoppingCartDialog());

        // Add components to the panel
        panel.add(tableScrollPane);// Add the JScrollPane containing the JTable
        panel.add(menuItemsDropdown);// Add the JComboBox for selecting product categories
        panel.add(detailsScrollPane); // Add the JScrollPane for displaying details
        panel.add(addToCartButton);// Add the JButton for adding products to the shopping cart
        panel.add(shoppingButton); // Add the JButton for opening the shopping cart dialog
        
        // Create a TableRowSorter for the shoppingTableModel and set it to the JTable
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(shoppingTableModel);
        table.setRowSorter(sorter);
        // Add a ListSelectionListener to the JTable to display details when a row is selected
        table.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    // Get the selected product from the productList and display its details
                    Product selectedProduct = productList.get(selectedRow);
                    displayProductDetails(selectedProduct);
                }
            }
        });
        // Add the panel to the frame
        add(panel);

        // Make the frame visible
        setVisible(true);
    }
    // Method to get the JComboBox for menu items
    public JComboBox<String> getmenuItemsDropdown() {
        // Return the menuItemsDropdown JComboBox
        return menuItemsDropdown;
    }
    // Method to get the JTable for displaying product data
    public JTable getTable() {
        // Return the table JTable
        return table;
    }
    // Method to display product details in the detailsTextArea
    private void displayProductDetails(Product product) {
        detailsTextArea.setText(""); // Clear previous content
        
        // Check if the product is not null
        if (product != null) {
            // Display product details in a vertical format
            String formattedDetails = String.format("Product ID: %s\nProduct Name: %s\nCategory: %s\nPrice: $%.2f\n",
                    String.valueOf(product.getProductID()), product.getProductName(),
                    (product instanceof Electronics) ? "Electronics" : "Clothes",
                    product.getPrice());

            // Append additional details based on the product type
            if (!(product instanceof Electronics electronicsProduct)) {
                 // If the product is not an Electronics type
                if (product instanceof Clothing clothingProduct) {
                    // If the product is a Clothing type, append size and color details
                    formattedDetails += String.format("Size: %s\nColor: %s\n",
                            clothingProduct.getSize(), clothingProduct.getColor());
                }
            } else {
                // If the product is an Electronics type, append warranty period and brand details
                formattedDetails += String.format("Warranty Period: %s\nBrand: %s\n",
                        electronicsProduct.getWarrantyPeriod(), electronicsProduct.getBrand());
            }

            // Display each piece of information in a separate line
            // string using newline characters and appending each line to the detailsTextArea
            String[] detailsArray = formattedDetails.split("\n");
            for (String detail : detailsArray) {
                detailsTextArea.append(detail + "\n");
            }
        }
    }
    // Method to update the data in the JTable based on the selected product type
    void updateTableData(JComboBox<String> menuItemsDropdown, JTable table, List<Product> productList) {
        // Clear existing rows in the shoppingTableModel
        shoppingTableModel.setRowCount(0);
        // Get the selected item from the menuItemsDropdown
        String selectedMenuItems = (String) menuItemsDropdown.getSelectedItem();

        // Add products to the table based on the selected product type
        for (Product product : productList) {
            // Check if the selected product type is "All" or matches the type of the current product
            if ("All".equals(selectedMenuItems) ||
                    (selectedMenuItems.equals("Electronics") && product instanceof Electronics) ||
                    (selectedMenuItems.equals("Clothing") && product instanceof Clothing)) {
                // Get an array of product information and add it as a row to the shoppingTableModel
                String[] productInfo = getProductInfoArray(product);
                shoppingTableModel.addRow(productInfo);
            }
        }
    }
    // Method to get an array of product information based on the product type
    private String[] getProductInfoArray(Product product) {
        // Check the type of product and extract information accordingly
        if (!(product instanceof Electronics electronicsProduct)) if (product instanceof Clothing clothingProduct) {
             // Return an array with Clothing-specific information
            return new String[]{
                String.valueOf(clothingProduct.getProductID()),
                clothingProduct.getProductName(),
                "Clothes",
                String.valueOf(clothingProduct.getPrice()),
                "Size: " + clothingProduct.getSize() +
                    ", Color: " + clothingProduct.getColor()
            };
        } else {
            // If the product is neither Electronics nor Clothing, return an array of information from the toString method
            return product.toString().split(", ");
        } else {
             // If the product is an Electronics type, return an array with Electronics-specific information
            return new String[]{
                String.valueOf(electronicsProduct.getProductID()),
                electronicsProduct.getProductName(),
                "Electronics",
                String.valueOf(electronicsProduct.getPrice()),
                "Warranty Period: " + electronicsProduct.getWarrantyPeriod() +
                    ", Brand: " + electronicsProduct.getBrand()
            };
        }
    }
    
    // Method to add the selected product to the shopping cart
    private void addToShoppingCart(JTable table) {
        // Get the index of the selected row in the table
        int selectedRow = table.getSelectedRow();
         // Check if a row is selected
        if (selectedRow != -1) {
            // Get the selected product from the productList based on the selected row
            Product selectedProduct = productList.get(selectedRow);

            // Calculate the total cost of the selected product (initially set to the product's price)
            double totalCost = selectedProduct.getPrice();
            // Update the shopping cart dialog with the selected product and its total cost
            shoppingCartDialog.updateCartDetails(selectedProduct,totalCost);

            // Get the quantity of the selected product already in the shopping cart
            int quantityInCart = shoppingCart.getProductQuantity(selectedProduct);

            // Update the shopping cart dialog with the selected product's information and quantity
            shoppingCartDialog.updateProductInfo(selectedProduct, quantityInCart);
        }
    }
    // Method to open a dialog displaying the current content of the shopping cart and its total cost after applying discounts
    public void openShoppingCart() {
        // Retrieve the list of products in the shopping cart and calculate the total cost
        List<Product> cartProducts = shoppingCart.getProducts();
        double totalCost = shoppingCart.calculateTotalCost();
        // Create a StringBuilder to store information about the shopping cart
        StringBuilder cartInfo = new StringBuilder("Shopping Cart:\n");
        // Append each product's information to the StringBuilder
        for (Product product : cartProducts) {
            cartInfo.append(product.toString()).append("\n");
        }

        // Apply any discounts to the total cost
        double discountedCost = shoppingCart.applyDiscount(totalCost);
        // Append the total cost (after discounts) to the cartInfo StringBuilder
        cartInfo.append("\nTotal Cost: $").append(String.format("%.2f", discountedCost));

       // Show the shopping cart information in a dialog using JOptionPane
        JOptionPane.showMessageDialog(this, cartInfo.toString(), "Shopping Cart", JOptionPane.INFORMATION_MESSAGE);
    }
    // Method to open the shopping cart dialog window
    private void openShoppingCartDialog() {
        // Display the shopping cart window
        shoppingCartDialog.setVisible(true);
    }    
}


