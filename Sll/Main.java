import java.util.*;

class Member {
    int id;
    String name, address, position;
    Member next;

    Member(int id, String name, String address, String position) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.position = position;
    }
}

public class Main {
    static Member head = null;
    static Scanner sc = new Scanner(System.in);

    static void create() {
        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter position: ");
        String position = sc.nextLine();

        Member n = new Member(id, name, address, position);

        if (head == null)
            head = n;
        else {
            Member t = head;
            while (t.next != null)
                t = t.next;
            t.next = n;
        }

        System.out.println("Member added");
    }

    static void display() {
        Member t = head;

        if (t == null) {
            System.out.println("List is empty");
            return;
        }

        while (t != null) {
            System.out.println(t.id + " " + t.name + " "
                    + t.address + " " + t.position);
            t = t.next;
        }
    }

    static void insert() {
        System.out.print("Enter position: ");
        int pos = Integer.parseInt(sc.nextLine());

        if (pos < 1)
            throw new IllegalArgumentException("Invalid position");

        System.out.print("Enter ID: ");
        int id = Integer.parseInt(sc.nextLine());

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter position/role: ");
        String role = sc.nextLine();

        Member n = new Member(id, name, address, role);

        if (pos == 1) {
            n.next = head;
            head = n;
            return;
        }

        Member t = head;

        for (int i = 1; i < pos - 1 && t != null; i++)
            t = t.next;

        if (t == null)
            throw new IllegalArgumentException("Position not found");

        n.next = t.next;
        t.next = n;
        System.out.println("Member inserted");
    }

    static void delete() {
        System.out.print("Enter ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());

        if (head == null)
            throw new NoSuchElementException("List is empty");

        if (head.id == id) {
            head = head.next;
            System.out.println("Member deleted");
            return;
        }

        Member t = head;

        while (t.next != null && t.next.id != id)
            t = t.next;

        if (t.next == null)
            throw new NoSuchElementException("Member not found");

        t.next = t.next.next;
        System.out.println("Member deleted");
    }

    static void update() {
        System.out.print("Enter ID to update: ");
        int id = Integer.parseInt(sc.nextLine());

        Member t = head;

        while (t != null && t.id != id)
            t = t.next;

        if (t == null)
            throw new NoSuchElementException("Member not found");

        System.out.print("Enter new name: ");
        t.name = sc.nextLine();

        System.out.print("Enter new address: ");
        t.address = sc.nextLine();

        System.out.print("Enter new position: ");
        t.position = sc.nextLine();

        System.out.println("Member updated");
    }

    public static void main(String[] args) {
        int choice = 0;

        do {
            try {
                System.out.println("\n1.Create  2.Display  3.Insert");
                System.out.println("4.Delete  5.Update  6.Exit");
                System.out.print("Enter choice: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1: create(); break;
                    case 2: display(); break;
                    case 3: insert(); break;
                    case 4: delete(); break;
                    case 5: update(); break;
                    case 6: System.out.println("Exiting"); break;
                    default: System.out.println("Invalid choice");
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Enter a valid number");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (NoSuchElementException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Invalid input");
            }

        } while (choice != 6);

        sc.close();
    }
}