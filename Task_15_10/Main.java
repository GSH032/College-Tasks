import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Database bd = new Database();
   static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {


        while (true) {
            System.out.println("Выберите опуию которая подходит вам ");
            System.out.println("\n1.Добавить клиента");
            System.out.println("\n2.Добавить кибератаку");
            System.out.println("\n3.Добавить устройство");
            System.out.println("\n4.Добавить лог");
            System.out.println("\n5.Добавить рекомендацию");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    adduser();
                    break;
                case 2 :
                    addHack();
                    break;
                case 3:
                    addDevice();
                    break;
                case 4 :
                    addLog();
                    break;
                case  5:
                    addRecomend();
                    break;

            }
        }




    }
    static  void adduser() {
        System.out.print("Введите фамилию: ");
        String surname = sc.nextLine();
        System.out.print("Введите имя: ");
        String name = sc.nextLine();
        System.out.print("Введение отчество: ");
        String middlename = sc.nextLine();
        System.out.println("Введите имя пользователя: ");
        String username = sc.nextLine();
        bd.addUser(surname, name, middlename, username);

    }
    static  void addHack() {
        System.out.println("Введите дату кибератаки в формате (ГГГГ-ММ-ДД): ");
        String hack_date = sc.nextLine();
        System.out.println("Опишите кибератаку: ");
        String hack_desription = sc.nextLine();
        System.out.println("Введите название устройства ");
        String device_name = sc.nextLine();
        bd.addHack(hack_date, hack_desription , device_name);

    }
    static  void  addDevice() {
        System.out.println("Введите имя устройства");
        String device_name = sc.nextLine();
        System.out.println("Введите производитля устройства");
        String device_mark = sc.nextLine();
        bd.addDevice(device_name,device_mark);

    }
    static  void addLog() {
        System.out.println("Введите само действие");
        String log_desription = sc.nextLine();
        bd.addlog(log_desription);

    }
    static void  addRecomend() {
        System.out.println("Введите рекомендацию: ");
        String log_desription = sc.nextLine();
        bd.addRecomend(log_desription);
    }
}