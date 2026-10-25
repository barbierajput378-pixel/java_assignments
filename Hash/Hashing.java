import java.util.*;

class Contact {
    String name;
    String phone;

    Contact(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + " -> " + phone;
    }
}

class HashTable {
    private final int size;
    private final Contact[] table;

    HashTable(int size) {
        this.size = size;
        table = new Contact[size];
    }

    private int hash(String key) {
        int sum = 0;
        for (int i = 0; i < key.length(); i++) {
            sum += key.charAt(i);
        }
        return sum % size;
    }

    private boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z ]+") && !name.trim().isEmpty();
    }

    private boolean isValidPhone(String phone) {
        return phone != null && phone.matches("\\d{7,15}");
    }

    // Accept the main scanner as a parameter so we never close System.in
    public void insert(String name, String phone, Scanner sc) {
        if (!isValidName(name)) {
            System.out.println("Invalid name.");
            return;
        }
        int hashVal = hash(name.toLowerCase());
        int start = hashVal;

        while (table[hashVal] != null) {
            if (table[hashVal].name.equalsIgnoreCase(name)) {
                System.out.println("Name already exists...");
                return;
            }
            hashVal = (hashVal + 1) % size;
            if (hashVal == start) {
                System.out.println("Hash table is full..");
                return;
            }
        }

        // Validate phone number using the shared scanner safely
        while (!isValidPhone(phone)) {
            System.out.print("Invalid phone number. Enter again (7-15 digits): ");
            phone = sc.nextLine().trim();
        }

        table[hashVal] = new Contact(name, phone);
        System.out.println("Contact inserted successfully.");
    }

    public void search(String name) {
        if (!isValidName(name)) return;
        int hashVal = hash(name.toLowerCase());
        int start = hashVal;

        while (table[hashVal] != null) {
            if (table[hashVal].name.equalsIgnoreCase(name)) {
                System.out.println("Contact found: " + table[hashVal]);
                return;
            }
            hashVal = (hashVal + 1) % size;
            if (hashVal == start) break;
        }
        System.out.println("Contact not found: " + name);
    }

    public void delete(String name) {
        if (!isValidName(name)) return;
        int hashVal = hash(name.toLowerCase());
        int start = hashVal;

        while (table[hashVal] != null) {
            if (table[hashVal].name.equalsIgnoreCase(name)) {
                table[hashVal] = null; // Clear the slot
                System.out.println("Contact deleted successfully.");
                return;
            }
            hashVal = (hashVal + 1) % size;
            if (hashVal == start) break;
        }
        System.out.println("Contact not found: " + name);
    }

    public void display() {
        System.out.println("\n--- Contact List ---");
        Contact[] contacts = Arrays.stream(table)
                .filter(c -> c != null)
                .toArray(Contact[]::new);

        if (contacts.length == 0) {
            System.out.println("Contact list is empty.");
            return;
        }

        // Sort contacts alphabetically
        Arrays.sort(contacts, Comparator.comparing(c -> c.name.toLowerCase()));
        for (Contact c : contacts) {
            System.out.println(c);
        }
    }
}

public class Hashing {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int size = 0;

        // Get valid table size from user
        while (size <= 0) {
            System.out.print("Enter size of hash table (positive integer): ");
            try {
                size = Integer.parseInt(sc.nextLine());
                if (size <= 0) System.out.println("Size must be positive.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }

        HashTable contacts = new HashTable(size);

        while (true) {
            System.out.println("\n--- Contact List Menu ---");
            System.out.println("1. Insert Contact");
            System.out.println("2. Search Contact");
            System.out.println("3. Delete Contact");
            System.out.println("4. Display All Contacts");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine().trim();
                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine().trim();
                    contacts.insert(name, phone, sc); // Pass the main scanner here
                }
                case 2 -> {
                    System.out.print("Enter name to search: ");
                    contacts.search(sc.nextLine().trim());
                }
                case 3 -> {
                    System.out.print("Enter name to delete: ");
                    contacts.delete(sc.nextLine().trim());
                }
                case 4 -> contacts.display();
                case 5 -> {
                    System.out.println("Exiting program...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Select between 1 and 5.");
            }
        }
    }
}