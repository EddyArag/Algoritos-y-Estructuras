package Sesion9.src.Tests;

import Sesion9.src.GRAFO.GraphListEdge;

public class TestEjer8 {

    public static void main(String[] args) {
        System.out.println("========== INICIO DE PRUEBA DE REPRESENTACIONES DE GRAFO ==========\n");
        testDisplayGraphRepresentations();
        System.out.println("\n========== FIN DE PRUEBA ==========");
    }

    // Prueba para mostrar las diferentes representaciones del grafo
    public static void testDisplayGraphRepresentations() {
        System.out.println("--- Prueba: Mostrar representaciones del grafo dirigido ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(true); // Grafo dirigido

        // Inserción de vértices
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");

        // Inserción de aristas con pesos
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("A", "C", 2);
        graph.insertEdge("B", "C", 3);
        graph.insertEdge("C", "A", 3);

        // Mostrar representaciones
        System.out.println("Representaciones del grafo:");
        graph.displayGraphRepresentations();
    }
}
