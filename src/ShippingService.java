import java.util.*;

public class ShippingService {
    public static void ship(List<Shippable> itemsToShip) {
        if (itemsToShip.isEmpty()) {
            return;
        }

        System.out.println("** Shipment notice **");

        Map<String, ShippableSummary> summaryMap = new LinkedHashMap<>();

        for (Shippable item : itemsToShip) {
            String key = item.getName() + "-" + item.getWeight();
            summaryMap.putIfAbsent(key, new ShippableSummary (item.getName(), item.getWeight(),0 ));
            summaryMap.get(key).incrementCount();
        }

        double totalWeight = 0.0;

        for (ShippableSummary summary : summaryMap.values()) {
            double productWeight = summary.count * summary.weight;
            System.out.println(summary.count + "x " + summary.name + " " + (productWeight * 1000) + "g");
            totalWeight += productWeight;
        }

        System.out.println("Total package weight " + totalWeight + "kg");
    }

    private static class ShippableSummary {
        String name;
        double weight;
        int count;

        ShippableSummary(String name, double weight, int count) {
            this.name = name;
            this.weight = weight;
            this.count = count;
        }

        void incrementCount() {
            this.count++;
        }
    }
}
