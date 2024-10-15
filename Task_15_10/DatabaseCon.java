import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCon {
    public static String url = "jdbc:mysql://localhost/Security";
    public static String user = "root";
    public static String password = "";

    public java.sql.Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void closeConnection(java.sql.Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

