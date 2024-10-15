import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Database {
    
    DatabaseCon db =new DatabaseCon();
    Scanner scanner = new Scanner(System.in);

    public void addUser(String user_name, String user_surname, String user_middlename, String username) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "INSERT INTO Users (Users.user_name , Users.user_surname , Users.user_middlename, Users.username) VALUES (?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user_surname);
            statement.setString(2, user_name);
            statement.setString(3, user_middlename);
            statement.setString(4,username);
            statement.executeUpdate();
            System.out.println("Пользователь добавлен!");
            addlog("Пользователь добавлен!" + username);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Призошла ошибка! Клиент не добавлен!");
        } finally {
            db.closeConnection(connection);
        }

    }
    public void addHack(String hack_date, String hack_description, String device_name){
        Connection connection = null;
        try {
            connection = db.openConnection();
            String queryy = "SELECT Devices.device_id FROM Devices WHERE Devices.device_name = ?";
            PreparedStatement statement1 = connection.prepareStatement(queryy);
            statement1.setString(1 , device_name);
            ResultSet resultSet = statement1.executeQuery();
            if (resultSet.next()) {
                int  device_id = resultSet.getInt("device_id");


                String query = "INSERT INTO Hack(Hack.hack_date,Hack.hack_description,\tHack.device_id)VALUES (?,?,?)";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, hack_date);
                statement.setString(2, hack_description);
                statement.setInt(3, device_id);
                statement.executeUpdate();
                System.out.println("Кибератака добавлена!");
                addlog("Кибератака зафиксирована" + hack_description);
                ShowRecomend();

            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Кибератака не добавлена!");
        } finally {
            db.closeConnection(connection);
        }


    }
    public void addDevice(String device_name , String device_mark) {
        Connection connection = null;
        try {
            connection = db.openConnection();

            String query = "INSERT INTO Devices(Devices.device_name, Devices.device_mark) VALUES (?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, device_name);
            statement.setString(2,device_mark);
            statement.executeUpdate();
            System.out.println("Устройство " + " " + device_name + " " + "добавлено!" );
            addlog("Устройство добавлено!" + device_name );
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Устройство не добавлено!");
        } finally {
            db.closeConnection(connection);
        }
    }
    public void addlog(String log_desription) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String currdate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String query = "INSERT INTO Logs (Logs.log_date, Logs.log_desription) VALUES (?,?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, currdate);
            statement.setString(2,log_desription);
statement.executeUpdate();
            System.out.println("Лог обавлен!" );
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Лог не добавлен");
        } finally {
            db.closeConnection(connection);
        }

    }
    public void addRecomend( String reccomend_description) {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "INSERT INTO Reccomend (Reccomend.reccomend_description) VALUES (?);";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, reccomend_description);
            statement.executeUpdate();
            System.out.println("Рекомендация добавлена!" );
            addlog("Рекомендация добавлена! " + reccomend_description);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Рекодмендация не добавлена");
        } finally {
            db.closeConnection(connection);
        }
    }
    public  void ShowRecomend() {
        Connection connection = null;
        try {
            connection = db.openConnection();
            String query = "SELECT Reccomend.reccomend_description FROM Reccomend ORDER BY RAND() LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String reccomend = resultSet.getString("reccomend_description");
                System.out.println("Важно! Рекомендация во избежание повторения кибератак!" + reccomend);
            }
            else {
                System.out.println("Рекомендаций нет!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка! Рекодмендация не добавлена");
        } finally {
            db.closeConnection(connection);
        }
    }
}
