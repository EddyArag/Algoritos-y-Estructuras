package Sesion9.src.Tests;

import Sesion9.src.ExceptionIsEmpty;
import Sesion9.src.LISTA.ExceptionEmptyLinkedList;

public class TestGrafo2 {
    public static void main(String[] args) throws ExceptionEmptyLinkedList{
        // ------------------------------
        // Ejemplo con GRAFO NO DIRIGIDO
        // ------------------------------

        System.out.println("=== GRAFO NO DIRIGIDO ===");

        GraphLink<String> grafoNoDirigido = new GraphLink<>();

        // Insertar vértices
        grafoNoDirigido.insertVertex("A");
        grafoNoDirigido.insertVertex("B");
        grafoNoDirigido.insertVertex("C");
        grafoNoDirigido.insertVertex("D");

        // Insertar aristas
        grafoNoDirigido.insertEdge("A", "B", 5);
        grafoNoDirigido.insertEdge("A", "C", 3);
        grafoNoDirigido.insertEdge("B", "D", 2);
        grafoNoDirigido.insertEdge("C", "D", 4);

        // Mostrar grafo
        printGraph(grafoNoDirigido);

        // Buscar vértices
        System.out.println("Buscar vértice 'A': " + grafoNoDirigido.searchVertex("A"));
        System.out.println("Buscar vértice 'E': " + grafoNoDirigido.searchVertex("E"));

        // Buscar aristas
        System.out.println("Buscar arista A-C: " + grafoNoDirigido.searchEdge("A", "C"));
        System.out.println("Buscar arista B-C: " + grafoNoDirigido.searchEdge("B", "C"));

        // Eliminar arista
        System.out.println("Eliminando arista A-C...");
        grafoNoDirigido.removeEdge("A", "C");
        printGraph(grafoNoDirigido);

        // Eliminar vértice
        System.out.println("Eliminando vértice D...");
        grafoNoDirigido.removeVertex("D");
        printGraph(grafoNoDirigido);

        // ------------------------------
        // Ejemplo con GRAFO DIRIGIDO
        // ------------------------------

        System.out.println("\n=== GRAFO DIRIGIDO ===");

        GraphLink<String> grafoDirigido = new GraphLink<>();

        // Insertar vértices
        grafoDirigido.insertVertex("X");
        grafoDirigido.insertVertex("Y");
        grafoDirigido.insertVertex("Z");

        // Insertar aristas SOLO en un sentido
        grafoDirigido.insertEdge("X", "Y", 10);
        grafoDirigido.insertEdge("Y", "Z", 7);
        grafoDirigido.insertEdge("Z", "X", 4);

        // Mostrar grafo dirigido
        printGraph(grafoDirigido);

        // Buscar aristas
        System.out.println("Buscar arista X-Y: " + grafoDirigido.searchEdge("X", "Y"));
        System.out.println("Buscar arista Y-X: " + grafoDirigido.searchEdge("Y", "X"));

        // Eliminar arista
        System.out.println("Eliminando arista Y-Z...");
        grafoDirigido.removeEdge("Y", "Z");
        printGraph(grafoDirigido);

        // Eliminar vértice
        System.out.println("Eliminando vértice X...");
        grafoDirigido.removeVertex("X");
        printGraph(grafoDirigido);
    }

    // Método para imprimir el grafo actual (sirve para ambos tipos de grafos)
    public static <E extends Comparable<E>> void printGraph(GraphLink<E> graph) throws ExceptionEmptyLinkedList{
        System.out.println("Estado actual del grafo:");
        try {
            for (int i = 0; i < graph.listVertex.length(); i++) {
                Vertex<E> vertex = graph.listVertex.get(i);
                System.out.print(vertex);
            }
        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}

