import javax.swing.*;
import java.awt.*;

public class CheckoutPanel extends JPanel {
    public CheckoutPanel() {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Thank you for your purchase!", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label, BorderLayout.CENTER);

        Cart.clear();
    }
}
