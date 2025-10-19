package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccounts {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:/Users/USER/Desktop/LibManagementSystem.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                System.out.println("Connected to SQLite database.");

                createAccTable(conn);
            }
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    public static void createAccTable(Connection conn) {
        String sql = """
            CREATE TABLE IF NOT EXISTS Accounts (
                name TEXT NOT NULL,
                password TEXT NOT NULL,
                email TEXT NOT NULL,
                isAdmin BOOLEAN,
                isBorrower BOOLEAN
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Account table created.");
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    public static void addAccount(Connection conn, String name, String password, String email, boolean isAdmin, boolean isBorrower) {
        String sql = "INSERT INTO Accounts(name, password, email, isAdmin, isBorrower) VALUES (?, ?, ?, ?, ?)";

        try (java.sql.PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
            pstmt.setBoolean(4, isAdmin);
            pstmt.setBoolean(5, isBorrower);

            pstmt.executeUpdate();
            System.out.println("Account added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding account: " + e.getMessage());
        }
    }

    public static boolean emailExists(Connection conn, String email) {
        String sql = "SELECT COUNT(*) FROM Accounts WHERE email = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error checking email: " + e.getMessage());
        }
        return false;
    }

    public static boolean passwordMatches(Connection conn, String email, String password) {
        String sql = "SELECT password FROM Accounts WHERE email = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password);
            } else {
                System.out.println("Email not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error checking password: " + e.getMessage());
        }

        return false;
    }
}
