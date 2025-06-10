package Sesion9.src.Tests;

import Sesion9.src.GRAFO.GraphListEdge;

public class TestEjer7 {

    public static void main(String[] args) {
        System.out.println("========== INICIO DE PRUEBAS DE TIPO DE GRAFO ==========\n");

        testIdentifyGraphType_Path();
        testIdentifyGraphType_Cycle();
        testIdentifyGraphType_Wheel();
        testIdentifyGraphType_Complete();
        testIdentifyGraphType_Unknown();

        System.out.println("\n========== FIN DE PRUEBAS ==========");
    }

    // Prueba para identificar un grafo tipo "Camino"
    public static void testIdentifyGraphType_Path() {
        System.out.println("\n--- Prueba: Tipo de grafo - Camino ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(true); // Grafo dirigido
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("B", "C", 2);

        String tipo = graph.identifyGraphType("A");
        System.out.println("Tipo identificado (esperado: Camino): " + tipo);
    }

    // Prueba para identificar un grafo tipo "Ciclo"
    public static void testIdentifyGraphType_Cycle() {
        System.out.println("\n--- Prueba: Tipo de grafo - Ciclo ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(true); // Grafo dirigido
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("C", "A", 3);

        String tipo = graph.identifyGraphType("A");
        System.out.println("Tipo identificado (esperado: Ciclo): " + tipo);
    }

    // Prueba para identificar un grafo tipo "Rueda"
    public static void testIdentifyGraphType_Wheel() {
        System.out.println("\n--- Prueba: Tipo de grafo - Rueda ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(true); // Grafo dirigido
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");

        // Aristas del ciclo
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("B", "C", 2);
        graph.insertEdge("C", "A", 3);

        // Aristas desde el centro hacia los vértices del ciclo
        graph.insertEdge("D", "A", 4);
        graph.insertEdge("D", "B", 5);
        graph.insertEdge("D", "C", 6);

        // Aristas desde los vértices del ciclo hacia el centro
        graph.insertEdge("A", "D", 7);
        graph.insertEdge("B", "D", 8);
        graph.insertEdge("C", "D", 9);

        String tipo = graph.identifyGraphType("A");
        System.out.println("Tipo identificado (esperado: Rueda): " + tipo);
    }

    // Prueba para identificar un grafo tipo "Completo"
    public static void testIdentifyGraphType_Complete() {
        System.out.println("\n--- Prueba: Tipo de grafo - Completo ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(true); // Grafo dirigido
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertEdge("A", "B", 1);
        graph.insertEdge("B", "A", 2);
        graph.insertEdge("A", "C", 3);
        graph.insertEdge("C", "A", 4);
        graph.insertEdge("B", "C", 5);
        graph.insertEdge("C", "B", 6);

        String tipo = graph.identifyGraphType("A");
        System.out.println("Tipo identificado (esperado: Completo): " + tipo);
    }

    // Prueba para un grafo que no encaja con ningún tipo conocido
    public static void testIdentifyGraphType_Unknown() {
        System.out.println("\n--- Prueba: Tipo de grafo - Desconocido ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(true); // Grafo dirigido
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertEdge("A", "B", 1);

        String tipo = graph.identifyGraphType("A");
        System.out.println("Tipo identificado (esperado: Desconocido): " + tipo);
    }
}
