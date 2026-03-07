import java.util.ArrayList;
import java.util.List;

/*
 * Product class
 * This class represents a product in an e-commerce system.
 * Each product has:
 *  - a name
 *  - a price
 */
class Product {
    public String name;
    public double price;

    // Constructor to create a product object
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}


/*
 * ShoppingCart class
 *
 * This class represents a user's shopping cart.
 * It stores products and performs different operations on them.
 *
 * HOWEVER:
 * This class is violating the Single Responsibility Principle (SRP).
 *
 * SRP states:
 * "A class should have only ONE reason to change."
 *
 * But this class is doing MULTIPLE responsibilities:
 *
 * 1. Managing products in the cart
 * 2. Calculating the total price
 * 3. Printing the invoice
 * 4. Saving cart data to a database
 *
 * These should ideally be handled by different classes.
 */
class ShoppingCart {

    // List to store all products added to the cart
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public List<Product> getProducts() {
        return products;
    }

    /*
     * Responsibility #1
     * Calculates the total price of all products in the cart
     */
    public double calculateTotal() {
        double total = 0;

        // Loop through each product in the cart
        for (Product p : products) {

            // Add each product's price to the total
            total += p.price;
        }

        return total;
    }

    /*
     * Responsibility #2 (SRP Violation)
     *
     * This method prints the invoice for the cart.
     * Printing invoices should ideally be handled by a
     * separate class such as InvoicePrinter.
     */
    public void printInvoice() {

        System.out.println("Shopping Cart Invoice:");

        // Print each product in the cart
        for (Product p : products) {
            System.out.println(p.name + " - Rs " + p.price);
        }

        // Print total price
        System.out.println("Total: Rs " + calculateTotal());
    }


    /*
     * Responsibility #3 (SRP Violation)
     *
     * This method simulates saving the shopping cart
     * to a database.
     *
     * Database operations should ideally be handled by
     * another class such as CartRepository.
     */
    public void saveToDatabase() {
        System.out.println("Saving shopping cart to database...");
    }
}

public class SRP_violated {

    public static void main(String[] args) {

        // Create a new shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Add products to the cart
        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        // Print invoice for all products
        cart.printInvoice();

        // Save cart data to database
        cart.saveToDatabase();
    }
}