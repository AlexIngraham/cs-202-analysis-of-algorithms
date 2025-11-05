import java.util.*;

public class main {
    
    static class Edge {
        String destination;
        int length;

        Edge(String destination, int length) {
            this.destination = destination;
            this.length = length;
        }
    }

    static class Node implements Comparable<Node> {
        String city;
        int cost;

        Node(String city, int cost) {
            this.city = city;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.cost, other.cost);
        }
    }

    public static int[] findShortest(Map<String, List<Edge>> graph, String start, String end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<String, Integer> distances = new HashMap<>();
        Map<String, Integer> pathCount = new HashMap<>();

        // Initialize all cities
        for (String city : graph.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
            pathCount.put(city, 0);
        }
        
        distances.put(start, 0);
        pathCount.put(start, 1); 
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            String currentCity = currentNode.city;
            int currentDist = currentNode.cost;

            if (currentDist > distances.get(currentCity)) {
                continue;
            }

            if (graph.containsKey(currentCity)) {
                for (Edge edge : graph.get(currentCity)) {
                    String neighbor = edge.destination;
                    int newDist = distances.get(currentCity) + edge.length;

                    if (newDist < distances.get(neighbor)) {
                        distances.put(neighbor, newDist);
                        pathCount.put(neighbor, pathCount.get(currentCity));
                        pq.add(new Node(neighbor, newDist));
                    } else if (newDist == distances.get(neighbor)) {
                        pathCount.put(neighbor, pathCount.get(neighbor) + pathCount.get(currentCity));
                    }
                }
            }
        }

        int shortestDistance = distances.get(end);
        int numPaths = pathCount.get(end); 
        
        return shortestDistance == Integer.MAX_VALUE ? 
            new int[]{-1, 0} : new int[]{shortestDistance, numPaths}; 
    }

    public static void main(String[] args) {
        Map<String, List<Edge>> graph = new HashMap<>();

        String[][] roads = {
            {"Fairview", "Madison", "7"},
            {"Fairview", "Ashland", "2"},
            {"Ashland", "Clayton", "3"},
            {"Ashland", "Hudson", "3"},
            {"Madison", "Hudson", "3"},
            {"Hudson", "Greenville", "1"},
            {"Madison", "Greenville", "1"},
            {"Clayton", "Greenville", "1"},
            {"Fairview", "Franklin", "5"},
            {"Franklin", "Madison", "2"}
        };
        
        for (String[] road : roads) {
            String from = road[0];
            String to = road[1];
            int length = Integer.parseInt(road[2]);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());

            graph.get(from).add(new Edge(to, length));
            graph.get(to).add(new Edge(from, length));
        }

        String start = "Fairview";
        String end = "Madison";
        int[] result = findShortest(graph, start, end);

        System.out.println(result[0] + " " + result[1]);
    }
}