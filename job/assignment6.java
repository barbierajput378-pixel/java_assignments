import java.util.*;

// Job class to store job details
class Job {
    char id;       // Job ID (single letter: a, b, c, ...)
    int deadline;  // Deadline of job (positive integer)
    int profit;    // Profit if job is completed (non-negative)

    // Constructor
    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class assignment6 {

    // Function to schedule jobs for maximum profit using a greedy approach
    void printJobScheduling(ArrayList<Job> arr, int n) {
        if (arr == null || arr.isEmpty()) {
            System.out.println("No jobs to schedule.");
            return;
        }

        // Step 1: Sort jobs in descending order of profit
        arr.sort((a, b) -> Integer.compare(b.profit, a.profit));

        // Step 2: Find maximum deadline
        int maxDeadline = 0;
        for (Job job : arr) {
            if (job.deadline > maxDeadline)
                maxDeadline = job.deadline;
        }

        // Track occupied slots and store scheduled job IDs
        boolean[] slot = new boolean[maxDeadline + 1];
        char[] result = new char[maxDeadline + 1];
        int totalProfit = 0;

        // Step 3: Schedule jobs in the latest available slot before their deadline
        for (Job job : arr) {
            for (int j = Math.min(job.deadline, maxDeadline); j > 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Step 4: Print scheduled jobs and total profit
        System.out.print("Scheduled Jobs: ");
        for (int i = 1; i <= maxDeadline; i++) {
            if (slot[i])
                System.out.print(result[i] + " ");
        }
        System.out.println("\nMaximum Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Job> arr = new ArrayList<>();
        Set<Character> usedIDs = new HashSet<>(); // To track unique job IDs

        // Input number of jobs (positive integer only)
        int n;
        while (true) {
            System.out.print("Enter number of jobs (positive integer): ");
            if (sc.hasNextInt()) {
                n = sc.nextInt();
                if (n > 0) break;
                else System.out.println("Number of jobs must be positive.");
            } else {
                System.out.println("Invalid input. Enter an integer.");
                sc.next(); // Clear invalid input token
            }
        }

        // Input job details with comprehensive input validation
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Job " + (i + 1) + ":");

            // Job ID validation: single letter & unique
            char id;
            while (true) {
                System.out.print("Enter Job ID (single letter, unique): ");
                String s = sc.next();
                if (s.length() == 1 && Character.isLetter(s.charAt(0))) {
                    id = s.charAt(0);
                    if (!usedIDs.contains(id)) {
                        usedIDs.add(id); // Mark ID as used
                        break;
                    } else {
                        System.out.println("This Job ID already exists. Enter a unique ID.");
                    }
                } else {
                    System.out.println("Invalid input. Job ID must be a single letter.");
                }
            }

            // Deadline validation: positive integer
            int deadline;
            while (true) {
                System.out.print("Enter Deadline (positive integer): ");
                if (sc.hasNextInt()) {
                    deadline = sc.nextInt();
                    if (deadline > 0) break;
                    else System.out.println("Deadline must be positive.");
                } else {
                    System.out.println("Invalid input. Enter an integer.");
                    sc.next();
                }
            }

            // Profit validation: non-negative integer
            int profit;
            while (true) {
                System.out.print("Enter Profit (non-negative integer): ");
                if (sc.hasNextInt()) {
                    profit = sc.nextInt();
                    if (profit >= 0) break;
                    else System.out.println("Profit cannot be negative.");
                } else {
                    System.out.println("Invalid input. Enter an integer.");
                    sc.next();
                }
            }

            arr.add(new Job(id, deadline, profit));
        }

        // Schedule and print jobs
        assignment6 js = new assignment6();
        System.out.println();
        js.printJobScheduling(arr, arr.size());

        sc.close();
    }
}