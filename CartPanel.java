import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.util.ArrayList;

public class CartPanel extends JPanel {
    private JPanel itemsPanel;
    private JLabel totalLabel;

    public CartPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // 🔷 Title
        JLabel title = new JLabel("🛒 Your Shopping Cart", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(new Color(0, 102, 102));
        title.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(title, BorderLayout.NORTH);

        // 📦 Items Panel inside ScrollPane
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getVerticalScrollBar().setUI(createModernScrollBarUI());

        add(scrollPane, BorderLayout.CENTER);

        // 💰 Total Panel
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        totalPanel.setBackground(new Color(245, 245, 245));
        totalPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        totalLabel = new JLabel();
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalLabel.setForeground(new Color(0, 128, 128));
        totalPanel.add(totalLabel);

        add(totalPanel, BorderLayout.SOUTH);

        loadCartItems();
    }

    public void loadCartItems() {
        itemsPanel.removeAll();
        double total = 0;

        ArrayList<Product> cartItems = Cart.getItems();
        if (cartItems.isEmpty()) {
            JLabel emptyLabel = new JLabel("🧺 Your cart is empty.");
            emptyLabel.setFont(new Font("Segoe UI", Font.ITALIC, 16));
            emptyLabel.setForeground(new Color(120, 120, 120));
            emptyLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(Color.WHITE);
            emptyPanel.setLayout(new BoxLayout(emptyPanel, BoxLayout.Y_AXIS));
            emptyPanel.add(emptyLabel);

            itemsPanel.add(Box.createVerticalGlue());
            itemsPanel.add(emptyPanel);
            itemsPanel.add(Box.createVerticalGlue());
        } else {
            for (Product product : cartItems) {
                JPanel item = new JPanel(new BorderLayout());
                item.setBackground(Color.WHITE);
                item.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
                        BorderFactory.createEmptyBorder(15, 20, 15, 20)
                ));

                JLabel name = new JLabel(product.getName());
                name.setFont(new Font("Segoe UI", Font.PLAIN, 15));
                name.setForeground(new Color(40, 40, 40));

                JLabel price = new JLabel("₹" + product.getPrice());
                price.setFont(new Font("Segoe UI", Font.BOLD, 15));
                price.setForeground(new Color(0, 153, 0));

                item.add(name, BorderLayout.WEST);
                item.add(price, BorderLayout.EAST);
                itemsPanel.add(item);

                total += product.getPrice();
            }
        }

        totalLabel.setText("Total: ₹" + String.format("%.2f", total));
        revalidate();
        repaint();
    }

    // 🎨 Custom Scrollbar UI for better appearance
    private BasicScrollBarUI createModernScrollBarUI() {
        return new BasicScrollBarUI() {
            private final Dimension zeroDim = new Dimension();

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton btn = new JButton();
                btn.setPreferredSize(zeroDim);
                btn.setMinimumSize(zeroDim);
                btn.setMaximumSize(zeroDim);
                return btn;
            }

            @Override
            protected void configureScrollBarColors() {
                thumbColor = new Color(0, 180, 180);
                trackColor = new Color(240, 240, 240);
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setPaint(thumbColor);
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
                g2.dispose();
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                g.setColor(trackColor);
                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
            }
        };
    }

    // ✅ Optional Test
    public static void main(String[] args) {
        Cart.add(new Product("Smart Watch", 2499.99, ""));
        Cart.add(new Product("Wireless Earbuds", 1199.49, ""));
        Cart.add(new Product("Portable Charger", 899.99, ""));
        Cart.add(new Product("Laptop Stand", 999.00, ""));
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Cart Panel Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 450);
            frame.add(new CartPanel());
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
