package Graph;

import java.util.*;

public class assignment8 {
    static ArrayList<Integer>[] graph;
    static ArrayList<int[]> edges = new ArrayList<>();
    static boolean[] visited;
    static int[] parent;

    static void dfs(int u) {
        visited[u] = true;
        for (int v : graph[u]) {
            if (!visited[v]) {
                parent[v] = u;
                dfs(v);
            }
        }
    }

    static void path(int start, int end) {
        if (!visited[end]) {
            System.out.println("No path exists");
            return;
        }

        ArrayList<Integer> p = new ArrayList<>();
        for (int v = end; v != -1; v = parent[v])
            p.add(v);

        Collections.reverse(p);
        System.out.println("Path: " + p);
    }

    static int find(int x, int[] par) {
        if (par[x] == x) return x;
        return par[x] = find(par[x], par);
    }

    static long maxTree(int n) {
        edges.sort((a, b) -> Integer.compare(b[2], a[2]));

        int[] par = new int[n + 1];
        for (int i = 1; i <= n; i++) par[i] = i;

        long sum = 0;
        int count = 0;

        for (int[] e : edges) {
            int x = find(e[0], par);
            int y = find(e[1], par);

            if (x != y) {
                par[x] = y;
                sum += e[2];
                count++;
                if (count == n - 1) break;
            }
        }

        if (count != n - 1)
            throw new IllegalArgumentException("Spanning tree does not exist");

        return sum;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter vertices: ");
            int n = sc.nextInt();
            System.out.print("Enter edges: ");
            int m = sc.nextInt();

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            System.out.println("Enter source destination weight:");
            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt();
                graph[u].add(v);
                graph[v].add(u);
                edges.add(new int[]{u, v, w});
            }

            System.out.print("Start vertex: ");
            int start = sc.nextInt();
            System.out.print("End vertex: ");
            int end = sc.nextInt();

            visited = new boolean[n + 1];
            parent = new int[n + 1];
            Arrays.fill(parent, -1);

            dfs(start);
            path(start, end);

            long mstWeight = maxTree(n);
            System.out.println("Maximum Spanning Tree Total Weight: " + mstWeight);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}