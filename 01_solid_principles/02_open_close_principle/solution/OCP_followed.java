import java.util.ArrayList;
import java.util.List;

// Product class represents an item that can be added to the shopping cart.
// It only stores product information (name and price).
class Product {
    public String name;
    public double price;

    // Constructor to initialize product details
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

// ShoppingCart class is responsible ONLY for managing cart data and logic.
// This follows the Single Responsibility Principle (SRP).
class ShoppingCart {

    // List to store all products added to the cart
    private List<Product> products = new ArrayList<>();

    // Adds a product to the shopping cart
    public void addProduct(Product p) {
        products.add(p);
    }

    // Returns the list of products currently in the cart
    public List<Product> getProducts() {
        return products;
    }

    // Calculates the total cost of all products in the cart
    public double calculateTotal() {
        double total = 0;

        // Loop through all products and add their price
        for (Product p : products) {
            total += p.price;
        }

        return total;
    }
}

// ShoppingCartPrinter class is responsible ONLY for displaying
// the contents of the shopping cart (printing invoice).
// This separates presentation logic from business logic (SRP).
class ShoppingCartPrinter {

    // Reference to the cart that will be printed
    private ShoppingCart cart;

    // Constructor receives the cart to print
    public ShoppingCartPrinter(ShoppingCart cart) {
        this.cart = cart;
    }

    // Prints invoice details for the cart
    public void printInvoice() {

        System.out.println("Shopping Cart Invoice:");

        // Print each product in the cart
        for (Product p : cart.getProducts()) {
            System.out.println(p.name + " - Rs " + p.price);
        }

        // Print total amount
        System.out.println("Total: Rs " + cart.calculateTotal());
    }
}

// Persistence interface defines a general contract for saving a cart.
// This allows the program to support multiple storage types without
// modifying existing code (Open/Closed Principle).
interface Persistence {

    // Any persistence class must implement this method
    void save(ShoppingCart cart);
}

// SQLPersistence saves the cart into a SQL database.
class SQLPersistence implements Persistence {

    @Override
    public void save(ShoppingCart cart) {
        System.out.println("Saving shopping cart to SQL DB...");
    }
}

// MongoPersistence saves the cart into a MongoDB database.
class MongoPersistence implements Persistence {

    @Override
    public void save(ShoppingCart cart) {
        System.out.println("Saving shopping cart to MongoDB...");
    }
}

// FilePersistence saves the cart into a file.
class FilePersistence implements Persistence {

    @Override
    public void save(ShoppingCart cart) {
        System.out.println("Saving shopping cart to a file...");
    }
}

// Main class to demonstrate how everything works together
public class OCP_followed {

    public static void main(String[] args) {

        // Create a shopping cart
        ShoppingCart cart = new ShoppingCart();

        // Add products to the cart
        cart.addProduct(new Product("Laptop", 50000));
        cart.addProduct(new Product("Mouse", 2000));

        // Print the invoice using a separate printer class
        ShoppingCartPrinter printer = new ShoppingCartPrinter(cart);
        printer.printInvoice();

        // Different persistence implementations
        Persistence db    = new SQLPersistence();
        Persistence mongo = new MongoPersistence();
        Persistence file  = new FilePersistence();

        // Save the cart using different storage mechanisms
        // Notice that we can add new persistence types
        // without modifying existing classes (Open/Closed Principle)
        db.save(cart);
        mongo.save(cart);
        file.save(cart);
    }
}