
import java.util.*;

class contact {
    long mobile;
    String name, email;

    contact(long m, String n, String e) {
        mobile = m;
        name = n;
        email = e;
    }
}

public class main {
    static Scanner sc = new Scanner(System.in);
    static contact a[] = new contact[40];
    static contact Del = new contact(-1, "", "");
    static int count = 0;

    static int hash(long m) {
        return (int)(m % 40);
    }

    static void insert() {
        System.out.print("Name: ");
        String n = sc.next();
        System.out.print("Email: ");
        String e = sc.next();
        System.out.print("Mobile: ");
        long m = sc.nextLong();

        if (count == 40) {
            System.out.println("Hash is full");
            return;
        }

        int i = hash(m);

        for (int j = 0; j < 40; j++) {
            if (a[i] == null || a[i] == Del) {
                a[i] = new contact(m, n, e);
                count++;
                System.out.println("Inserted at: " + i);
                return;
            }
            i = (i + 1) % 40;
        }
    }

    static void display() {
        for (int i = 0; i < 40; i++) {
            if (a[i] != null && a[i] != Del) {
                System.out.println(i + " | " + a[i].name
                    + " | " + a[i].email + " | " + a[i].mobile);
            }
        }
    }

    static void search() {
        System.out.print("Enter mobile to search: ");
        long m = sc.nextLong();
        int i = hash(m);

        for (int j = 0; j < 40; j++) {
            if (a[i] == null) break;

            if (a[i] != Del && a[i].mobile == m) {
                System.out.println("Found!");
                System.out.println(a[i].name + " | "
                    + a[i].email + " | " + a[i].mobile);
                return;
            }
            i = (i + 1) % 40;
        }

        System.out.println("Not found");
    }

    static void delete() {
        System.out.print("Enter mobile to delete: ");
        long m = sc.nextLong();
        int i = hash(m);

        for (int j = 0; j < 40; j++) {
            if (a[i] == null) break;

            if (a[i] != Del && a[i].mobile == m) {
                a[i] = Del;
                count--;
                System.out.println("Deleted successfully!");
                return;
            }
            i = (i + 1) % 40;
        }

        System.out.println("Not found");
    }

    public static void main(String[] args) {
        int ch;
        do {
            System.out.println(
                "\n1.Insert\n2.Display\n3.Search\n4.Delete\n5.Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1: insert(); break;
                case 2: display(); break;
                case 3: search(); break;
                case 4: delete(); break;
                case 5: System.out.println("Exiting!"); break;
                default: System.out.println("Invalid choice");
            }
        } while (ch != 5);
    }
}
