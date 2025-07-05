public class Product {
    protected String name;
    protected double price;
    protected int quantity;
    //protected => partial encapsulation

    //constructor -- this =>refers to current object
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    public boolean isAvailable(int requestedQuantity) {
        return quantity >= requestedQuantity;
    }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
