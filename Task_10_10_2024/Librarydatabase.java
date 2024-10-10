import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;


public class Librarydatabase {
    public static String url = "jdbc:mysql://localhost/Library";
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

    public void Bookbyauthor(String author_surname) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Books.book_name, Authors.author_surname, Authors.auhor_name , Authors.author_middlename FROM Books, Authors WHERE Books.id_author = Authors.id_author AND Authors.author_surname = \"Толстой\";";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("1.Список книг автора: " + author_surname);
            while (resultSet.next()) {
                String book_name = resultSet.getString("book_name");
                author_surname = resultSet.getString("author_surname");
                String author_name = resultSet.getString("auhor_name");
                String author_middlename = resultSet.getString("author_middlename");

                System.out.println("\nАвтор: " + author_surname + " " + author_name + " " + author_middlename + " " + "Книга: " + book_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void Datebook(String book_name) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Books.book_name, Visitors.visitor_name , Visitors.visitor_surname , Visitors.visitor_middlename AS visit_info , Time.book_taken AS taken, Time.book_returned AS return_date FROM Time JOIN Books ON Time.id_book = Books.id_book JOIN Visitors ON Time.id_visitor = Visitors.id_visitor WHERE Books.book_name = '\"Война и мир\"'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n2.История выдачи книги:  " + book_name);
            while (resultSet.next()) {
                book_name = resultSet.getString("book_name");
                String visitor_surname = resultSet.getString("visitor_surname");
                String visitor_name = resultSet.getString("visitor_name");
                String visitor_middlename = resultSet.getString("visit_info");
                String date_taken = resultSet.getString("taken");
                String date_return = resultSet.getString("return_date");

                System.out.println("Посетитель: " + " " + visitor_surname + " " + visitor_name + " " + visitor_middlename + " " + "Взял(а) книгу: " + book_name + " " + date_taken + " " + " И вернул(а) книгу: " + date_return);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void Popular(String book_name) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Books.book_name, COUNT(Time.id_time) AS taken FROM Time JOIN Books ON Time.id_book = Books.id_book GROUP BY Books.book_name ORDER BY taken DESC;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n 3.Популярность книг: ");
            while (resultSet.next()) {
                book_name = resultSet.getString("book_name");
                String book_count = resultSet.getString("taken");

                System.out.println(book_name + " " + ":" + " " + "Книга взята: " + book_count + " " + "раз(а)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void AllVisitors() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Visitors.id_visitor, Visitors.visitor_surname , Visitors.visitor_name , Visitors.visitor_middlename FROM Visitors;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n4.Список поситителей библиотеки: ");
            while (resultSet.next()) {

                String visitor_name = resultSet.getString("visitor_name");
                String visitor_surname = resultSet.getString("visitor_surname");
                String visitor_middlename = resultSet.getString("visitor_middlename");

                System.out.println("\n Фамилия: " + " " + visitor_surname + " " + "Имя: " + " " + visitor_name + " " + "Отчество: " + " " + visitor_middlename);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void Allbooks() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Books.book_name, Authors.auhor_name, Authors.author_surname, Authors.author_middlename FROM Books JOIN Authors ON Books.id_author = Authors.id_author;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n5.Список книг в библиотеке: ");
            while (resultSet.next()) {
                String book_name = resultSet.getString("book_name");
                String visitor_name = resultSet.getString("auhor_name");
                String visitor_surname = resultSet.getString("author_surname");
                String visitor_middlename = resultSet.getString("author_middlename");

                System.out.println("\n" + book_name + " " + "Автор:  " + " " + visitor_surname + " " + visitor_name + " " + visitor_middlename);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }
    }

    public void Booksthatnottaken() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Books.book_name FROM Books WHERE NOT EXISTS( SELECT 1 FROM Time WHERE Books.id_book = Time.id_book);";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\nСписок книг которые никто не взял: ");
            while (resultSet.next()) {
                String book_name = resultSet.getString("book_name");


                System.out.println("\n" + book_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void PopularAuthors() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT a.author_surname, a.auhor_name, a.author_middlename, COUNT(b.id_book) AS book_count FROM Authors a JOIN Books b ON a.id_author = b.id_author JOIN Time t ON b.id_book = t.id_book GROUP BY a.id_author, a.author_surname , a.auhor_name , a.author_middlename ORDER BY book_count DESC;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n7.Популярные авторы ");
            while (resultSet.next()) {
                String author_surname = resultSet.getString("author_surname");
                String author_name = resultSet.getString("auhor_name");
                String author_middlename = resultSet.getString("author_middlename");
                String count = resultSet.getString("book_count");

                System.out.println("\n" + author_surname + " " + author_name + " " + author_middlename + " " + "Книг взято : " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void findAuthorBySurname() {
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);
        try {
            connection = openConnection();
            String query = "SELECT Authors.author_surname, Authors.auhor_name, Authors.author_middlename, Books.book_name FROM Authors JOIN Books ON Authors.id_author = Books.id_author WHERE Authors.author_surname = ?;";
            PreparedStatement statement = connection.prepareStatement(query);
            System.out.print("\n 8.Введите фамилию автора: ");
            String inputSurname = scanner.nextLine();
            statement.setString(1, inputSurname);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Результаты поиска:");
            boolean authorFound = false;
            while (resultSet.next()) {
                String authorSurname = resultSet.getString("author_surname");
                String authorName = resultSet.getString("auhor_name");
                String authorMiddlename = resultSet.getString("author_middlename");
                String bookName = resultSet.getString("book_name");
                System.out.println(authorSurname + " " + authorName + " " + authorMiddlename +  " " + " Книги от этого автора: " + bookName);
                authorFound = true;
            }
            if (!authorFound) {
                System.out.println("Автор с такой фамилией не найден.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
            scanner.close();
        }
    }

    public void BooksbyAlphabet() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Authors.author_surname, Authors.auhor_name, Authors.author_middlename, Books.book_name FROM Books JOIN Authors ON Books.id_author= Authors.id_author ORDER BY Books.book_name ASC;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n9.Список книг отсортированный в алфавитном порядке ");
            while (resultSet.next()) {
                String author_surname = resultSet.getString("author_surname");
                String author_name = resultSet.getString("auhor_name");
                String author_middlename = resultSet.getString("author_middlename");
                String count = resultSet.getString("book_name");

                System.out.println("\n" + count + " " + author_surname + " " + author_name + " " + author_middlename );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void Daysborrow() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT b.book_name, CONCAT(v.visitor_surname, ' ', v.visitor_name, ' ', v.visitor_middlename) AS full_name, DATEDIFF(t.book_returned, t.book_taken) AS days_taken FROM Books b JOIN Time t ON b.Id_book = t.id_book JOIN Visitors v ON t.id_visitor = v.id_visitor;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n10.Колличество дней которые книга была у посетителя");
            while (resultSet.next()) {
                String book_name = resultSet.getString("book_name");
                String name = resultSet.getString("full_name");
                String days = resultSet.getString("days_taken");

                System.out.println("\nКнига :" + book_name + " " + "была у была у посетителя " + " " + name + " " + days + " дней/дня " );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }
}




