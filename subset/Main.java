import java.util.*;

public class Main {

    // Reads an integer within a given range [min, max].
    public static int readIntInRange(Scanner sc, String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter an integer between " + min + " and " + max + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next(); // clear invalid token
            }
        }
    }

    // Reads a non-negative integer (>=0).
    public static int readNonNegativeInt(Scanner sc, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                value = sc.nextInt();
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println("Please enter a non-negative integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer.");
                sc.next();
            }
        }
    }

    // Reads an array of non-negative integers from the user.
    public static int[] readIntArray(Scanner sc, int n) {
        int[] arr = new int[n];
        System.out.println("Enter " + n + " non-negative integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = readNonNegativeInt(sc, "Element " + (i + 1) + ": ");
        }
        return arr;
    }

    // Dynamic Programming method to count subsets of nums[] that sum to target.
    // dp[i][j] = number of subsets using first i elements that sum to j.
    public static int countSubsetSumDP(int[] nums, int target) {
        int n = nums.length;
        int[][] dp = new int[n + 1][target + 1];

        // Base case: sum = 0 is always achievable (empty subset)
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                if (nums[i - 1] > j) {
                    // If current element is greater than target sum j, skip it
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // Otherwise, count subsets that:
                    // (1) Exclude current element + (2) Include current element
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[n][target];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("WELCOME\n");

        // Step 1: Read number of elements (1 to 30 allowed)
        int n = readIntInRange(sc, "Enter number of elements (1–30): ", 1, 30);
        System.out.println();

        // Step 2: Read array elements (non-negative)
        int[] nums = readIntArray(sc, n);

        // Step 3: Read target sum (non-negative)
        int target = readNonNegativeInt(sc, "Enter the target sum: ");

        // Step 4: Count subsets using DP
        int count = countSubsetSumDP(nums, target);

        // Step 5: Display result
        System.out.println();
        if (count > 0) {
            System.out.println("Subset(s) with the given sum exist.");
            System.out.println("Number of such subsets = " + count);
        } else {
            System.out.println("No subset with the given sum exists.");
        }
        
        sc.close();
    }
}