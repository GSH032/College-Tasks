
import java.util.Scanner;

public class Main {
    static DataBase db = new DataBase();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Добавить клиента");
            System.out.println("2. Добавить продукт");
            System.out.println("3. Добавить заказ");
            System.out.println("4. Добавить контракт");
            System.out.println("5. Добавить проблему поддержки");
            System.out.println("6. Найти клиента по фамилии");
            System.out.println("7. Найти продукт по названию");
            System.out.println("8. Найти заказ по фамилии клиента и названию продукта ");
            System.out.println("9. Показать все контракты");
            System.out.println("10. Показать все проблемы поддержки");
            System.out.println("11. Выход");
            System.out.print("Выберите опцию: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    addOrder();
                    break;
                case 4:
                    addContract();
                    break;
                case 5:
                    addSupportIssue();
                    break;
                case 6:
                    findClientBySurname();
                    break;
                case 7:
                    findProductByName();
                    break;
                case 8:
                    findOrderByClientAndProduct();
                    break;
                case 9:
                    db.displayContracts();
                    break;
                case 10:
                    db.displaySupport();
                    break;

                case 11:
                    System.out.println("Закрываю программу");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Ошибка! Такой опции нет в функционале");
            }
        }
    }

    static void addClient() {
        System.out.print("Введите фамилию: ");
        String surname = scanner.nextLine();
        System.out.print("Введите имя: ");
        String name = scanner.nextLine();
        System.out.print("Введение отчество: ");
        String middlename = scanner.nextLine();
        db.addClient(surname, name, middlename);
    }

    static void addProduct() {
        System.out.print("Введите название продукта: ");
        String productName = scanner.nextLine();
        db.addProduct(productName);
    }

    static void addOrder() {
        System.out.print("Введите фамилию клиента: ");
        String clientSurname = scanner.nextLine();
        System.out.print("Введите название продукта: ");
        String productName = scanner.nextLine();
        System.out.print("Введите цену заказа: ");
        int price = scanner.nextInt();
        db.addOrder(clientSurname, productName, price);
    }

    static void addContract() {
        System.out.print("Введите фамилию клиента: ");
        String clientSurname = scanner.nextLine();
        System.out.print("Введите дату начала (ГГГГ-ММ-ДД): ");
        String startDate = scanner.nextLine();
        System.out.print("Введите дату окончания (ГГГГ-ММ-ДД): ");
        String endDate = scanner.nextLine();
        db.addContract(clientSurname, startDate, endDate);
    }

    static void addSupportIssue() {
        System.out.print("Введите фамилию клиента: ");
        String clientSurname = scanner.nextLine();
        System.out.print("Введите описание проблемы: ");
        String description = scanner.nextLine();
        System.out.print("Введите статус: ");
        String status = scanner.nextLine();
        db.addSupportIssue(clientSurname, description, status);
    }

    static void findClientBySurname() {
        System.out.print("Введите фамилию: ");
        String surname = scanner.nextLine();
        db.findClientBySurname(surname);
    }

    static void findProductByName() {
        System.out.print("Введите название продукта: ");
        String productName = scanner.nextLine();
        db.findProductByName(productName);
    }

    static void findOrderByClientAndProduct() {
        System.out.print("Введите фамилию клиента: ");
        String clientSurname = scanner.nextLine();
        System.out.print("Введите название продукта: ");
        String productName = scanner.nextLine();
        db.findOrderByClientAndProduct(clientSurname, productName);
    }
}
