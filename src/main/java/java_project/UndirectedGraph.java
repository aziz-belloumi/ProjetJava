package java_project;

import java.util.*;

public class UndirectedGraph extends Graph {
    public UndirectedGraph(ArrayList<Integer> vertices, ArrayList<ArrayList<Integer>> edges) {
        super(vertices, edges);
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
        // Implémentation de la recherche en profondeur pour un graphe non orienté
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
        // Implémentation de l'algorithme de Dijkstra pour un graphe non orienté
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
            for (int i = 0; i < neighbors.size(); i++) {
                int neighbor = neighbors.get(i);
                int edgeWeight = getWeight(vertex, neighbor);
                int newDist = dist[vertex] + edgeWeight;
                if (newDist < dist[neighbor]) {
                    dist[neighbor] = newDist;
                    pq.offer(new int[]{neighbor, newDist});
                }
            }
        }
        // Affichage des distances les plus courtes
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("Distance la plus courte du sommet " + startVertex + " au sommet " + i + ": " + dist[i]);
        }
    }

    private int getWeight(int vertex, int neighbor) {
        return 1;
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
            int v1 = edge.get(0);
            int v2 = edge.get(1);
            adjacencyMatrix.get(v1).set(v2, 1);
            adjacencyMatrix.get(v2).set(v1, 1);
        }

        return adjacencyMatrix;
    }
}
