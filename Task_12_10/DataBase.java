import java.sql.*;

public class DataBase {
    private DataBaseSelect db = new DataBaseSelect();

    public void addClient(String surname, String name, String middlename) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "INSERT INTO Clients (client_surname, client_name, client_middlename) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, surname);
            statement.setString(2, name);
            statement.setString(3, middlename);
            statement.executeUpdate();
            System.out.println("Клиент добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Клиент не добавлен ");
        } finally {
            db.closeConnection(connection);
        }
    }

    public void addProduct(String productName) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "INSERT INTO Products (product_name) VALUES (?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productName);
            statement.executeUpdate();
            System.out.println("Продукт добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Продукт не добавлен");
        } finally {
            db.closeConnection(connection);
        }
    }
    public void addContract(int clientId, String startDate, String endDate) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "INSERT INTO Contracts (client_id, start_date, end_date) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, clientId);
            statement.setString(2, startDate);
            statement.setString(3, endDate);
            statement.executeUpdate();
            System.out.println("Контракт успешно добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось добавить контракт");
        } finally {
            db.closeConnection(connection);
        }
    }

    public void addSupportIssue(int clientId, String description, String status) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "INSERT INTO Support (id_client, issue_description, status) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, clientId);
            statement.setString(2, description);
            statement.setString(3, status);
            statement.executeUpdate();
            System.out.println("Проблема поддержки успешно добавлена");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось добавить проблему поддержки");
        } finally {
            db.closeConnection(connection);
        }
    }


    public void addOrder(int clientId, int productId, int price) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "INSERT INTO Orders (client_id, product_id, price) VALUES (?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, clientId);
            statement.setInt(2, productId);
            statement.setInt(3, price);
            statement.executeUpdate();
            System.out.println("Заказ добавлен!");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Заказ не добавлен");
        } finally {
            db.closeConnection(connection);
        }
    }

    public void findClientBySurname(String surname) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT * FROM Clients WHERE client_surname = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, surname);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int  id = resultSet.getInt("client_id");
                String name = resultSet.getString("client_name");
                surname = resultSet.getString("client_surname");
                String middlename = resultSet.getString("client_middlename");
                System.out.println("Id клиента" + id + "Фамилия: " + surname + "Имя: " + name + "Отчество: " + middlename );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Клиент не найден");
        } finally {
            db.closeConnection(connection);
        }
    }

    public void displayContracts() {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT * FROM Contracts";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int idcontr = resultSet.getInt("contract_id");
                int idclient = resultSet.getInt("client_id");
                String start_date = resultSet.getString("start_date");
                String end_date = resultSet.getString("end_date");
                System.out.println("ID контракта: " + idcontr + ", ID клиента: " + idclient + ", Дата начала: " + start_date + ", Дата окончания: " + end_date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось отобразить контракты");
        } finally {
            db.closeConnection(connection);
        }
    }

    public void findProductByName(String productName) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT * FROM Products WHERE product_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, productName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id =  resultSet.getInt("product_id");
                productName = resultSet.getString("product_name");

                System.out.println("id продукта " + id + "Название продукта: " + productName );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не вышло найти продукт! Ошибка!");
        } finally {
            db.closeConnection(connection);
        }
    }

    public void findOrderById(int orderId) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT * FROM Orders WHERE order_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderId = resultSet.getInt("order_id");
                int clientid = resultSet.getInt("client_id");
                int productId = resultSet.getInt("product_id");
                int price = resultSet.getInt("price");
                System.out.println("ID Заказа" + orderId + "ID Клиента" + clientid + "ID Продукта" + productId + "Цена: " + price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Не вышло найти заказ");
        } finally {
            db.closeConnection(connection);
        }
    }

    public void displaySupport() {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT * FROM Support";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int idsup = resultSet.getInt("issue_id");
                String description = resultSet.getString("issue_description");
                String status = resultSet.getString("status");
                System.out.println("Id ошибки: " + idsup + "Описание ошибки: " + description + "Статус ошибки:" + status );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed to display support information");
        } finally {
            db.closeConnection(connection);
        }
    }
}
