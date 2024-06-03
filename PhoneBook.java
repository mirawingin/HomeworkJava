import java.util.*;

public class PhoneBook {
    private HashMap<String, HashSet<String>> phoneBook;
    private Scanner scanner;

    public PhoneBook() {
        this.phoneBook = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void addContact(String name, String phone) {
        phoneBook.putIfAbsent(name, new HashSet<>());
        phoneBook.get(name).add(phone);
    }

    public void removeContact(String name) {
        if (phoneBook.containsKey(name)) {
            phoneBook.remove(name);
            System.out.println("Контакт \"" + name + "\" успешно удален.");
        } else {
            System.out.println("Контакт с именем \"" + name + "\" не найден.");
        }
    }

    public void displayAllContactsSortedByPhoneCount() {
        List<Map.Entry<String, HashSet<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());
        sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));

        System.out.println("Телефонная книга (отсортированная по числу телефонов):");
        for (Map.Entry<String, HashSet<String>> entry : sortedEntries) {
            System.out.println(entry.getKey() + ":");
            for (String phone : entry.getValue()) {
                System.out.println("\t- " + phone);
            }
        }
    }

    public void startMenu() {
        int choice;
        do {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Просмотреть все контакты");
            System.out.println("2. Добавить новый контакт");
            System.out.println("3. Удалить контакт");
            System.out.println("0. Выход");

            choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки после ввода числа

            switch (choice) {
                case 1:
                    displayAllContactsSortedByPhoneCount();
                    break;
                case 2:
                    System.out.println("Введите имя контакта:");
                    String name = scanner.nextLine();
                    System.out.println("Введите номер телефона:");
                    String phone = scanner.nextLine();
                    addContact(name, phone);
                    break;
                case 3:
                    System.out.println("Введите имя контакта для удаления:");
                    String contactName = scanner.nextLine();
                    removeContact(contactName);
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте еще раз.");
                    break;
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        // Добавление контактов в телефонную книгу
        phoneBook.addContact("Сидорова Анна Павловна", "+7 (812) 345-67-89");
        phoneBook.addContact("Петрова Мария Алексеевна", "+7 (495) 987-65-43");
        phoneBook.addContact("Петрова Мария Алексеевна", "+7 (495) 222-33-44");
        phoneBook.addContact("Иванов Петр Сергеевич", "+7 (999) 123-45-67");
        phoneBook.addContact("Иванов Петр Сергеевич", "+7 (999) 111-22-33");
        phoneBook.addContact("Иванов Петр Сергеевич", "+7 (999) 999-99-99");
        phoneBook.addContact("Иванов Петр Павлович", "+7 (495) 555-77-88");
        phoneBook.addContact("Николаев Алексей Дмитриевич", "+7 (800) 555-35-35");
        phoneBook.addContact("Николаев Алексей Дмитриевич", "+7 (812) 777-88-99");
        phoneBook.addContact("Николаев Алексей Дмитриевич", "+7 (499) 333-22-11");

        phoneBook.startMenu();
    }
}