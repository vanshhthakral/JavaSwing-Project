import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class ProductPanel extends JPanel {

    public ProductPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(250, 250, 250));
        setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        Product[] products = {
            new Product("MacBook Pro", 109999.99, "macbook.png"),
            new Product("iPhone 14", 80099.99, "iphone.png"),
            new Product("AirPods Pro", 6849.99, "earpods.png"),
            new Product("Headphones", 4249.99, "Headphones.jpeg"),
            new Product("Watch", 3249.99, "watch.png"),
            new Product("Bluetooth Speaker", 2499.99, "speaker.png"),
            new Product("Gaming Mouse", 1899.99, "mouse.png"),
            new Product("Mechanical Keyboard", 2899.99, "keyboard.png"),
            new Product("Webcam", 1799.99, "webcam.png"),
            new Product("Smart TV", 40999.99, "tv.png")
        };

        for (Product product : products) {
            add(createProductCard(product));
            add(Box.createVerticalStrut(20)); // spacing
        }
    }

    private JPanel createProductCard(Product product) {
        JPanel card = new JPanel(new BorderLayout(15, 10));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(680, 140));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 140));
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(0, 160, 160), 2, true),
                new EmptyBorder(10, 10, 10, 10)
        ));

        // 🖼 Product Image
        JLabel imageLabel;
        try {
            ImageIcon icon = new ImageIcon(product.getImagePath());
            Image img = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel = new JLabel("🖼️");
            imageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setPreferredSize(new Dimension(100, 100));
        }
        imageLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // 📋 Info
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setOpaque(false);

        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nameLabel.setForeground(new Color(30, 30, 30));

        JLabel priceLabel = new JLabel("₹" + product.getPrice());
        priceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        priceLabel.setForeground(new Color(0, 153, 0));

        infoPanel.add(nameLabel);
        infoPanel.add(Box.createVerticalStrut(5));
        infoPanel.add(priceLabel);

        // 🛒 Add to Cart
        JButton addBtn = new JButton("Add to Cart");
        addBtn.setFocusPainted(false);
        addBtn.setBackground(new Color(0, 160, 160));
        addBtn.setForeground(Color.WHITE);
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        addBtn.setPreferredSize(new Dimension(140, 40));
        addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Hover effect
        addBtn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                addBtn.setBackground(new Color(0, 190, 190));
            }

            public void mouseExited(MouseEvent e) {
                addBtn.setBackground(new Color(0, 160, 160));
            }
        });

        addBtn.addActionListener(e -> {
            Cart.add(product);
            JOptionPane.showMessageDialog(this, product.getName() + " added to cart!");
        });

        card.add(imageLabel, BorderLayout.WEST);
        card.add(infoPanel, BorderLayout.CENTER);
        card.add(addBtn, BorderLayout.EAST);

        return card;
    }

    // ✅ Test Panel Alone
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("🛍 Product Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 700);

            ProductPanel productPanel = new ProductPanel();
            JScrollPane scroll = new JScrollPane(productPanel);
            scroll.setBorder(null);
            scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.getVerticalScrollBar().setUnitIncrement(16);
            scroll.getVerticalScrollBar().setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = new Color(0, 180, 180);
                }
            });

            frame.add(scroll);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
