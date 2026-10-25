import java.util.*;

class Edge {
    int destination;
    int weight;

    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }
}

class KruskalEdge {
    int source;
    int destination;
    int weight;

    KruskalEdge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

public class assignment8 {

    static ArrayList<Edge>[] graph;
    static ArrayList<KruskalEdge> edges = new ArrayList<>();
    
    // Adjacency Matrix
    static int[][] adjMatrix;

    static boolean visited[];
    static int parent[];

    // ---------------- BFS (Safe from StackOverflow for n = 10^5) ----------------
    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Edge e : graph[current]) {
                int next = e.destination;

                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = current;
                    queue.add(next);
                }
            }
        }
    }

    // ------------ Print Path ------------
    static void printPath(int start, int end) {
        if (!visited[end]) {
            System.out.println("No Path Exists");
            return;
        }

        ArrayList<Integer> path = new ArrayList<>();
        int current = end;

        while (current != -1) {
            path.add(current);
            current = parent[current];
        }

        Collections.reverse(path);

        System.out.println("Task 1: Path from start vertex to end vertex");
        for (int x : path)
            System.out.print(x + " ");

        System.out.println();
    }

    // ----------- DSU (Union Find) ------------
    static int dsuParent[];

    static int find(int x) {
        if (dsuParent[x] == x)
            return x;
        dsuParent[x] = find(dsuParent[x]); // Path Compression
        return dsuParent[x];
    }

    static void union(int x, int y) {
        int leaderX = find(x);
        int leaderY = find(y);
        dsuParent[leaderY] = leaderX;
    }

    // -------- Maximum Spanning Tree --------
    static long maximumSpanningTree(int n) {
        Collections.sort(edges, (a, b) -> Integer.compare(b.weight, a.weight));

        dsuParent = new int[n + 1];
        for (int i = 1; i <= n; i++)
            dsuParent[i] = i;

        long totalBandwidth = 0;
        int edgesTaken = 0;

        for (KruskalEdge e : edges) {
            if (find(e.source) != find(e.destination)) {
                totalBandwidth += e.weight;
                union(e.source, e.destination);
                edgesTaken++;
                if (edgesTaken == n - 1)
                    break;
            }
        }

        return totalBandwidth;
    }

    // ---------------- Main ----------------
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices (n): ");
        int n = sc.nextInt();

        System.out.print("Enter number of edges (m): ");
        int m = sc.nextInt();

        // Initialize structures
        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        // Initialize the Adjacency Matrix
        adjMatrix = new int[n + 1][n + 1];

        System.out.println("\nEnter each edge as: source destination weight");

        for (int i = 0; i < m; i++) {
            System.out.print("Edge " + (i + 1) + ": ");
            int u = sc.nextInt();
            int v = sc.nextInt();
            int w = sc.nextInt();

            // Populate Adjacency List
            graph[u].add(new Edge(v, w));
            graph[v].add(new Edge(u, w));

            // Populate Kruskal Edge List
            edges.add(new KruskalEdge(u, v, w));

            // Populate Adjacency Matrix
            adjMatrix[u][v] = w;
            adjMatrix[v][u] = w;
        }

        // Print the Adjacency Matrix to the console
        System.out.println("\n--- Adjacency Matrix ---");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(adjMatrix[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.print("\nEnter start vertex: ");
        int start = sc.nextInt();

        System.out.print("Enter end vertex: ");
        int end = sc.nextInt();

        visited = new boolean[n + 1];
        parent = new int[n + 1];
        Arrays.fill(parent, -1);

        // Run BFS for Task 1
        bfs(start);

        System.out.println();
        printPath(start, end);

        // Run MST for Task 2
        long answer = maximumSpanningTree(n);

        System.out.println("Task 2: Maximum Spanning Tree total weight " + answer);

        sc.close();
    }
}