import java.sql.*;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> env = EnvLoader.load("config/.env");

        String url = env.get("DB_URL");
        String user = env.get("DB_USER");
        String password = env.get("DB_PASSWORD");

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement ps =
                     con.prepareStatement("SELECT * FROM users");
             ResultSet rs = ps.executeQuery()) {

            System.out.println("✅ Connected to MySQL");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}