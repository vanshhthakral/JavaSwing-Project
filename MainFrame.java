import java.awt.*;
import javax.swing.*;


public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Swing E-Commerce");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panels
        JPanel header = new JPanel();
        header.setBackground(Color.DARK_GRAY);
        JLabel title = new JLabel("🛒 Swing E-Commerce Store");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        header.add(title);

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(240, 240, 240));
        sidebar.setPreferredSize(new Dimension(140, 600));

        JButton productsBtn = new JButton("Products");
        JButton cartBtn = new JButton("Cart");
        JButton checkoutBtn = new JButton("Checkout");

        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(productsBtn);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(cartBtn);
        sidebar.add(Box.createVerticalStrut(10));
        sidebar.add(checkoutBtn);

        JPanel footer = new JPanel();
        JLabel footerText = new JLabel("© 2025 Vansh's E-Shop");
        footer.add(footerText);

        // Main content area
        JPanel content = new JPanel(new CardLayout());
        ProductPanel productPanel = new ProductPanel();
        CartPanel cartPanel = new CartPanel();
        CheckoutPanel checkoutPanel = new CheckoutPanel();

        content.add(productPanel, "products");
        content.add(cartPanel, "cart");
        content.add(checkoutPanel, "checkout");

        CardLayout cl = (CardLayout) content.getLayout();

        productsBtn.addActionListener(e -> {
            content.remove(1);
            content.add(new CartPanel(), "cart");
            cl.show(content, "products");
        });

        cartBtn.addActionListener(e -> {
            content.remove(1);
            content.add(new CartPanel(), "cart");
            cl.show(content, "cart");
        });

        checkoutBtn.addActionListener(e -> {
            content.remove(2);
            content.add(new CheckoutPanel(), "checkout");
            cl.show(content, "checkout");
        });

        // Layout all parts
        setLayout(new BorderLayout());
        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(content, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
