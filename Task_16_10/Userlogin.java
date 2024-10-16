import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Userlogin {
    DataBaseSelect db = new DataBaseSelect();

    public String loginUser(String username, String password) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT role FROM Users WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("role");
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            db.closeConnection(connection);
        }
    }
}
