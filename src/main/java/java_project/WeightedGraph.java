package java_project;

import java.util.*;

public class WeightedGraph extends Graph {
    public Map<String, Integer> weights;

    public WeightedGraph(ArrayList<Integer> vertices, ArrayList<ArrayList<Integer>> edges , HashMap weights) {
        super(vertices, edges);
        this.weights = weights;
    }
    public int getWeight(int from, int to) {
        String edge = from + "-" + to;
        return weights.getOrDefault(edge, Integer.MAX_VALUE);
    }

    @Override
    public void breadthFirstSearch(int startVertex) {
        boolean[] visited = new boolean[vertices.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startVertex);
        visited[startVertex] = true;

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            ArrayList<Integer> neighbors = edges.get(currentVertex);
            for (int neighbor : neighbors) {
                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    }

    @Override
    public void depthFirstSearch(int startVertex) {
        boolean[] visited = new boolean[vertices.size()];
        dfsHelper(startVertex, visited);
    }

    private void dfsHelper(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");

        ArrayList<Integer> neighbors = edges.get(vertex);
        for (int neighbor : neighbors) {
            if (!visited[neighbor]) {
                dfsHelper(neighbor, visited);
            }
        }
    }

    public void dijkstraAlgorithm(int startVertex) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{startVertex, 0});

        int[] dist = new int[vertices.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startVertex] = 0;

        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int vertex = pair[0];
            int weight = pair[1];

            if (weight > dist[vertex]) continue;

            ArrayList<Integer> neighbors = edges.get(vertex);
            for (int neighbor : neighbors) {
                int newDist = weight + getWeight(vertex, neighbor);
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    pq.offer(new int[]{neighbor, newDist});
                }
            }
        }

        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("Shortest distance from vertex " + startVertex + " to vertex " + i + ": " + dist[i]);
        }
    }
    public ArrayList<ArrayList<Integer>> convertToMatrix() {
        ArrayList<ArrayList<Integer>> adjacencyMatrix = new ArrayList<>();
        int size = vertices.size();

    // Initialize the adjacency matrix with all zeros
        for (int i = 0; i < size; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                row.add(0);
            }
            adjacencyMatrix.add(row);
        }

    // Fill in the adjacency matrix based on the edges
        for (ArrayList<Integer> edge : edges) {
            int from = edge.get(0);
            int to = edge.get(1);
            adjacencyMatrix.get(from).set(to, 1); // Assuming unweighted graph, set 1 for existence of edge
        }
        return adjacencyMatrix;
    }
}
