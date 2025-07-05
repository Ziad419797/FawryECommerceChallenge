import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Product cheese = new ExpirableShippableProduct("Cheese", 20, 5, LocalDate.of(2025, 7, 30), 0.125);
        Product biscuits = new ExpirableShippableProduct("Biscuits", 5, 2, LocalDate.of(2025, 8, 1), 0.50);
        Product tv = new ShippableProduct("TV", 500, 3, 2.0);
        Product mobile = new ShippableProduct("TV", 500, 3, 2.0);
        Product scratchCard = new Product("Scratch Card", 50, 10);

        Customer customer = new Customer("Ziad", 1500);

        Cart cart = new Cart();

        cart.addProduct(cheese, 5);
        cart.addProduct(biscuits, 1);
        cart.addProduct(tv, 2);
        cart.addProduct(scratchCard, 2);

        CheckoutService.checkout(customer, cart);
    }
}
