package DS_ESE;
import java.util.*;
public class Subset {

	//backtrack
	public static void backtrack(int a[], int i, int sum, List<Integer> curr) {
		if (sum == 0) {
			System.out.println("->" + curr);
			return;
		}
		if (i == a.length || sum < 0) {
			return;
		}
		
		curr.add(a[i]);
		backtrack(a, i + 1, sum - a[i], curr);
		curr.remove(curr.size() - 1);
		backtrack(a, i + 1, sum, curr);
	}
	// dp
	public static boolean dp(int[] a, int target) {
		int n = a.length;
		boolean[][] d = new boolean[n+1][target+1];
		d[0][0]=true;
		for (int i = 0; i <= n; i++) {
			d[i][0] = true;
		}
		 for(int i = 1; i<=n ; i++) {
			 for(int j =0; j<= target; j++) {
				 d[i][j] = d[i-1][j];
				 if(a[i-1]<= j)
					 d[i][j] =d[i - 1][j] || d[i-1][j-a[i-1]];
			 
			 
				 }
		 }
		  return d[n][target];
	}
	
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Enter number of elements :");
			int n = sc.nextInt();
			sc.nextLine();
			int[] a = new int[n];
			
			System.out.println("Enter the elements :");
			for(int i =0; i<n; i++) {
				a[i] = sc.nextInt();
			}
			sc.nextLine();
			
			System.out.println("Enter target sum :");
			int target = sc.nextInt();
			sc.nextLine();
			
			int choice;
			do {
				System.out.println("--Menu---");
				System.out.println("1.Backtrack \n2.Dynamic Programming \n3.Exit");
				System.out.println("Enter your choice :");
				choice = sc.nextInt();
				sc.nextLine();
				switch(choice) {
				case 1:
					backtrack(a,0,target,new ArrayList<>());
					break;
				case 2:
					if (dp(a, target)) {
						System.out.println("Subset exists! Printing via Backtrack:");
						backtrack(a, 0, target, new ArrayList<>());
					} else {
						System.out.println("No subset is found...");
					}
					break;
				case 3:
					System.out.println("Exiting the program...");
					break;
					default :
						System.out.println("Invalid choice...");
				}
			}while(choice != 3);
		}catch(Exception e) {
			System.out.println("Invalid input....error has occured!!");
		}finally {
			sc.close();
		}

	}

}
