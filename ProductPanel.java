import java.awt.*;
import javax.swing.*;

public class ProductPanel extends JPanel {

    public ProductPanel() {
        setLayout(new GridLayout(0, 1, 10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        Product[] products = {
            new Product("MacBook Pro", 1999.99, "macbook.png"),
            new Product("iPhone 14", 899.99, "iphone.png"),
            new Product("AirPods Pro", 249.99, "earpods.png")
        };

        for (Product product : products) {
            JPanel productCard = new JPanel(new BorderLayout(10, 10));
            productCard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            productCard.setBackground(Color.WHITE);
            productCard.setPreferredSize(new Dimension(400, 120));

            // Image
            ImageIcon icon = new ImageIcon(product.getImagePath());
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            imageLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Info
            JPanel infoPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel(product.getName(), JLabel.LEFT);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            JLabel priceLabel = new JLabel("₹" + product.getPrice(), JLabel.LEFT);
            priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            infoPanel.add(nameLabel, BorderLayout.NORTH);
            infoPanel.add(priceLabel, BorderLayout.SOUTH);

            // Button
            JButton addBtn = new JButton("Add to Cart");
            addBtn.setPreferredSize(new Dimension(120, 40));
            addBtn.addActionListener(e -> {
                Cart.add(product);
                JOptionPane.showMessageDialog(this, product.getName() + " added to cart!");
            });

            productCard.add(imageLabel, BorderLayout.WEST);
            productCard.add(infoPanel, BorderLayout.CENTER);
            productCard.add(addBtn, BorderLayout.EAST);

            add(productCard);
        }
    }

    // ✅ Add this method to run ProductPanel directly
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Product Catalog");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);
            frame.setLocationRelativeTo(null);
            frame.add(new JScrollPane(new ProductPanel())); // Scrollable panel
            frame.setVisible(true);
        });
    }
}
