import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KoneksiDatabase {

    private static final String DB_NAME = "tetris_db";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;

    private static Connection connection;

    public static void initialize() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Database connected!");

            String sql1 =
                "CREATE TABLE IF NOT EXISTS player (" +
                "player_id INT AUTO_INCREMENT PRIMARY KEY, " +
                "nickname VARCHAR(50) NOT NULL, " +
                "score INT NOT NULL, " +
                "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
            connection.prepareStatement(sql1).execute();

        } catch (Exception e) {
            System.err.println("Database failed: " + e.getMessage());
        }
    }

    public static boolean saveScore(String nickname, int score) {
        String sql = "INSERT INTO scores(nickname, score) VALUES(?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nickname);
            stmt.setInt(2, score);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static List<Object[]> getTopTen() {
        List<Object[]> list = new ArrayList<>();
        String sql = "SELECT nickname, score, created_at FROM scores ORDER BY score DESC LIMIT 10";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Object[]{
                    rs.getString("nickname"),
                    rs.getInt("score"),
                    rs.getTimestamp("created_at")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
