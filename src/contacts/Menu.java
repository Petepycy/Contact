package contacts;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);
    private static PhoneBook phoneBook;

    public static void start(String[] args) {
        if (args.length != 0) {
            SerializationUtils.createFile(args[0]);
        }
        phoneBook = new PhoneBook();
        menuOptions();
    }

    private static void menuOptions() {
        boolean isWorking = true;
        while (isWorking) {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            String action = scanner.nextLine();
            switch (action) {
                case "add" -> phoneBook.addContact();
                case "list" -> {
                    phoneBook.printList();
                    listMenu();
                }
                case "search" -> searchMenu();
                case "count" -> printCount();
                case "exit" -> isWorking = false;
                default -> System.out.println("Incorrect option. Try again!");
            }
        }
    }

    private static void searchMenu() {
        boolean isWorking = true;
        List<Contact> foundList = phoneBook.search();

        while (isWorking) {
            System.out.print("[search] Enter action ([number], back, again): ");
            String action = scanner.next();
            switch (action) {
                case "again":
                    foundList = phoneBook.search();
                    break;
                case "back":
                    isWorking = false;
                    break;
                default:
                    try {
                        int num = Integer.parseInt(action);
                        if (num <= 1 || num > foundList.size()) throw new RuntimeException();
                        Contact contact = foundList.get(num - 1);
                        System.out.println(contact.info());
                        recordMenu(contact);
                        isWorking = false;
                    } catch (Exception e) {
                        System.out.println("Incorrect number. Try again!");
                    }
                    break;
            }
        }
    }

    private static void listMenu() {
        boolean isWorking = true;
        while (isWorking) {
            System.out.print("[list] Enter action ([number], back): ");
            String action = scanner.next();
            if ("back".equals(action)) {
                isWorking = false;
            } else {
                try {
                    int num = Integer.parseInt(action);
                    if (num <= 0 || num > phoneBook.count()) throw new RuntimeException();
                    Contact contact = phoneBook.getContact(num - 1);
                    System.out.println(contact.info());
                    recordMenu(contact);
                    isWorking = false;
                } catch (Exception e) {
                    System.out.println("Incorrect action. Try again!");
                }
            }
        }
    }

    private static void recordMenu(Contact contact) {
        boolean isWorking = true;
        while (isWorking) {
            System.out.print("[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine();
            switch (action) {
                case "edit" -> phoneBook.edit(contact);
                case "delete" -> phoneBook.remove(contact);
                case "menu" -> isWorking = false;
                default -> System.out.println("Incorrect action. Try again!");
            }
        }
    }

    private static void printCount() {
        System.out.printf("The Phone Book has %d records.%n", phoneBook.count());
    }
}