import javax.swing.*;

public class ProductApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Product Catalog");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.add(new JScrollPane(new ProductPanel()));
            frame.setVisible(true);
        });
    }
}
