package java_project;



public interface IGraphManaging {
    // Méthode pour créer un graphe avec un nom spécifié
    boolean createGraph(String name);

    // Méthode pour mettre à jour un graphe avec un nom spécifié et un nouveau graphe
    boolean updateGraph(String name, Graph graph);

    // Méthode pour supprimer un graphe avec un nom spécifié
    boolean deleteGraph(String name);
}

