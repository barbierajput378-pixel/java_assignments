import java.util.*;

class Job {
    String id;
    int deadline, profit;

    Job(String id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class assignment6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter total number of jobs: ");
            int n = sc.nextInt();

            if (n <= 0)
                throw new IllegalArgumentException("Number of jobs must be positive.");

            Job[] jobs = new Job[n];

            for (int i = 0; i < n; i++) {
                System.out.println("Enter ID, Deadline and Profit for Job " + (i + 1) + ":");

                String id = sc.next();
                int deadline = sc.nextInt();
                int profit = sc.nextInt();

                if (deadline <= 0 || profit < 0)
                    throw new IllegalArgumentException("Invalid deadline or profit.");

                jobs[i] = new Job(id, deadline, profit);
            }

            // Sort jobs by decreasing profit (Greedy approach)
            Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

            int maxDeadline = 0;
            for (Job job : jobs)
                maxDeadline = Math.max(maxDeadline, job.deadline);

            boolean[] slot = new boolean[maxDeadline + 1];
            String[] result = new String[maxDeadline + 1];
            int totalProfit = 0;

            for (Job job : jobs) {
                for (int j = job.deadline; j > 0; j--) {
                    if (!slot[j]) {
                        slot[j] = true;
                        result[j] = job.id;
                        totalProfit += job.profit;
                        break;
                    }
                }
            }

            System.out.print("Scheduling Sequence of Jobs: ");
            for (int i = 1; i <= maxDeadline; i++) {
                if (slot[i])
                    System.out.print(result[i] + " ");
            }

            System.out.println("\nTotal Profit = " + totalProfit);

        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter valid input types.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred.");
        } finally {
            sc.close();
        }
    }
}