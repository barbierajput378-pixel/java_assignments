import java.util.*;

class Book {
    String name, author;
    int isbn;

    Book(String name, String author, int isbn) {
        this.name = name;
        this.author = author;
        this.isbn = isbn;
    }
}

public class Main {
    static Book b[] = new Book[50];
    static int n = 0;
    static Scanner sc = new Scanner(System.in);

    static void add() {
        if (n == 50)
            throw new IllegalArgumentException("Library is full");

        System.out.print("Enter book name: ");
        String name = sc.nextLine();

        System.out.print("Enter author: ");
        String author = sc.nextLine();

        System.out.print("Enter ISBN: ");
        int isbn = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < n; i++)
            if (b[i].isbn == isbn)
                throw new IllegalArgumentException("ISBN already exists");

        b[n++] = new Book(name, author, isbn);
        System.out.println("Book added");
    }

    static void display() {
        if (n == 0) {
            System.out.println("Library is empty");
            return;
        }

        for (int i = 0; i < n; i++)
            System.out.println(b[i].isbn + " " + b[i].name
                    + " " + b[i].author);
    }

    static void sort() {
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (b[j].isbn > b[j + 1].isbn) {
                    Book temp = b[j];
                    b[j] = b[j + 1];
                    b[j + 1] = temp;
                }

        System.out.println("Books sorted");
        display();
    }

    static void searchISBN() {
        sort();

        System.out.print("Enter ISBN to search: ");
        int key = Integer.parseInt(sc.nextLine());

        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (b[mid].isbn == key) {
                System.out.println("Book found: "
                        + b[mid].name + " " + b[mid].author);
                return;
            }

            if (b[mid].isbn < key)
                low = mid + 1;
            else
                high = mid - 1;
        }

        System.out.println("Book not found");
    }

    static void searchAuthor() {
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        boolean found = false;

        for (int i = 0; i < n; i++) {
            if (b[i].author.equalsIgnoreCase(author)) {
                System.out.println(b[i].isbn + " "
                        + b[i].name + " " + b[i].author);
                found = true;
            }
        }

        if (!found)
            System.out.println("No books found");
    }

    public static void main(String[] args) {
        int choice = 0;

        do {
            try {
                System.out.println("\n1.Add  2.Display");
                System.out.println("3.Search ISBN  4.Search Author");
                System.out.println("5.Sort  6.Exit");
                System.out.print("Enter choice: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1: add(); break;
                    case 2: display(); break;
                    case 3: searchISBN(); break;
                    case 4: searchAuthor(); break;
                    case 5: sort(); break;
                    case 6: System.out.println("Exiting"); break;
                    default: System.out.println("Invalid choice");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input");
            }

        } while (choice != 6);

        sc.close();
    }
}