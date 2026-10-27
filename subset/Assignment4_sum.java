package subset;
import java.util.*;


public class Assignment4_sum {

    static void backtrack(int[] a, int i, int sum, List<Integer> curr) {
        if (i == a.length) {
            if (sum == 0)
                System.out.println(curr);
            return;
        }

        curr.add(a[i]);
        backtrack(a, i + 1, sum - a[i], curr);
        curr.remove(curr.size() - 1);

        backtrack(a, i + 1, sum, curr);
    }

    static void dp(int[] a, int target) {
        int n = a.length;
        boolean[][] d = new boolean[n + 1][target + 1];

        d[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                d[i][j] = d[i - 1][j];
                if (a[i - 1] <= j)
                    d[i][j] |= d[i - 1][j - a[i - 1]];
            }
        }

        if (!d[n][target]) {
            System.out.println("No subset found");
            return;
        }

        System.out.println("Subsets:");
        backtrack(a, 0, target, new ArrayList<>());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            int choice;
            do {
                System.out.println("\n1. Backtracking");
                System.out.println("2. Dynamic Programming");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();

                if (choice == 3) break;

                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice");
                    continue;
                }

                System.out.print("Enter number of elements: ");
                int n = sc.nextInt();

                if (n < 0)
                    throw new IllegalArgumentException("Size cannot be negative");

                int[] a = new int[n];
                System.out.println("Enter elements:");
                for (int i = 0; i < n; i++) {
                    a[i] = sc.nextInt();
                    if (a[i] < 0)
                        throw new IllegalArgumentException("Elements must be non-negative");
                }

                System.out.print("Enter target sum: ");
                int target = sc.nextInt();

                if (target < 0)
                    throw new IllegalArgumentException("Target cannot be negative");

                System.out.println("Subsets with sum " + target + ":");

                if (choice == 1) {
                    backtrack(a, 0, target, new ArrayList<>());
                } else {
                    dp(a, target);
                }

            } while (true);

        } catch (InputMismatchException e) {
            System.out.println("Error: Enter integers only.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}