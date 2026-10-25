import java.util.*;

// Node class representing each keyword and its meaning in the BST
class Node {
    String keyword;
    String meaning;
    Node left, right;

    public Node(String keyword, String meaning) {
        this.keyword = keyword;
        this.meaning = meaning;
        this.left = this.right = null;
    }
}

// Class implementing the Binary Search Tree Dictionary operations
class DictionaryBST {
    private Node root;

    public DictionaryBST() {
        this.root = null;
    }

    // 1. INSERTION (Add Operation)
    public void insert(String keyword, String meaning) throws IllegalArgumentException {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be empty.");
        }
        if (meaning == null || meaning.trim().isEmpty()) {
            throw new IllegalArgumentException("Meaning cannot be empty.");
        }
        root = insertRec(root, keyword.trim(), meaning.trim());
    }

    private Node insertRec(Node root, String keyword, String meaning) {
        if (root == null) {
            System.out.println(">>> Keyword added successfully!");
            return new Node(keyword, meaning);
        }
        int cmp = keyword.compareToIgnoreCase(root.keyword);
        if (cmp < 0) {
            root.left = insertRec(root.left, keyword, meaning);
        } else if (cmp > 0) {
            root.right = insertRec(root.right, keyword, meaning);
        } else {
            System.out.println(">>> Keyword already exists! Use the update option to modify its meaning.");
        }
        return root;
    }

    // 2. SEARCH OPERATION
    public Node search(String keyword) {
        if (keyword == null) return null;
        Node current = root;
        while (current != null) {
            int cmp = keyword.trim().compareToIgnoreCase(current.keyword);
            if (cmp == 0) return current; // Found
            else if (cmp < 0) current = current.left;
            else current = current.right;
        }
        return null; // Not found
    }

    // 3. UPDATE OPERATION
    public boolean update(String keyword, String newMeaning) {
        Node target = search(keyword);
        if (target != null) {
            target.meaning = newMeaning.trim();
            return true;
        }
        return false;
    }

    // 4. DELETION OPERATION
    public boolean delete(String keyword) {
        if (search(keyword) == null) {
            return false;
        }
        root = deleteRec(root, keyword);
        return true;
    }

    private Node deleteRec(Node root, String keyword) {
        if (root == null) return root;

        int cmp = keyword.trim().compareToIgnoreCase(root.keyword);
        if (cmp < 0) {
            root.left = deleteRec(root.left, keyword);
        } else if (cmp > 0) {
            root.right = deleteRec(root.right, keyword);
        } else {
            // Node with only one child or no child
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            // Node with two children: Get inorder successor (smallest in the right subtree)
            Node successor = minValueNode(root.right);
            root.keyword = successor.keyword;
            root.meaning = successor.meaning;

            // Delete the inorder successor
            root.right = deleteRec(root.right, successor.keyword);
        }
        return root;
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // 5. DISPLAY OPERATIONS (Ascending / Alphabetical)
    public void displayAscending() {
        if (root == null) {
            System.out.println("Dictionary is empty.");
            return;
        }
        inorderAsc(root);
    }

    private void inorderAsc(Node root) {
        if (root != null) {
            inorderAsc(root.left);
            System.out.println("• " + root.keyword + " : " + root.meaning);
            inorderAsc(root.right);
        }
    }

    // 6. DISPLAY OPERATIONS (Descending / Reverse-Alphabetical)
    public void displayDescending() {
        if (root == null) {
            System.out.println("Dictionary is empty.");
            return;
        }
        inorderDesc(root);
    }

    private void inorderDesc(Node root) {
        if (root != null) {
            inorderDesc(root.right);
            System.out.println("• " + root.keyword + " : " + root.meaning);
            inorderDesc(root.left);
        }
    }
}

// Main class providing the runtime user-driven menu interface with Try-Catch Block Handling
public class Assignment_7 {
    public static void main(String[] args) {
        DictionaryBST dict = new DictionaryBST();
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            try {
                System.out.println("\n================ DICTIONARY MENU ================");
                System.out.println("1. Add Keyword");
                System.out.println("2. Search Keyword");
                System.out.println("3. Update Meaning");
                System.out.println("4. Delete Keyword");
                System.out.println("5. Display (Ascending/Alphabetical)");
                System.out.println("6. Display (Descending/Reverse-Alphabetical)");
                System.out.println("7. Exit");
                System.out.print("Enter your choice (1-7): ");
                
                choice = sc.nextInt();
                sc.nextLine(); // Consume trailing newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter Keyword to add: ");
                        String word = sc.nextLine();
                        System.out.print("Enter Meaning: ");
                        String meaning = sc.nextLine();
                        dict.insert(word, meaning);
                        break;

                    case 2:
                        System.out.print("Enter Keyword to search: ");
                        String sWord = sc.nextLine();
                        Node result = dict.search(sWord);
                        if (result != null) {
                            System.out.println("Found! -> " + result.keyword + " : " + result.meaning);
                        } else {
                            System.out.println("Keyword not found in the dictionary.");
                        }
                        break;

                    case 3:
                        System.out.print("Enter Keyword to update: ");
                        String uWord = sc.nextLine();
                        System.out.print("Enter New Meaning: ");
                        String uMeaning = sc.nextLine();
                        if (dict.update(uWord, uMeaning)) {
                            System.out.println("Meaning updated successfully!");
                        } else {
                            System.out.println("Keyword not found. Cannot update.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Keyword to delete: ");
                        String dWord = sc.nextLine();
                        if (dict.delete(dWord)) {
                            System.out.println("Keyword '" + dWord + "' deleted successfully.");
                        } else {
                            System.out.println("Keyword not found. Deletion failed.");
                        }
                        break;

                    case 5:
                        System.out.println("\n--- Alphabetical Order ---");
                        dict.displayAscending();
                        break;

                    case 6:
                        System.out.println("\n--- Reverse Alphabetical Order ---");
                        dict.displayDescending();
                        break;

                    case 7:
                        System.out.println("Exiting Dictionary Application. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice! Please choose an option from 1 to 7.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Error: Invalid entry format detected. Please input an integer only.");
                sc.nextLine(); // Clear the bad input buffer string from the scanner stream
                choice = 0;    
            } 
            catch (IllegalArgumentException e) {
                System.out.println("Validation Error: " + e.getMessage());
            } 
            catch (Exception e) {
                System.out.println("An unexpected system exception occurred: " + e.getMessage());
            }
            
        } while (choice != 7);

        sc.close();
    }
}
