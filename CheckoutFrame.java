import java.awt.*;
import javax.swing.*;

public class CheckoutFrame extends JFrame {
    public CheckoutFrame() {
        setTitle("Checkout");
        setSize(400, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // So it doesn't exit whole app
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel addressLabel = new JLabel("Shipping Address:");
        JTextArea addressArea = new JTextArea(3, 20);
        JLabel contactLabel = new JLabel("Contact Number:");
        JTextField contactField = new JTextField();

        JButton placeOrderBtn = new JButton("Place Order");

        placeOrderBtn.addActionListener(e -> {
            String address = addressArea.getText().trim();
            String contact = contactField.getText().trim();

            if (address.isEmpty() || contact.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields");
            } else {
                JOptionPane.showMessageDialog(this, "✅ Order placed successfully!");
                dispose(); // close checkout window
            }
        });

        panel.add(addressLabel);
        panel.add(new JScrollPane(addressArea));
        panel.add(contactLabel);
        panel.add(contactField);
        panel.add(placeOrderBtn);

        add(panel, BorderLayout.CENTER);
    }
}
