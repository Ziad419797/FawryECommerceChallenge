import java.util.ArrayList;
import java.util.List;

public class CheckoutService {

    private static final double SHIPPING_RATE_PER_KG = 30.0;

    public static void checkout(Customer customer, Cart cart) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Cart is empty. Cannot proceed with checkout.");
        }

        List<CartItem> items = cart.getItems();
        double subtotal = 0.0;
        List<Shippable> itemsToShip = new ArrayList<>();

        for (CartItem item : items) {
            Product product = item.getProduct();

            if (product instanceof Expirable) {
                Expirable expirable = (Expirable) product;
                if (expirable.isExpired()) {
                    throw new IllegalStateException(product.getName() + " is expired.");
                }
            }

            if (!product.isAvailable(item.getQuantity())) {
                throw new IllegalStateException(product.getName() + " is out of stock.");
            }

            if (product instanceof Shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    itemsToShip.add((Shippable) product);
                }
            }

            subtotal += item.getTotalPrice();
        }

        double totalWeight = itemsToShip.stream().mapToDouble(Shippable::getWeight).sum();
        double shippingFee = totalWeight * SHIPPING_RATE_PER_KG;
        double amountToPay = subtotal + shippingFee;

        if (customer.getBalance() < amountToPay) {
            throw new IllegalStateException("Insufficient balance to complete purchase.");
        }

        customer.deductBalance(amountToPay);

        for (CartItem item : items) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        ShippingService.ship(itemsToShip);

        System.out.println("** Checkout receipt **");
        for (CartItem item : items) {
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + " " + item.getTotalPrice());
        }
        System.out.println("---------------------------");
        System.out.println("Subtotal:        " + subtotal);
        System.out.println("Shipping:        " + shippingFee);
        System.out.println("Amount Paid:     " + amountToPay);
        System.out.println("---------------------------");
        System.out.println("Current balance: " + customer.getBalance());
        System.out.println("---------------------------");
        System.out.println("END.\n");
    }
}
