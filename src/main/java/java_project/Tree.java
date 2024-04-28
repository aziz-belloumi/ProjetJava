package java_project;

import java.util.*;

public class Tree extends Graph {
    public Integer root;
    public Map<Integer, Integer> parent;
    public Map<Integer, ArrayList<Integer>> children;
    public Map<Integer, Integer> depth;

    public Tree(ArrayList<Integer> vertices, ArrayList<ArrayList<Integer>> edges, Integer root, Map<Integer, ArrayList<Integer>> children, Map<Integer, Integer> parent, Map<Integer, Integer> depth) {
        super(vertices, edges);
        this.root = root;
        this.children = children ;
        this.parent = parent ;
        this.depth = depth;
    }

    @Override
    public void breadthFirstSearch(int startVertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startVertex);
        depth.put(startVertex, 0);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            ArrayList<Integer> currentChildren = children.getOrDefault(currentVertex, new ArrayList<>());
            for (int i = 0; i < currentChildren.size(); i++) {
                int child = currentChildren.get(i);
                if (!depth.containsKey(child)) {
                    depth.put(child, depth.get(currentVertex) + 1);
                    parent.put(child, currentVertex);
                    queue.offer(child);
                }
            }
        }
    }

    @Override
    public void depthFirstSearch(int startVertex) {
        dfsHelper(startVertex);
    }

    private void dfsHelper(int vertex) {
        System.out.print(vertex + " ");

        ArrayList<Integer> currentChildren = children.getOrDefault(vertex, new ArrayList<>());
        for (int i = 0; i < currentChildren.size(); i++) {
            int child = currentChildren.get(i);
            if (!depth.containsKey(child)) {
                depth.put(child, depth.get(vertex) + 1);
                parent.put(child, vertex);
                dfsHelper(child);
            }
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

    // Fill in the adjacency matrix based on the parent-child relationships
    for (Map.Entry<Integer, Integer> entry : parent.entrySet()) {
        int child = entry.getKey();
        int parent = entry.getValue();
        adjacencyMatrix.get(parent).set(child, 1);
    }

    return adjacencyMatrix;
}

    
}
