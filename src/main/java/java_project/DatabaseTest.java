/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package java_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseTest {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TEST", "postgres", "aziznoura")) {
            // Création d'une instance de GraphManaging
            GraphManaging graphManager = new GraphManaging(connection);

            // Création d'un premier graph vide
            graphManager.createGraph("emptyGraph");

            // Création d'un deuxième graph avec des valeurs
            ArrayList<Integer> verticesWithValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            ArrayList<ArrayList<Integer>> edgesWithValues = new ArrayList<>();
            edgesWithValues.add(new ArrayList<>(Arrays.asList(1, 2)));
            edgesWithValues.add(new ArrayList<>(Arrays.asList(2, 3)));
            edgesWithValues.add(new ArrayList<>(Arrays.asList(2, 4)));
            edgesWithValues.add(new ArrayList<>(Arrays.asList(3, 5)));
            Graph graphWithValues = new UndirectedGraph(verticesWithValues, edgesWithValues);
            graphManager.createGraph("graphWithValues");
            graphManager.updateGraph("graphWithValues", graphWithValues);
            
            ArrayList<Integer> verticesWithValuesUp = new ArrayList<>(Arrays.asList(6, 7, 8));
            ArrayList<ArrayList<Integer>> edgesWithValuesUp = new ArrayList<>();
            edgesWithValuesUp.add(new ArrayList<>(Arrays.asList(5, 6)));
            edgesWithValuesUp.add(new ArrayList<>(Arrays.asList(5, 7)));
            edgesWithValuesUp.add(new ArrayList<>(Arrays.asList(6, 8)));
            Graph graphWithValues2 = new UndirectedGraph(verticesWithValuesUp, edgesWithValuesUp);
            graphManager.updateGraph("graphWithValues", graphWithValues2);
            

            // Création d'un troisième graph avec des valeurs différentes
            ArrayList<Integer> otherVertices = new ArrayList<>(Arrays.asList(10, 20, 30));
            ArrayList<ArrayList<Integer>> otherEdges = new ArrayList<>();
            otherEdges.add(new ArrayList<>(Arrays.asList(10, 20)));
            otherEdges.add(new ArrayList<>(Arrays.asList(20, 30)));
            Graph otherGraph = new UndirectedGraph(otherVertices, otherEdges);
            graphManager.createGraph("otherGraph");

            // Mise à jour du troisième graph avec des valeurs
            graphManager.updateGraph("otherGraph", otherGraph);

            // Suppression du troisième graph
            //graphManager.deleteGraph("otherGraph");

        } catch (SQLException e) {
        }
    }
}

