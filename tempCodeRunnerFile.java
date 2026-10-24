/*
 * Name: Barbie Rajput
 * Rollno. : UCE2025404
 *
 * Problem Statement:
 * Simulate the working of a simple music player playlist using
 * Doubly Linked List demonstrating operations like adding songs,
 * deleting songs and moving forward & backward through the playlist.
 *
 * Operations:
 * ADD id name artist: Add a new song at the end of the playlist.
 * Play the next song: Move to the next song and display its details.
 * Play Previous song: Move to the previous song and display its details.
 * CURRENT: Display the current song details.
 * DELETE id: Delete the song with the given ID.
 * SHOW: Display the entire playlist from first to last.
 * SHOW REVERSE: Display the entire playlist from last to first.
 */

package Practice;

import java.util.InputMismatchException;
import java.util.Scanner;


// ========================= NODE =========================

class Node {

    int id;
    String title;
    String artist;

    Node prev;
    Node next;

    Node(int id, String title, String artist) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.prev = null;
        this.next = null;
    }
}


// ========================= PLAYLIST =========================

class Playlist {

    Node head;
    Node tail;
    Node current;


    // ========================= ADD SONG =========================

    void addNewSong(int id, String title, String artist) {

        // Check for valid ID
        if (id <= 0) {
            System.out.println("Invalid ID! Song ID must be greater than 0.");
            return;
        }

        // Check for empty title
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Invalid title! Song title cannot be empty.");
            return;
        }

        // Check for empty artist
        if (artist == null || artist.trim().isEmpty()) {
            System.out.println("Invalid artist name! Artist name cannot be empty.");
            return;
        }

        // Check for duplicate ID
        if (findSong(id) != null) {
            System.out.println("Song with ID " + id + " already exists!");
            return;
        }

        Node temp = new Node(id, title.trim(), artist.trim());

        // If playlist is empty
        if (head == null) {

            head = temp;
            tail = temp;
            current = temp;

        } else {

            // Add at the end
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }

        System.out.println(
            "Added: [" + id + "] " + title.trim() + " - " + artist.trim()
        );
    }


    // ========================= FIND SONG =========================

    Node findSong(int id) {

        Node temp = head;

        while (temp != null) {

            if (temp.id == id) {
                return temp;
            }

            temp = temp.next;
        }

        return null;
    }


    // ========================= DELETE SONG =========================

    void deleteSong(int id) {

        // Condition 1: Empty playlist
        if (head == null) {
            System.out.println("Playlist is empty! Nothing to delete.");
            return;
        }

        // Condition 2: Invalid ID
        if (id <= 0) {
            System.out.println("Invalid ID! Song ID must be greater than 0.");
            return;
        }

        Node temp = findSong(id);

        // Condition 3: Song not found
        if (temp == null) {
            System.out.println("Song with ID " + id + " not found!");
            return;
        }


        // =========================
        // CASE 1: ONLY SONG
        // =========================

        if (temp == head && temp == tail) {

            head = null;
            tail = null;
            current = null;

        }


        // =========================
        // CASE 2: DELETE FIRST SONG
        // =========================

        else if (temp == head) {

            head = temp.next;
            head.prev = null;

            // If deleted song was current
            if (current == temp) {
                current = head;
            }
        }


        // =========================
        // CASE 3: DELETE LAST SONG
        // =========================

        else if (temp == tail) {

            tail = temp.prev;
            tail.next = null;

            // If deleted song was current
            if (current == temp) {
                current = tail;
            }
        }


        // =========================
        // CASE 4: DELETE MIDDLE SONG
        // =========================

        else {

            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;

            // If deleted song was current,
            // move current to the next song
            if (current == temp) {
                current = temp.next;
            }
        }

        // Disconnect deleted node
        temp.prev = null;
        temp.next = null;

        System.out.println("Deleted song with ID " + id);
    }


    // ========================= CURRENT SONG =========================

    void displayCurrent() {

        if (current == null) {
            System.out.println("No song is currently playing!");
            return;
        }

        System.out.println(
            "Now Playing -> ID: " +
            current.id +
            " | " +
            current.title +
            " - " +
            current.artist
        );
    }


    // ========================= PLAY NEXT =========================

    void playNext() {

        // Playlist empty
        if (current == null) {
            System.out.println("Playlist is empty! No songs are available.");
            return;
        }

        // Already at last song
        if (current.next == null) {
            System.out.println("Already the last song is playing!");
            return;
        }

        current = current.next;

        displayCurrent();
    }


    // ========================= PLAY PREVIOUS =========================

    void playPrev() {

        // Playlist empty
        if (current == null) {
            System.out.println("Playlist is empty! No songs are available.");
            return;
        }

        // Already at first song
        if (current.prev == null) {
            System.out.println("Already the first song is playing!");
            return;
        }

        current = current.prev;

        displayCurrent();
    }


    // ========================= SHOW FORWARD =========================

    void showList() {

        if (head == null) {
            System.out.println("The playlist is empty!");
            return;
        }

        Node temp = head;

        System.out.println("\n--- Playlist (Start to End) ---");

        while (temp != null) {

            String marker;

            if (temp == current) {
                marker = " [Playing] ";
            } else {
                marker = " ";
            }

            System.out.println(
                marker +
                "ID: " +
                temp.id +
                " | " +
                temp.title +
                " - " +
                temp.artist
            );

            temp = temp.next;
        }
    }


    // ========================= SHOW REVERSE =========================

    void showReverseList() {

        if (tail == null) {
            System.out.println("The playlist is empty!");
            return;
        }

        Node temp = tail;

        System.out.println("\n--- Playlist (End to Start) ---");

        while (temp != null) {

            String marker;

            if (temp == current) {
                marker = " [Playing] ";
            } else {
                marker = " ";
            }

            System.out.println(
                marker +
                "ID: " +
                temp.id +
                " | " +
                temp.title +
                " - " +
                temp.artist
            );

            temp = temp.prev;
        }
    }
}


