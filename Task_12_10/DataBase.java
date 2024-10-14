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
    public void addContract(String clientSurname, String startDate, String endDate) {
        Connection connection = null;
        try {
            connection = db.openConnection();

            // Получаем client_id правда мне кажется это делается по другому
            int clientId = getClientIdBySurname(connection, clientSurname);
            if (clientId == -1) {
                System.out.println("Клиент не найден");
                return;
            }

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

    public void addSupportIssue(String clientSurname, String description, String status) {
        Connection connection = null;
        try {
            connection = db.openConnection();

            // Сначала найдем ID клиента по фамилии
            String findClientQuery = "SELECT client_id FROM Clients WHERE client_surname = ?";
            PreparedStatement findClientStatement = connection.prepareStatement(findClientQuery);
            findClientStatement.setString(1, clientSurname);
            ResultSet clientResult = findClientStatement.executeQuery();

            if (clientResult.next()) {
                int clientId = clientResult.getInt("client_id");

                // Теперь добавим проблему в поддержку
                String addIssueQuery = "INSERT INTO Support (id_client, issue_description, status) VALUES (?, ?, ?);";
                PreparedStatement addIssueStatement = connection.prepareStatement(addIssueQuery);
                addIssueStatement.setInt(1, clientId);
                addIssueStatement.setString(2, description);
                addIssueStatement.setString(3, status);
                addIssueStatement.executeUpdate();

                System.out.println("Проблема поддержки успешно добавлена для клиента: " + clientSurname);
            } else {
                System.out.println("Клиент с фамилией " + clientSurname + " не найден");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не удалось добавить проблему поддержки");
        } finally {
            db.closeConnection(connection);
        }
    }


    public void addOrder(String clientSurname, String productName, int price) {
        Connection connection = null;
        try {
            connection = db.openConnection();


            String clientQuery = "SELECT client_id FROM Clients WHERE client_surname = ?";
            PreparedStatement clientStatement = connection.prepareStatement(clientQuery);
            clientStatement.setString(1, clientSurname);
            ResultSet clientResult = clientStatement.executeQuery();

            if (!clientResult.next()) {
                System.out.println("Клиент не найден");
                return;
            }
            int clientId = clientResult.getInt("client_id");


            String productQuery = "SELECT product_id FROM Products WHERE product_name = ?";
            PreparedStatement productStatement = connection.prepareStatement(productQuery);
            productStatement.setString(1, productName);
            ResultSet productResult = productStatement.executeQuery();

            if (!productResult.next()) {
                System.out.println("Продукт не найден");
                return;
            }
            int productId = productResult.getInt("product_id");


            String orderQuery = "INSERT INTO Orders (client_id, product_id, price) VALUES (?, ?, ?)";
            PreparedStatement orderStatement = connection.prepareStatement(orderQuery);
            orderStatement.setInt(1, clientId);
            orderStatement.setInt(2, productId);
            orderStatement.setInt(3, price);
            orderStatement.executeUpdate();

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
                String name = resultSet.getString("client_name");
                surname = resultSet.getString("client_surname");
                String middlename = resultSet.getString("client_middlename");
                System.out.println("Фамилия: " + " " + surname + " " + "Имя: "+ " " + name + " " + "Отчество: " + " " + middlename );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Клиент не найден");
        } finally {
            db.closeConnection(connection);
        }
    }
     int getClientIdBySurname(Connection connection, String surname) throws SQLException {
        String query = "SELECT client_id FROM Clients WHERE client_surname = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, surname);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("client_id");
        }
        return -1;
    }

    public void displayContracts() {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT Clients.client_surname, Contracts.contract_id,Contracts.start_date,Contracts.end_date FROM `Contracts`, `Clients`;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int idcontr = resultSet.getInt("contract_id");
                String idclient = resultSet.getString("client_surname");
                String start_date = resultSet.getString("start_date");
                String end_date = resultSet.getString("end_date");
                System.out.println("ID контракта: " + " " + idcontr + " " + ", Фамилия клиента: " + " " + idclient + " " + ", Дата начала: " + " " + start_date + " " + ", Дата окончания: " + end_date);
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

                System.out.println("id продукта " + " " + id + "" + "Название продукта: " + " " + productName );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не вышло найти продукт! Ошибка!");
        } finally {
            db.closeConnection(connection);
        }
    }

    public void findOrderByClientAndProduct(String clientSurname, String productName) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT o.order_id, c.client_surname, p.product_name, o.price " +
                    "FROM Orders o " +
                    "JOIN Clients c ON o.client_id = c.client_id " +
                    "JOIN Products p ON o.product_id = p.product_id " +
                    "WHERE c.client_surname = ? AND p.product_name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, clientSurname);
            statement.setString(2, productName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int orderId = resultSet.getInt("order_id");
                String surname = resultSet.getString("client_surname");
                String name = resultSet.getString("product_name");
                int price = resultSet.getInt("price");
                System.out.println("ID Заказа: " + " " + orderId + " " + ", Фамилия клиента: " + " " + surname + " " + ", Название продукта: " + name + ", Цена: " + price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Не удалось найти заказ");
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
                System.out.println("№ ошибки: " + " " + idsup + " " + "Описание ошибки: " + " " + description + " " + "Статус ошибки:" + " " + status );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Не вышло вывести информацию об ошибках");
        } finally {
            db.closeConnection(connection);
        }
    }
}
