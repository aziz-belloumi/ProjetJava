package java_project;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("printing the matrix of the undirected graph : ");
        ArrayList<Integer> vertices = new ArrayList<>();
        vertices.add(0);
        vertices.add(1);
        vertices.add(2);

        // Create an ArrayList of edges
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();
        edges.add(new ArrayList<>(List.of(0, 1)));
        edges.add(new ArrayList<>(List.of(0, 2)));
        edges.add(new ArrayList<>(List.of(1, 2)));

        // Create an instance of UndirectedGraph
        UndirectedGraph graph1 = new UndirectedGraph(vertices, edges);
        System.out.println(graph1.vertices);
        System.out.println(graph1.edges);
        graph1.breadthFirstSearch(0);
        System.out.println();
        graph1.depthFirstSearch(0);
        System.out.println();
        graph1.dijkstraAlgorithm(0);
        // Convert to matrix and print the adjacency matrix
        ArrayList<ArrayList<Integer>> adjacencyMatrix1 = graph1.convertToMatrix();
        for (ArrayList<Integer> row : adjacencyMatrix1) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        
        System.out.println("printing the matrix of the directed graph : ");
        DirectedGraph graph2 = new DirectedGraph(vertices, edges);
        System.out.println(graph2.vertices);
        System.out.println(graph2.edges);
        graph2.breadthFirstSearch(0);
        System.out.println();
        graph2.depthFirstSearch(0);
        System.out.println();
        graph2.dijkstraAlgorithm(0);
        // Convert to matrix and print the adjacency matrix
        ArrayList<ArrayList<Integer>> adjacencyMatrix2 = graph2.convertToMatrix();
        for (ArrayList<Integer> row : adjacencyMatrix2) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
        System.out.println("printing the matrix of the weighted graph : ");
        
        Map<String, Integer> weights = new HashMap<>();
        weights.put("0-1", 2);
        weights.put("0-2", 3);
        weights.put("1-2", 1);

        // Create an instance of WeightedGraph with vertices, edges, and weights
        WeightedGraph graph = new WeightedGraph(vertices, edges, (HashMap) weights);

        System.out.println(graph.vertices);
        System.out.println(graph.edges);
        graph.breadthFirstSearch(0);
        System.out.println();
        graph.depthFirstSearch(0);
        System.out.println();
        graph.dijkstraAlgorithm(0);

        // Convert to matrix and print the adjacency matrix
        ArrayList<ArrayList<Integer>> adjacencyMatrix = graph.convertToMatrix();
        for (ArrayList<Integer> row : adjacencyMatrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        } 
        
        
        
        System.out.println("printing the matrix of the tree : ");
        ArrayList<Integer> verticesTree = new ArrayList<>();
        verticesTree.add(0);
        verticesTree.add(1);
        verticesTree.add(2);
        ArrayList<ArrayList<Integer>> edgesTree = new ArrayList<>();
        edgesTree.add(new ArrayList<>(List.of(0, 1)));
        edgesTree.add(new ArrayList<>(List.of(0, 2)));    
        Map<Integer, ArrayList<Integer>> children = new HashMap<>();
        children.put(0, new ArrayList<>(List.of(1, 2)));
        children.put(1, new ArrayList<>());
        children.put(2, new ArrayList<>());

        Map<Integer, Integer> parent = new HashMap<>();
        parent.put(1, 0);   
        parent.put(2, 0);

        Map<Integer, Integer> depth = new HashMap<>();
        depth.put(0, 0);
        depth.put(1, 1);
        depth.put(2, 1);

    // Create an instance of Tree with vertices, edges, children, parent, and depthz
       Tree tree = new Tree(verticesTree, edgesTree, 0, children, parent, depth);


       System.out.println(tree.vertices);
       System.out.println(tree.edges);
       graph.breadthFirstSearch(0);
       System.out.println();
       graph.depthFirstSearch(0);

    // Convert to matrix and print the adjacency matrix
       ArrayList<ArrayList<Integer>> adjacencyMatrixTree = tree.convertToMatrix();
       for (ArrayList<Integer> row : adjacencyMatrixTree) {
            for (int value : row) {
                System.out.print(value + " ");
            } 
            System.out.println();
        }
    }  
}

