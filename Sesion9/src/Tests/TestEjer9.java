package Sesion9.src.Tests;

import Sesion9.src.GRAFO.GraphListEdge;

public class TestEjer9 {

    public static void main(String[] args) {
        System.out.println("========== INICIO DE PRUEBAS ==========\n");

        testIsIsomorphic_True();
        testIsIsomorphic_False();
        testIsConnected_True();
        testIsConnected_False();
        testIsPlanar();
        testIsSelfComplementary();

        System.out.println("\n========== FIN DE PRUEBAS ==========");
    }

    // Prueba de isomorfismo: se espera TRUE porque los grafos tienen la misma
    // estructura
    public static void testIsIsomorphic_True() {
        System.out.println("\n--- Prueba: Isomorfismo (caso verdadero) ---");

        GraphListEdge<String, Integer> graph1 = new GraphListEdge<>(false);
        graph1.insertVertex("A");
        graph1.insertVertex("B");
        graph1.insertEdge("A", "B", 1); // Arista con peso 1

        GraphListEdge<String, Integer> graph2 = new GraphListEdge<>(false);
        graph2.insertVertex("C");
        graph2.insertVertex("D");
        graph2.insertEdge("C", "D", 2); // Arista con peso 2

        boolean result = graph1.isIsomorphic(graph2);
        System.out.println("¿Son isomorfos? (esperado: true) -> " + result);
    }

    // Prueba de isomorfismo: se espera FALSE porque uno tiene arista y otro no
    public static void testIsIsomorphic_False() {
        System.out.println("\n--- Prueba: Isomorfismo (caso falso) ---");

        GraphListEdge<String, Integer> graph1 = new GraphListEdge<>(false);
        graph1.insertVertex("A");
        graph1.insertVertex("B");
        graph1.insertEdge("A", "B", 1);

        GraphListEdge<String, Integer> graph2 = new GraphListEdge<>(false);
        graph2.insertVertex("C");
        graph2.insertVertex("D");
        // No se inserta arista

        boolean result = graph1.isIsomorphic(graph2);
        System.out.println("¿Son isomorfos? (esperado: false) -> " + result);
    }

    // Prueba de conectividad: se espera TRUE porque los vértices están conectados
    // por una arista
    public static void testIsConnected_True() {
        System.out.println("\n--- Prueba: Conectividad (caso verdadero) ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(false);
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertEdge("A", "B", 1);

        boolean result = graph.isConnected();
        System.out.println("¿Está conectado? (esperado: true) -> " + result);
    }

    // Prueba de conectividad: se espera FALSE porque no hay arista que conecte los
    // vértices
    public static void testIsConnected_False() {
        System.out.println("\n--- Prueba: Conectividad (caso falso) ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(false);
        graph.insertVertex("A");
        graph.insertVertex("B");
        // No se inserta arista

        boolean result = graph.isConnected();
        System.out.println("¿Está conectado? (esperado: false) -> " + result);
    }

    // Prueba de planitud: sin aristas, se considera planar por implementación
    // simplificada
    public static void testIsPlanar() {
        System.out.println("\n--- Prueba: Planitud del grafo ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(false);
        // No se agregan vértices ni aristas

        boolean result = graph.isPlanar();
        System.out.println("¿Es planar? (esperado: true o según implementación) -> " + result);
    }

    // Prueba de autocomplementariedad: según la implementación simplificada
    public static void testIsSelfComplementary() {
        System.out.println("\n--- Prueba: Grafo autocomplementario ---");

        GraphListEdge<String, Integer> graph = new GraphListEdge<>(false);
        // No se agregan vértices ni aristas

        boolean result = graph.isSelfComplementary();
        System.out.println("¿Es autocomplementario? (esperado: según implementación) -> " + result);
    }
}
