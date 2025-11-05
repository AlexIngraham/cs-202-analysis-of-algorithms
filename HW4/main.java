import java.io.*;
import java.util.*;

public class main {
    
    private static void dfs(int node, List<List<Integer>> adj, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (visited[current]) {
                continue;
            }
            visited[current] = true;

            for (int neighbor : adj.get(current)) {
                if (!visited[neighbor]) {
                    stack.push(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("map.txt"));
        String[] firstLine = br.readLine().split(" ");
        int n = Integer.parseInt(firstLine[0]);
        int m = Integer.parseInt(firstLine[1]);

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            int u = Integer.parseInt(edge[0]) - 1;
            int v = Integer.parseInt(edge[1]) - 1;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        br.close();

        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, adj, visited);
            }
        }

        System.out.println(components - 1);
    }
}


/*
 * RUNTIME:
 * 
 * Let n = number of cities, which are the vertices
 * Let m = number of roads, which are the edges
 * 
 * Each vertex and edge is visited at most once in the entire DFS traversal
 * So, the time complexity is O(n + m), and the space complexity is also O(n + m)
 * 
 * It is efficient for graph traversal problems, especially when the graph has millions of nodes or edges.
 * 
 */