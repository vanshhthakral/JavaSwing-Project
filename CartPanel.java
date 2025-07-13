import java.awt.*;
import javax.swing.*;

public class CartPanel extends JPanel {
    public CartPanel() {
        setLayout(new BorderLayout());

        JTextArea area = new JTextArea(10, 30);
        area.setEditable(false);

        StringBuilder sb = new StringBuilder("Cart Items:\n\n");
        for (Product p : Cart.getItems()) {
            sb.append(p).append("\n");
        }
        sb.append("\nTotal: ₹").append(Cart.getTotal());

        area.setText(sb.toString());

        add(new JScrollPane(area), BorderLayout.CENTER);
    }
}
