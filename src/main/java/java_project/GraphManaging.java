package java_project;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

public class GraphManaging implements IGraphManaging {
    private Connection connection;

    public GraphManaging(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void createGraph(String name) {
        String sql = "CREATE TABLE IF NOT EXISTS " + name + " (vertex INT PRIMARY KEY)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("Table '" + name + "' created successfully");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    @Override
    public void updateGraph(String name, Graph graph) {
        // Update vertices and edges
        try {
            // First, clear existing data in the table
            clearGraphData(name);

            // Add vertices to the table
            List<Integer> vertices = graph.vertices;
            for (int vertex : vertices) {
                addVertex(name, vertex);
            }
            // Add edges to the table
            ArrayList<ArrayList> edges = graph.edges;
            for (List<Integer> edge : edges) {
                addEdge(name, edge.get(0), edge.get(1));
            }
            System.out.println("Graph '" + name + "' updated successfully");
        } catch (SQLException e) {
            System.err.println("Error updating graph: " + e.getMessage());
        }
    }

    // Helper method to add a vertex to the graph table
    private void addVertex(String tableName, int vertex) throws SQLException {
        String sql = "INSERT INTO " + tableName + "(vertex) VALUES (?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, vertex);
            pstmt.executeUpdate();
        }
    }

    // Helper method to add an edge to the graph table
    private void addEdge(String tableName, int u, int v) throws SQLException {
        String sql = "INSERT INTO " + tableName + "(vertex1, vertex2) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, u);
            pstmt.setInt(2, v);
            pstmt.executeUpdate();
        }
    }

    // Helper method to clear existing data in the graph table
    private void clearGraphData(String tableName) throws SQLException {
        String sql = "DELETE FROM " + tableName;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
        }
    }

    @Override
    public void deleteGraph(String name) {
        String sql = "DROP TABLE IF EXISTS " + name;
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("Table '" + name + "' deleted successfully");
        } catch (SQLException e) {
            System.err.println("Error deleting table: " + e.getMessage());
        }
    }
}

