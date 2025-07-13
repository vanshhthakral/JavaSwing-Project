import java.util.ArrayList;

public class Cart {
    private static ArrayList<Product> items = new ArrayList<>();

    public static void add(Product p) {
        items.add(p);
    }

    public static ArrayList<Product> getItems() {
        return items;
    }

    public static void clear() {
        items.clear();
    }
}
