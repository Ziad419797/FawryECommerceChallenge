import java.time.LocalDate;

//extends => inherit -- LocalDate from java 8
public class ExpirableProduct extends Product implements Expirable {
    private LocalDate expiryDate;

    //super => calls the parent constructor (product) instead of rewriting code
    public ExpirableProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    public boolean isExpired() {
        return LocalDate.now().isAfter(expiryDate);
    }
}
