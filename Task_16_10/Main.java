import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataBase db = new DataBase();
        Userlogin ul = new Userlogin();

        while (true) {
            System.out.println("1. Регистрация");
            System.out.println("2. Авторизация");
            System.out.println("3. Выход");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    registerUser(scanner, db);
                    break;
                case 2:
                    loginUser(scanner, ul, db);
                    break;
                case 3:
                    System.out.println("До свидания!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите 1, 2 или 3.");
            }
        }
    }

     static void registerUser(Scanner scanner, DataBase db) {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        db.registerUser(username, password);
    }

    static void loginUser(Scanner scanner, Userlogin ul, DataBase db) {
        System.out.print("Введите имя пользователя: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        String role = ul.loginUser(username, password);
        if (role != null) {
            System.out.println("Авторизация успешна! Добро пожаловать, " + username + "!");
            if (role.equals("admin")) {
                adminMenu(scanner, db);
            } else {
                userMenu(scanner, db);
            }
        } else {
            System.out.println("Неверное имя пользователя или пароль.");
            System.out.print("Хотите зарегистрироваться? (да/нет): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("да")) {
                registerUser(scanner, db);
            }
        }
    }

     static void adminMenu(Scanner scanner, DataBase db) {
        while (true) {
            System.out.println("\nМеню администратора:");
            System.out.println("1. Просмотреть данные");
            System.out.println("2. Добавить данные");
            System.out.println("3. Удалить данные");
            System.out.println("4. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    db.viewData();
                    break;
                case 2:
                    db.addData(scanner);
                    break;
                case 3:
                    db.deleteData(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите от 1 до 4.");
            }
        }
    }

    static void userMenu(Scanner scanner, DataBase db) {
        while (true) {
            System.out.println("\nМеню пользователя:");
            System.out.println("1. Просмотреть данные");
            System.out.println("2. Выйти");
            System.out.print("Выберите действие: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    db.viewData();
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, выберите 1 или 2.");
            }
        }
    }
}