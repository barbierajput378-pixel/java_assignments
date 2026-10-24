import java.util.*;

public class Assignment_4_DS {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            while (true) {
                System.out.println("\n===== SUBSET SUM =====");
                System.out.println("1. Backtracking");
                System.out.println("2. Dynamic Programming");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int choice = sc.nextInt();

                if (choice == 3) {
                    System.out.println("Program exited.");
                    break;
                }

                if (choice != 1 && choice != 2) {
                    System.out.println("Invalid choice. Please enter 1, 2 or 3.");
                    continue;
                }

                // Take array size
                System.out.print("Enter number of elements: ");
                int n = sc.nextInt();

                if (n < 0) {
                    throw new IllegalArgumentException("Number of elements cannot be negative.");
                }

                // Take array elements
                int[] arr = new int[n];

                System.out.println("Enter array elements:");
                for (int i = 0; i < n; i++) {
                    arr[i] = sc.nextInt();
                }

                // Take target
                System.out.print("Enter target sum: ");
                int target = sc.nextInt();

                List<List<Integer>> result;

                if (choice == 1) {
                    // Find subsets using recursion
                    result = RecursionSubset.perfectSum(arr, target);
                } else {
                    result = DynamicSubset.perfectSum(arr, target);
                }

                // Print result
                System.out.println("Subsets with sum " + target + ":");

                if (choice == 1) {
                    RecursionSubset.print2dArray(result);
                } else {
                    DynamicSubset.print2dArray(result);
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter integers only.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } finally {
            sc.close();
        }
    }
}

class RecursionSubset {
    // Recursively finds all subsets with the given target
    static void findSubsets(int[] arr, int index, int target,
                            List<Integer> curr,
                            List<List<Integer>> result) {

        // Base case: If index exceeds array length
        if (index >= arr.length) {
            if (target == 0) {
                result.add(new ArrayList<>(curr));
            }// If target is not zero, no valid subset found
            return;
        }

        curr.add(arr[index]);

        findSubsets(arr, index + 1,
                    target - arr[index],
                    curr, result);

        curr.remove(curr.size() - 1);

        findSubsets(arr,
                    index + 1,
                    target,
                    curr, result);
    }

    static List<List<Integer>> perfectSum(int[] arr, int target) {
        // List to store all valid subsets
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> curr = new ArrayList<>();

        findSubsets(arr, 0, target, curr, result);

        return result;
    }

    static void print2dArray(List<List<Integer>> arr) {
        // Check if the list is empty
        if (arr.isEmpty()) {

            // No valid subsets found
            System.out.println("-1");
            return;
        }

        for (int row = 0; row < arr.size(); row++) {
            System.out.print("[");

            for (int col = 0; col < arr.get(row).size(); col++) {
                System.out.print(arr.get(row).get(col));

                if (col != arr.get(row).size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.print("]");

            if (row < arr.size() - 1)
                System.out.print(", ");
        }

        System.out.println();
    }
}

class DynamicSubset {
    // Recursive function to find all subsets with the given target sum
    static void findSubsets(int[] arr, boolean[][] dp,
                            int i, int target,
                            List<Integer> curr,
                            List<List<Integer>> res) {

        // Base case: If target becomes 0
        if (i == 0) {
            if (target == 0)
                res.add(new ArrayList<>(curr));

            if (arr[0] == target) {
                curr.add(arr[0]);
                res.add(new ArrayList<>(curr));
                curr.remove(curr.size() - 1);
            }

            return;
        }

        // Exclude current element
        if (target >= 0 && dp[i - 1][target]) {
            findSubsets(arr, dp, i - 1, target, curr, res);
        }

        // Include current element if it does not exceed target
        if (target >= arr[i] && dp[i - 1][target - arr[i]]) {
            curr.add(arr[i]);

            findSubsets(arr, dp,
                        i - 1,
                        target - arr[i],
                        curr, res);

            curr.remove(curr.size() - 1);
        }
    }

    // Returns all subsets with the given target sum
    static List<List<Integer>> perfectSum(int[] arr, int target) {
        int n = arr.length;

        if (n == 0 || target < 0)
            return new ArrayList<>();

        // DP table to store subset sum possibilities
        boolean[][] dp = new boolean[n][target + 1];

        // Correct DP initialization for handling zeroes
        dp[0][0] = true;

        if (arr[0] <= target)
            dp[0][arr[0]] = true;

        for (int i = 1; i < n; ++i) {
            for (int j = 0; j <= target; ++j) {
                dp[i][j] = dp[i - 1][j]
                        || (arr[i] <= j && dp[i - 1][j - arr[i]]);
            }
        }

        // If no subsets sum to target, return empty
        if (!dp[n - 1][target])
            return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();

        findSubsets(arr, dp,
                    n - 1,
                    target,
                    new ArrayList<>(),
                    res);

        return res;
    }

    // Function to print subsets in required format
    static void print2dArray(List<List<Integer>> arr) {
        if (arr.isEmpty()) {

            // No valid subsets found
            System.out.println("-1");
            return;
        }

        // Printing subsets in formatted output
        for (int row = 0; row < arr.size(); row++) {
            System.out.print("[");

            for (int col = arr.get(row).size() - 1; col >= 0; col--) {
                System.out.print(arr.get(row).get(col));

                if (col != 0) {
                    System.out.print(", ");
                }
            }

            System.out.print("]");

            if (row < arr.size() - 1)
                System.out.print(", ");
        }

        System.out.println();
    }
}