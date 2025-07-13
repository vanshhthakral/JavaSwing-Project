

import java.sql.*;

import javax.swing.JOptionPane;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlite:db/ecommerce.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initialize() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                         "username TEXT UNIQUE NOT NULL, " +
                         "password TEXT NOT NULL)";
            stmt.execute(sql);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        } catch (SQLException ex) {
    if (ex.getMessage().contains("UNIQUE")) {
        JOptionPane.showMessageDialog(this, "Username already exists! Try another one.", "Error", JOptionPane.ERROR_MESSAGE);
    } else {
        ex.printStackTrace(); // For debugging other SQL issues
        JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    }
}
