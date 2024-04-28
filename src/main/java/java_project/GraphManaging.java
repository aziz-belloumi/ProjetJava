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
        String sql = "CREATE TABLE IF NOT EXISTS " + name + " (vertex INT PRIMARY KEY, connected_vertex INT)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("Table '" + name + "' created successfully");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
    }

    @Override
    @SuppressWarnings("NonPublicExported")
    public void updateGraph(String name, Graph graph) {
        try {
            clearGraphData(name);

            List<Integer> vertices = graph.vertices;
            for (int vertex : vertices) {
                addVertex(name, vertex);
            }

            ArrayList<ArrayList> edges = graph.edges;
            for (List<Integer> edge : edges) {
                addEdge(name, edge.get(0), edge.get(1));
            }
            System.out.println("Graph '" + name + "' updated successfully");
        } catch (SQLException e) {
            System.err.println("Error updating graph: " + e.getMessage());
        }
    }

    private void addVertex(String tableName, int vertex) throws SQLException {
        String sql = "INSERT INTO " + tableName + "(vertex, connected_vertex) VALUES (?, NULL)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, vertex);
            pstmt.executeUpdate();
        }
    }

    private void addEdge(String tableName, int u, int v) throws SQLException {
        String sql = "UPDATE " + tableName + " SET connected_vertex = ? WHERE vertex = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, v);
            pstmt.setInt(2, u);
            pstmt.executeUpdate();
        }
    }

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
