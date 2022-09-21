package contacts;

import java.time.LocalDate;
import java.util.Scanner;

public class ContactFactory {

    private static final Scanner scanner = new Scanner(System.in);

    public Contact createContact(String type) {

        Contact contact = null;
        switch (type) {
            case "person" -> contact = createPerson();
            case "organization" -> contact = createOrganization();
            default -> System.out.println("Incorrect type. Try again");
        }
        return contact;
    }

    public Person createPerson() {

        System.out.print("Enter the name: ");
        String name = scanner.next();
        System.out.print("Enter the surname: ");
        String surname = scanner.next();

        System.out.print("Enter the birth date: ");
        String bd = scanner.nextLine();
        LocalDate birthDate = Person.isCorrectBirthDate(bd);

        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        if (!Person.isCorrectGender(gender)) {
            gender = null;
        }

        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        if (!Contact.isCorrectNumber(number)) {
            number = null;
        }
        return new Person(number, name, surname, birthDate, gender);
    }

    public Organization createOrganization() {

        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine();
        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: ");
        String number = scanner.nextLine();
        if (!Contact.isCorrectNumber(number)) {
            number = null;
        }
        return new Organization(number, name, address);
    }
}