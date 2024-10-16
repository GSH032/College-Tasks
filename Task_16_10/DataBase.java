import java.sql.*;
import java.util.Scanner;

public class DataBase {
    DataBaseSelect db = new DataBaseSelect();

    public void registerUser(String username, String password) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "INSERT INTO Users (username, password) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.executeUpdate();
            System.out.println("Пользователь успешно зарегистрирован.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(connection);
        }
    }

    public void viewData() {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT * FROM Users";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("Данные пользователей:");
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                System.out.println("ID: " + userId + ", Имя пользователя: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(connection);
        }
    }

    public void addData(Scanner scanner) {
        System.out.print("Введите имя нового пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль нового пользователя: ");
        String password = scanner.nextLine();

        registerUser(username, password);
    }

    public void deleteData(Scanner scanner) {
        System.out.print("Введите ID пользователя для удаления: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "DELETE FROM Users WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Пользователь успешно удален.");
            } else {
                System.out.println("Пользователь с указанным ID не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.closeConnection(connection);
        }
    }
}
