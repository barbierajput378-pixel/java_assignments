/*
Name: Barbie Rajput
Rollno. : UCE2025404


Problem Statement:
Simulate the working of a simple music player playlist using
Doubly Linked List demonstrating operations like adding songs,
deleting songs and moving forward & backward through the playlist.

Operations:
ADD id name artist: Add a new song at the end of the playlist.
Play the next song: Move to the next song and display its details.
Play Previous song: Move to the previous song and display its details.
CURRENT: Display the current song details.
DELETE id: Delete the song with the given ID.
SHOW: Display the entire playlist from first to last.
SHOW REVERSE: Display the entire playlist from last to first.
*/
import java.util.Scanner;
public class Main {
// Node class representing each song in the playlist
    private static final class Node {// Each song node contains an ID, title, artist, and pointers to the previous and next songs
        int id;
        String title;
        String artist;
        Node prev;
        Node next;
// Constructor to initialize a new song node
        Node(int id, String title, String artist) {
            this.id = id;
            this.title = title;
            this.artist = artist;
        }
    }
// Playlist class managing the doubly linked list of songs
    private static final class Playlist {
        private Node head;
        private Node tail;
        private Node current;
            // Method to add a new song to the playlist
        void addNewSong(int id, String title, String artist) {
            if (id <= 0) {
                System.out.println("Invalid ID! Song ID must be greater than 0.");
                return;
            }
// Check for empty title or artist name
            if (title == null || title.trim().isEmpty()) {
                System.out.println("Invalid title! Song title cannot be empty.");
                return;// Check for empty title or artist name
            }
// Check for empty title or artist name
            if (artist == null || artist.trim().isEmpty()) {
                System.out.println("Invalid artist name! Artist name cannot be empty.");
                return;
            }
                // Check if a song with the same ID already exists in the playlist
            if (findSong(id) != null) {
                System.out.println("Song with ID " + id + " already exists!");
                return;
            }// Create a new song node and add it to the end of the playlist

            Node song = new Node(id, title.trim(), artist.trim());
// If the playlist is empty, set the new song as head, tail, and current
            if (head == null) {
                head = song;
                tail = song;
                current = song;
            } else {// If the playlist is not empty, add the new song to the end and update pointers
                tail.next = song;
                song.prev = tail;
                tail = song;
            }
// Display a message indicating that the song has been added successfully
            System.out.println("Added: [" + id + "] " + song.title + " - " + song.artist);
        }
// Method to find a song in the playlist by its ID
        Node findSong(int id) {
            Node node = head;
            while (node != null) {
                if (node.id == id) {
                    return node;
                }
                node = node.next;
            }
            return null;// Return null if the song is not found
        }
// Method to delete a song from the playlist by its ID
        void deleteSong(int id) {
            if (head == null) {
                System.out.println("Playlist is empty! Nothing to delete.");
                return;
            }
// Check if the provided ID is valid (greater than 0)
            if (id <= 0) {
                System.out.println("Invalid ID! Song ID must be greater than 0.");
                return;
            }
// Find the song node to delete using the provided ID
            Node toDelete = findSong(id);
            if (toDelete == null) {
                System.out.println("Song with ID " + id + " not found!");
                return;
            }
// Handle deletion based on the position of the song in the playlist
            if (toDelete == head && toDelete == tail) {
                head = null;
                tail = null;
                current = null;// If the song to delete is the only song in the playlist, set head, tail, and current to null
            } else if (toDelete == head) {
                head = toDelete.next;
                head.prev = null;
                if (current == toDelete) {
                    current = head;
                }
            } else if (toDelete == tail) {// If the song to delete is the last song in the playlist, update the tail pointer
                tail = toDelete.prev;
                tail.next = null;
                if (current == toDelete) {
                    current = tail;
                }
            } else {// If the song to delete is in the middle of the playlist, update the pointers of the previous and next songs
                toDelete.prev.next = toDelete.next;
                toDelete.next.prev = toDelete.prev;
                if (current == toDelete) {
                    current = toDelete.next;
                }
            }
// Clear the pointers of the deleted song node to help with garbage collection
            toDelete.prev = null;
            toDelete.next = null;
            System.out.println("Deleted song with ID " + id);
        }
// Method to display the details of the currently playing song
        void displayCurrent() {
            if (current == null) {
                System.out.println("No song is currently playing!");
                return;
            }
            System.out.println("Now Playing -> ID: " + current.id + " | " + current.title + " - " + current.artist);
        }
// Method to play the next song in the playlist
        void playNext() {
            if (current == null) {// If there is no current song, the playlist is empty
                System.out.println("Playlist is empty! No songs are available.");
                return;
            }// If the current song is the last song in the playlist, display a message indicating that it's already the last song
            if (current.next == null) {
                System.out.println("Already the last song is playing!");
                return;
            }// Move to the next song and display its details
            current = current.next;
            displayCurrent();// Display the details of the newly playing song
        }
//  Method to play the previous song in the playlist
        void playPrev() {
            if (current == null) {// If there is no current song, the playlist is empty
                System.out.println("Playlist is empty! No songs are available.");
                return;
            }// If the current song is the first song in the playlist, display a message indicating that it's already the first song
            if (current.prev == null) {
                System.out.println("Already the first song is playing!");
                return;
            }// Move to the previous song and display its details
            current = current.prev;
            displayCurrent();
        }
// Method to display the entire playlist from start to end
        void showList() {
            if (head == null) {//   If the playlist is empty, display a message indicating that there are no songs to show
                System.out.println("The playlist is empty!");
                return;
            }
            System.out.println("\n--- Playlist (Start to End) ---");
            Node node = head;
            while (node != null) {// Iterate through the playlist and display each song's details
                String marker = node == current ? " [Playing] " : " ";
                System.out.println(marker + "ID: " + node.id + " | " + node.title + " - " + node.artist);
                node = node.next;
            }
        }
// Method to display the entire playlist from end to start
        void showReverseList() {
            if (tail == null) {
                System.out.println("The playlist is empty!");
                return;
            }
            System.out.println("\n--- Playlist (End to Start) ---");
            Node node = tail;
            while (node != null) {
                String marker = node == current ? " [Playing] " : " ";
                System.out.println(marker + "ID: " + node.id + " | " + node.title + " - " + node.artist);
                node = node.prev;// Iterate through the playlist in reverse order and display each song's details
            }
        }
    }
// Method to read an integer input from the user with validation
    private static int readInteger(Scanner sc, String message) {
        while (true) {// Prompt the user for input and read a line from the scanner
            System.out.print(message);
            String line = sc.nextLine();
            if (line == null || line.trim().isEmpty()) {// If the input is empty or null, display an error message and prompt again
                System.out.println("Invalid input! Please enter a valid integer.");
                continue;
            }// Try to parse the input as an integer and return it if successful
            try {// If the input is not a valid integer, catch the NumberFormatException and display an error message
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {// If the input is not a valid integer, catch the NumberFormatException and display an error message
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        }
    }
// Method to read a non-empty string input from the user with validation
    private static String readString(Scanner sc, String message) {
        while (true) {// Prompt the user for input and read a line from the scanner
            System.out.print(message);
            String input = sc.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("Input cannot be empty! Please try again.");
                continue;
            }// If the input is valid, trim any leading or trailing whitespace and return the string
            return input.trim();// If the input is valid, trim any leading or trailing whitespace and return the string
        }
    }
// Method to print the music player menu options
    private static void printMenu() {
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
    }
// Main method to run the music player application
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Playlist playlist = new Playlist();
        boolean running = true;
// Main loop to display the menu and handle user input
        while (running) {
            printMenu();// Display the music player menu
            int choice = readInteger(sc, "Enter your choice: ");
            switch (choice) {// Handle user input based on the selected menu option
                case 1 -> {
                    int id = readInteger(sc, "Enter song ID: ");
                    String title = readString(sc, "Enter song title: ");
                    String artist = readString(sc, "Enter artist name: ");
                    playlist.addNewSong(id, title, artist);
                }// Handle the case for adding a new song to the playlist by prompting the user for song details and calling the addNewSong method
                case 2 -> playlist.showList();
                case 3 -> playlist.showReverseList();
                case 4 -> playlist.playNext();
                case 5 -> playlist.playPrev();
                case 6 -> playlist.displayCurrent();
                case 7 -> {
                    int delId = readInteger(sc, "Enter song ID to delete: ");
                    playlist.deleteSong(delId);
                }
                case 8 -> {
                    running = false;
                    System.out.println("Exiting music player. Goodbye!");
                }
                default -> System.out.println("Invalid choice! Please select a number between 1 and 8.");
            }
        }

        sc.close();// Close the scanner to free up resources
    }
}
/*
======== MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 1
Enter song ID: 101
Enter song title: Golden
Enter artist name: Huntrx  
Added: [101] Golden - Huntrx

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 1
Enter song ID: 102
Enter song title: Afterglow
Enter artist name: Ariana Grande
Added: [102] Afterglow - Ariana Grande

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 1
Enter song ID: 103
Enter song title: Bad Blood
Enter artist name: Taylor Swift
Added: [103] Bad Blood - Taylor Swift

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 2

--- Playlist (Start to End) ---
 [Playing] ID: 101 | Golden - Huntrx
 ID: 102 | Afterglow - Ariana Grande
 ID: 103 | Bad Blood - Taylor Swift

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 3

--- Playlist (End to Start) ---
 ID: 103 | Bad Blood - Taylor Swift
 ID: 102 | Afterglow - Ariana Grande
 [Playing] ID: 101 | Golden - Huntrx

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 3

--- Playlist (End to Start) ---
 ID: 103 | Bad Blood - Taylor Swift
 ID: 102 | Afterglow - Ariana Grande
 [Playing] ID: 101 | Golden - Huntrx

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 4
Now Playing -> ID: 102 | Afterglow - Ariana Grande

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 4
Now Playing -> ID: 103 | Bad Blood - Taylor Swift

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 4
Already the last song is playing!

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 5
Now Playing -> ID: 102 | Afterglow - Ariana Grande

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 5
Now Playing -> ID: 101 | Golden - Huntrx

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 5
Already the first song is playing!

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 6
Now Playing -> ID: 101 | Golden - Huntrx

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 7
Enter song ID to delete: 101
Deleted song with ID 101

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 2

--- Playlist (Start to End) ---
 [Playing] ID: 102 | Afterglow - Ariana Grande
 ID: 103 | Bad Blood - Taylor Swift

========= MUSIC PLAYER MENU =========
1. Add new song to the playlist
2. Display list of all songs
3. Display list in reverse
4. Play next song
5. Play previous song
6. Show current song
7. Delete a song
8. Exit
=====================================
Enter your choice: 8
Exiting music player. Goodbye!
*/