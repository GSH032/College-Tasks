import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;



public class Database {
    public static String url = "jdbc:mysql://localhost/Shop";
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


    public void selecOnegenre(String genre_name) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Books.title FROM Books, Genre" + " WHERE Books.id_genre =Genre.id_genre AND Genre.genre_name ='" + genre_name + "'";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\nСписок книг жанра: " + genre_name);
            while (resultSet.next()) {
                String title = resultSet.getNString("title");
                System.out.println(title);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void Maxbooksauthor() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Author.author_surname , Author.author_name, COUNT(Books.id_book) AS books_count FROM Author JOIN Books ON Author.id_author = Books.id_author GROUP BY Author.author_surname , Author.author_name ORDER BY books_count DESC LIMIT 1;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String author_surname = resultSet.getString("author_surname");
                int books_count = resultSet.getInt("books_count");
                System.out.println( author_surname + " : " + books_count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void BookOrders(String customer_surname) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT o.id_order, b.title, b.year, c.customer_surname, c.customer_name, c.customer_middlename FROM Orders o JOIN Customers c ON o.id_customer = c.id_customer JOIN Books b ON o.id_book = b.id_book WHERE c.id_customer = 2;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("Заказы читателя с фамилией: " + customer_surname + ":");
            while (resultSet.next()) {
                customer_surname = resultSet.getString("c.customer_surname");
                String book_name = resultSet.getString("b.title");
                String book_year = resultSet.getString("b.year");
                System.out.println(customer_surname + ":" + book_name + " " + book_year);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }

    }

    public void Bookcount(String genre_name) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Genre.genre_name as genre_name, COUNT(Books.id_book) as book_count FROM Books, Genre WHERE Books.id_genre = Genre.id_genre GROUP BY Genre.genre_name ORDER BY Genre.genre_name DESC;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\nКниг в жанрах: " + genre_name );
            while (resultSet.next()) {
                String book_count = resultSet.getString("book_count");
                String book_name = resultSet.getString("genre_name");
                System.out.println(book_name + ":" + book_count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }
    }

    public void BooksAfter200(String book2000) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT * FROM Books WHERE year > 2000;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\nКниги изданые после 2000" + book2000 );
            while (resultSet.next()) {
                 book2000 = resultSet.getString("Books.year");
                 String bookname = resultSet.getString("Books.title");

                System.out.println(bookname +": " +book2000);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }
    }





public void Morethan5(String customer_name, String customer_surname , String customer_middlename) {
    Connection connection = null;
    try {
        connection = openConnection();
        String query = "SELECT Customers.customer_surname , Customers.customer_name , Customers.customer_middlename FROM Customers WHERE Customers.purcase_count > 5";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        System.out.println("\nСписок покупателей купивших больше 5 книг ");
        while (resultSet.next()) {
            customer_name = resultSet.getString("Customers.customer_name");
            customer_surname = resultSet.getString("Customers.customer_surname");
            customer_middlename = resultSet.getString("Customers.customer_middlename");



            System.out.println( customer_surname + " " +  customer_name  + " " + customer_middlename);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        Closeconnection(connection);
    }
}

    public void Morethan1(String author_name, String author_surname  , String author_middlename ) {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT a.author_surname, a.author_name, a.author_middlename, COUNT(b.id_book) AS BookCount FROM Author a JOIN Books b ON a.id_author = b.id_author GROUP BY a.id_author, a.author_surname, a.author_name, a.author_middlename HAVING COUNT(b.id_book) > 1 ORDER BY BookCount DESC;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\nАвторы имеющие более одной книги:");
            while (resultSet.next()) {
                author_name = resultSet.getString("author_name");
              author_surname  = resultSet.getString("author_surname");
                author_middlename = resultSet.getString("author_middlename");
                String book_count = resultSet.getString("BookCount");


                System.out.println(author_surname+ " " + author_name + " " + author_middlename + " " + " В колличестве: "+ " " + book_count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }
    }

    public void Zeroorders() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT Books.title, Books.id_book, Orders.id_customer, Orders.id_book FROM Books LEFT JOIN Orders ON Books.id_book = Orders.id_book WHERE Orders.id_customer is null;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\nКниги которые не покупали");
            while (resultSet.next()) {
               String book_name = resultSet.getString("Books.title");
               String id_customer  = resultSet.getString("Orders.id_customer");



                System.out.println(book_name + " " + id_customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }
    }

    public void Sum() {
        Connection connection = null;
        try {
            connection = openConnection();
            String query = "SELECT SUM(Orders.sum) FROM Orders;";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\nСумма всех заказов составляет:");
            while (resultSet.next()) {
                String book_name = resultSet.getString("SUM(Orders.sum)");




                System.out.println(book_name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Closeconnection(connection);
        }
    }

}






//SELECT Books.book_name, Orders.sum FROM Customers, Orders, Books WHERE Customers.id_customer = Orders.id_customer AND Books.id_book = Orders.id_book AND Customers.customer_surname =" + "'" + custom + "'"
//SELECT SELECT Genre.genre_name as genre_name, COUNT(Books.id_book) as book_count FROM Books, Genre WHERE Books.id_genre = Genre.id_genre GROUP BY Genre.genre_name ORDER BY Genre.genre_name DESC;
//SELECT Books.title , COUNT(Orders.id_book) as countBook FROM Books, Orders WHERE Books.id_book = Orders.id_book GROUP BY Books.title ORDER BY countBook DESC LIMIT 1;
//SELECT Author.author_surname , COUNT(Books.id_book) as countBook FROM Author, Books WHERE Author.id_author = Books.id_author GROUP BY Author.author_surname HAVING countBook > 1;
//SELECT Books.title, COUNT(Orders.id_book) as book FROM Books, Orders WHERE Books.id_book = Orders.id_book GROUP BY Books.title HAVING book = 0;
//SELECT Books.title, Books.id_book, Orders.id_customer, Orders.id_book FROM Books LEFT JOIN Orders ON Books.id_book = Orders.id_book WHERE Orders.id_customer is null;
//SELECT SUM(Orders.sum) FROM Orders; */