import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static final List<Product> items = new ArrayList<>();

    public static void add(Product p) {
        items.add(p);
    }

    public static List<Product> getItems() {
        return items;
    }

    public static double getTotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    public static void clear() {
        items.clear();
    }
}
