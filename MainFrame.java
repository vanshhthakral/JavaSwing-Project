import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class MainFrame extends JFrame {

    private JPanel content;
    private CardLayout cl;

    public MainFrame() {
        setTitle("Neo E-Commerce");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 🌟 Sidebar with gradient
        JPanel sidebar = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0, 60, 85),
                        0, getHeight(), new Color(0, 190, 190)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(250, getHeight()));
        sidebar.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));

        // Sidebar Title
        JLabel titleLabel = new JLabel("E-Commerce");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 50, 0));

        // Sidebar Buttons
        JButton productsBtn = createSidebarButton("Products");
        JButton cartBtn = createSidebarButton("Cart");
        JButton checkoutBtn = createSidebarButton("Checkout");

        sidebar.add(titleLabel);
        sidebar.add(productsBtn);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(cartBtn);
        sidebar.add(Box.createVerticalStrut(20));
        sidebar.add(checkoutBtn);
        sidebar.add(Box.createVerticalGlue());

        // 📦 Content Panel
        content = new JPanel();
        cl = new CardLayout();
        content.setLayout(cl);
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Scrollable Product Panel
        JScrollPane scrollableProducts = new JScrollPane(new ProductPanel());
        scrollableProducts.setBorder(null);
        scrollableProducts.getVerticalScrollBar().setUnitIncrement(16);
        scrollableProducts.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableProducts.getVerticalScrollBar().setUI(createModernScrollBarUI());

        // Placeholder Cart and Checkout (will recreate on click)
        content.add(scrollableProducts, "products");
        content.add(new JPanel(), "cart");
        content.add(new JPanel(), "checkout");

        // Button Actions
        productsBtn.addActionListener(e -> cl.show(content, "products"));

        cartBtn.addActionListener(e -> {
            content.remove(1); // remove old cart
            JScrollPane newCart = new JScrollPane(new CartPanel());
            newCart.setBorder(null);
            newCart.getVerticalScrollBar().setUnitIncrement(16);
            newCart.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            newCart.getVerticalScrollBar().setUI(createModernScrollBarUI());
            content.add(newCart, "cart");
            cl.show(content, "cart");
        });

        checkoutBtn.addActionListener(e -> {
            content.remove(2); // remove old checkout
            JScrollPane checkoutScroll = new JScrollPane(new CheckoutPanel());
            checkoutScroll.setBorder(null);
            checkoutScroll.getVerticalScrollBar().setUnitIncrement(16);
            checkoutScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            checkoutScroll.getVerticalScrollBar().setUI(createModernScrollBarUI());
            content.add(checkoutScroll, "checkout");
            cl.show(content, "checkout");
        });

        // Layout
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(sidebar, BorderLayout.WEST);
        wrapper.add(content, BorderLayout.CENTER);

        // Footer
        JLabel copyright = new JLabel("© 2025 NeoStore");
        copyright.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        copyright.setForeground(new Color(0, 130, 130));
        copyright.setHorizontalAlignment(SwingConstants.CENTER);
        copyright.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        add(wrapper, BorderLayout.CENTER);
        add(copyright, BorderLayout.SOUTH);
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(160, 45));
        button.setFocusPainted(false);
        button.setBackground(new Color(0, 180, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 210, 210));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 180, 180));
            }
        });

        return button;
    }

    private BasicScrollBarUI createModernScrollBarUI() {
        return new BasicScrollBarUI() {
            private final Dimension d = new Dimension();

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(d);
                button.setMinimumSize(d);
                button.setMaximumSize(d);
                return button;
            }

            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(0, 180, 180);
                this.trackColor = new Color(240, 240, 240);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
