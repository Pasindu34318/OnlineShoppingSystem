import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product ID: " + id + ", Name: " + name + ", Price: $" + price;
    }
}

class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getName() + " - Quantity: " + quantity + ", Total: $" + getTotalPrice();
    }
}

public class OnlineShoppingSystem {
    private static ArrayList<Product> products = new ArrayList<>();
    private static ArrayList<CartItem> cart = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadProducts();
        while (true) {
            System.out.println("\nOnline Shopping System");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addToCart();
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    System.out.println("Thank you for shopping! Goodbye.");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void loadProducts() {
        products.add(new Product(1, "Laptop", 800.00));
        products.add(new Product(2, "Smartphone", 500.00));
        products.add(new Product(3, "Headphones", 50.00));
        products.add(new Product(4, "Keyboard", 30.00));
        products.add(new Product(5, "Mouse", 20.00));
    }

    private static void viewProducts() {
        System.out.println("\nAvailable Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void addToCart() {
        System.out.print("Enter Product ID to add to cart: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        for (Product product : products) {
            if (product.getId() == productId) {
                addOrUpdateCart(product, quantity);
                System.out.println("Product added to cart successfully!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    private static void addOrUpdateCart(Product product, int quantity) {
        for (CartItem item : cart) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cart.add(new CartItem(product, quantity));
    }

    private static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }
        System.out.println("\nYour Cart:");
        double total = 0.0;
        for (CartItem item : cart) {
            System.out.println(item);
            total += item.getTotalPrice();
        }
        System.out.println("Total: $" + total);
    }

    private static void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add products before checkout.");
            return;
        }
        viewCart();
        System.out.println("Checkout successful! Thank you for shopping.");
        cart.clear();
    }
}