// ========================= MAIN CLASS =========================

public class Main {

    // ========================= SAFE INTEGER INPUT =========================

    static int readInteger(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            try {

                return sc.nextInt();

            } catch (InputMismatchException e) {

                System.out.println(
                    "Invalid input! Please enter a valid integer."
                );

                sc.nextLine();
            }
        }
    }


    // ========================= SAFE STRING INPUT =========================

    static String readString(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            String input = sc.nextLine();

            if (input.trim().isEmpty()) {

                System.out.println(
                    "Input cannot be empty! Please try again."
                );

            } else {

                return input.trim();
            }
        }
    }


    // ========================= MAIN =========================

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Playlist playlist = new Playlist();

        boolean running = true;


        while (running) {

            System.out.println("\n========= MUSIC PLAYER MENU =========");
            System.out.println("1. Add new song to the playlist");
            System.out.println("2. Display list of all songs");
            System.out.println("3. Display list in reverse");
            System.out.println("4. Play next song");
            System.out.println("5. Play previous song");
            System.out.println("6. Show current song");
            System.out.println("7. Delete a song");
            System.out.println("8. Exit");
            System.out.println("=====================================");


            int choice = readInteger(sc, "Enter your choice: ");


            switch (choice) {

                // =========================
                // ADD SONG
                // =========================

                case 1:

                    int id = readInteger(sc, "Enter song ID: ");

                    sc.nextLine();

                    String title =
                        readString(sc, "Enter song title: ");

                    String artist =
                        readString(sc, "Enter artist name: ");

                    playlist.addNewSong(id, title, artist);

                    break;


                // =========================
                // SHOW LIST
                // =========================

                case 2:

                    playlist.showList();

                    break;


                // =========================
                // SHOW REVERSE
                // =========================

                case 3:

                    playlist.showReverseList();

                    break;


                // =========================
                // NEXT SONG
                // =========================

                case 4:

                    playlist.playNext();

                    break;


                // =========================
                // PREVIOUS SONG
                // =========================

                case 5:

                    playlist.playPrev();

                    break;


                // =========================
                // CURRENT SONG
                // =========================

                case 6:

                    playlist.displayCurrent();

                    break;


                // =========================
                // DELETE SONG
                // =========================

                case 7:

                    int delId =
                        readInteger(sc, "Enter song ID to delete: ");

                    playlist.deleteSong(delId);

                    break;


                // =========================
                // EXIT
                // =========================

                case 8:

                    running = false;

                    System.out.println(
                        "Exiting music player. Goodbye!"
                    );

                    break;


                // =========================
                // INVALID MENU CHOICE
                // =========================

                default:

                    System.out.println(
                        "Invalid choice! Please select a number between 1 and 8."
                    );
            }
        }

        sc.close();
    }
}