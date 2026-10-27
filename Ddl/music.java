pakage ddl;

import java.util.*;

class Node {
    int id;
    String name, artist;
    Node prev, next;

    Node(int id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }
}

public class Assignment3_Music_Pl {
    static Node head, tail, current;

    static void add(int id, String name, String artist) {
        if (id <= 0 || name.isEmpty() || artist.isEmpty())
            throw new IllegalArgumentException("Invalid song details");

        Node n = head;
        while (n != null) {
            if (n.id == id)
                throw new IllegalArgumentException("ID already exists");
            n = n.next;
        }

        Node s = new Node(id, name, artist);

        if (head == null) {
            head = tail = current = s;
        } else {
            tail.next = s;
            s.prev = tail;
            tail = s;
        }

        System.out.println("Song added");
    }

    static void delete(int id) {
        Node n = head;

        while (n != null && n.id != id)
            n = n.next;

        if (n == null)
            throw new NoSuchElementException("Song not found");

        if (n.prev != null)
            n.prev.next = n.next;
        else
            head = n.next;

        if (n.next != null)
            n.next.prev = n.prev;
        else
            tail = n.prev;

        if (current == n)
            current = (n.next != null) ? n.next : n.prev;

        System.out.println("Song deleted");
    }

    static void show() {
        Node n = head;

        if (n == null) {
            System.out.println("Playlist empty");
            return;
        }

        while (n != null) {
            System.out.println(n.id + " " + n.name + " - " + n.artist);
            n = n.next;
        }
    }

    static void reverse() {
        Node n = tail;

        if (n == null) {
            System.out.println("Playlist empty");
            return;
        }

        while (n != null) {
            System.out.println(n.id + " " + n.name + " - " + n.artist);
            n = n.prev;
        }
    }

    static void play() {
        if (current == null)
            throw new NoSuchElementException("Playlist empty");

        System.out.println("Playing: " + current.name + " - " + current.artist);
    }

    static void next() {
        if (current == null || current.next == null)
            throw new NoSuchElementException("No next song");

        current = current.next;
        play();
    }

    static void previous() {
        if (current == null || current.prev == null)
            throw new NoSuchElementException("No previous song");

        current = current.prev;
        play();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            try {
                System.out.println("\n1.Add  2.Show  3.Reverse");
                System.out.println("4.Next  5.Previous  6.Current");
                System.out.println("7.Delete  8.Exit");
                System.out.print("Enter choice: ");

                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = Integer.parseInt(sc.nextLine());

                        System.out.print("Enter song name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Enter artist: ");
                        String artist = sc.nextLine().trim();

                        add(id, name, artist);
                        break;

                    case 2: show(); break;
                    case 3: reverse(); break;
                    case 4: next(); break;
                    case 5: previous(); break;
                    case 6: play(); break;

                    case 7:
                        System.out.print("Enter ID to delete: ");
                        delete(Integer.parseInt(sc.nextLine()));
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        break;

                    default:
                        System.out.println("Invalid choice");
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

        } while (choice != 8);

        sc.close();
    }
}