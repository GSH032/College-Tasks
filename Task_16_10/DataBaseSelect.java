import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseSelect {
    public static String url = "jdbc:mysql://localhost/Authorise";
    public static String user = "root";
    public static String password = "";

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}