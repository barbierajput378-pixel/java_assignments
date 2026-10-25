import java.util.*;

class Node {
    String key, meaning;
    Node left, right;

    Node(String k, String m) {
        key = k;
        meaning = m;
    }
}

public class Assignment_7 {
    static Scanner sc = new Scanner(System.in);
    static Node root = null;

    static Node insert(Node r, String k, String m) {
        if (r == null) return new Node(k, m);
        int c = k.compareToIgnoreCase(r.key);
        if (c < 0) r.left = insert(r.left, k, m);
        else if (c > 0) r.right = insert(r.right, k, m);
        else System.out.println("Keyword already exists!");
        return r;
    }

    static Node search(Node r, String k) {
        if (r == null || r.key.equalsIgnoreCase(k)) return r;
        if (k.compareToIgnoreCase(r.key) < 0) return search(r.left, k);
        return search(r.right, k);
    }

    static void ascending(Node r) {
        if (r != null) {
            ascending(r.left);
            System.out.println(r.key + " : " + r.meaning);
            ascending(r.right);
        }
    }

    static void descending(Node r) {
        if (r != null) {
            descending(r.right);
            System.out.println(r.key + " : " + r.meaning);
            descending(r.left);
        }
    }

    static Node delete(Node r, String k) {
        if (r == null) return null;
        int c = k.compareToIgnoreCase(r.key);
        if (c < 0) r.left = delete(r.left, k);
        else if (c > 0) r.right = delete(r.right, k);
        else {
            if (r.left == null) return r.right;
            if (r.right == null) return r.left;

            Node t = r.right;
            while (t.left != null) t = t.left;
            r.key = t.key;
            r.meaning = t.meaning;
            r.right = delete(r.right, t.key);
        }
        return r;
    }

    public static void main(String[] args) {
        int ch = 0;
        do {
            try {
                System.out.println("\n1.Add 2.Search 3.Update 4.Delete 5.Ascending 6.Descending 7.Exit");
                System.out.print("Choice: ");
                ch = sc.nextInt();
                sc.nextLine();

                switch (ch) {
                    case 1:
                        System.out.print("Keyword: ");
                        String k = sc.nextLine().trim();
                        System.out.print("Meaning: ");
                        String m = sc.nextLine().trim();
                        if (k.isEmpty() || m.isEmpty())
                            throw new IllegalArgumentException("Fields cannot be empty");
                        if (search(root, k) != null)
                            System.out.println("Keyword already exists!");
                        else {
                            root = insert(root, k, m);
                            System.out.println("Added!");
                        }
                        break;

                    case 2:
                        System.out.print("Keyword: ");
                        k = sc.nextLine();
                        Node t = search(root, k);
                        if (t == null) System.out.println("Not found");
                        else System.out.println(t.key + " : " + t.meaning);
                        break;

                    case 3:
                        System.out.print("Keyword: ");
                        k = sc.nextLine();
                        t = search(root, k);
                        if (t == null) System.out.println("Not found");
                        else {
                            System.out.print("New meaning: ");
                            t.meaning = sc.nextLine().trim();
                            System.out.println("Updated!");
                        }
                        break;

                    case 4:
                        System.out.print("Keyword: ");
                        k = sc.nextLine();
                        if (search(root, k) == null) System.out.println("Not found");
                        else {
                            root = delete(root, k);
                            System.out.println("Deleted!");
                        }
                        break;

                    case 5: ascending(root); break;
                    case 6: descending(root); break;
                    case 7: System.out.println("Exiting!"); break;
                    default: System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: Enter valid input");
                sc.nextLine();
            }
        } while (ch != 7);
        sc.close();
    }
}