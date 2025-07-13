import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class CheckoutPanel extends JPanel {

    public CheckoutPanel() {
        setLayout(new BorderLayout());

        // Background gradient
        JPanel bgPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(224, 247, 250),
                        0, getHeight(), new Color(200, 230, 201));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bgPanel.setLayout(new GridBagLayout());

        // White Card Panel
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setPreferredSize(new Dimension(440, 400));
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(180, 180, 180), 1, true),
                new EmptyBorder(30, 30, 30, 30)
        ));

        // Title
        JLabel title = new JLabel("Checkout Summary", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setBorder(new EmptyBorder(0, 0, 20, 0));
        card.add(title);
        card.add(createSeparator());

        // Shipping Address
        JLabel addressLabel = new JLabel("Shipping Address:", JLabel.CENTER);
        addressLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        addressLabel.setBorder(new EmptyBorder(15, 0, 5, 0));
        addressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea addressArea = new JTextArea(3, 20);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        addressArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addressArea.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));
        JScrollPane addressScroll = new JScrollPane(addressArea);
        addressScroll.setBorder(BorderFactory.createEmptyBorder());

        card.add(addressLabel);
        card.add(addressScroll);
        card.add(createSeparator());

        // Contact Number
        JLabel contactLabel = new JLabel("Contact Number:", JLabel.CENTER);
        contactLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        contactLabel.setBorder(new EmptyBorder(15, 0, 5, 0));
        contactLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField contactField = new JTextField();
        contactField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        contactField.setBorder(BorderFactory.createLineBorder(new Color(180, 180, 180)));

        card.add(contactLabel);
        card.add(contactField);
        card.add(createSeparator());

        // Place Order Button
        JButton placeOrderBtn = new JButton("Place Order");
        placeOrderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeOrderBtn.setBackground(new Color(76, 175, 80));
        placeOrderBtn.setForeground(Color.WHITE);
        placeOrderBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        placeOrderBtn.setFocusPainted(false);
        placeOrderBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        placeOrderBtn.setPreferredSize(new Dimension(160, 40));
        placeOrderBtn.setMaximumSize(new Dimension(200, 40));
        placeOrderBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        placeOrderBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                placeOrderBtn.setBackground(new Color(56, 142, 60));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                placeOrderBtn.setBackground(new Color(76, 175, 80));
            }
        });

        placeOrderBtn.addActionListener(e -> {
            String address = addressArea.getText().trim();
            String contact = contactField.getText().trim();

            if (address.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠️ Please fill in all fields", "Missing Info", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "🎉 Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        card.add(Box.createVerticalStrut(20));
        card.add(placeOrderBtn);

        bgPanel.add(card, new GridBagConstraints());
        add(bgPanel, BorderLayout.CENTER);
    }

    private JSeparator createSeparator() {
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(230, 230, 230));
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        return separator;
    }
}
