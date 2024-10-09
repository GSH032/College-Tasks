import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;



public class Projects {
  public static String url = "jdbc:mysql://localhost/Projectmanager";
  public static String user = "root";
  public static String password = "";

  public Connection openConnection() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }

  public void Closeconnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  public  void allTasks() {
    Connection connection = null;
    try {
      connection = openConnection();
      String query = "SELECT w.worker_surname, w.worker_name, w.worker_middlename , t.task_name AS task_name FROM Workers AS w JOIN Tasks t ON w.id_worker = t.id_worker WHERE w.worker_surname = \"Иванов\";";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet resultSet = statement.executeQuery();
      System.out.println("Задачи: ");
      while (resultSet.next()) {
        String worker_surname = resultSet.getString("w.worker_surname");
        String worker_name = resultSet.getString("w.worker_name");
        String worker_middlename = resultSet.getString("w.worker_middlename");
        String task_name = resultSet.getString("t.task_name");
        System.out.println(worker_surname+ " " + worker_name + " "+ worker_middlename + ": " + task_name);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Closeconnection(connection);
    }

  }

  public  void alottasks() {
    Connection connection = null;
    try {
      connection = openConnection();
      String query = "SELECT Projects.project_name, COUNT(*) AS task_count FROM Projects JOIN Tasks ON Projects.id_project = Tasks.id_project GROUP BY Projects.id_project ORDER BY task_count DESC LIMIT 1;";
      PreparedStatement statement = connection.prepareStatement(query);
      ResultSet resultSet = statement.executeQuery();
      System.out.println("\n Больше всего задач: ");
      while (resultSet.next()) {
        String project_name = resultSet.getString("Projects.project_name");
        String taskcount = resultSet.getString("task_count");

        System.out.println(project_name + " " + ":"+ " " + taskcount);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Closeconnection(connection);
    }

  }

}
