package Sesion9.src.Tests;

public class TestGrafo {
    public static void main(String[] args) {
        // Probando los métodos identificarTipoGrafo() y mostrarFormasGrafo() en grafo NO DIRIGDO
        GraphLink<String> grafo = new GraphLink<>();

        Vertex<String> A = new Vertex<>("A");
        Vertex<String> B = new Vertex<>("B");
        Vertex<String> C = new Vertex<>("C");
        Vertex<String> D = new Vertex<>("D");

        grafo.listVertex.addLast(A);
        grafo.listVertex.addLast(B);
        grafo.listVertex.addLast(C);
        grafo.listVertex.addLast(D);

        A.listAdj.addLast(new Edge<>(B, 1));
        B.listAdj.addLast(new Edge<>(A, 1));

        B.listAdj.addLast(new Edge<>(C, 1));
        C.listAdj.addLast(new Edge<>(B, 1));

        C.listAdj.addLast(new Edge<>(D, 1));
        D.listAdj.addLast(new Edge<>(C, 1));

        System.out.println("==== IDENTIFICAR TIPO DE GRAFO ====");
        grafo.identificarTipoGrafo();

        System.out.println("\n==== MOSTRAR FORMAS DEL GRAFO ====");
        grafo.mostrarFormasGrafo();

        // Probando los métodos identificarTipoGrafo() y mostrarFormasGrafo() en grafo DIRIGDO
        GraphLink<String> grafo2 = new GraphLink<>();

        Vertex<String> A2 = new Vertex<>("A");
        Vertex<String> B2 = new Vertex<>("B");
        Vertex<String> C2 = new Vertex<>("C");

        grafo2.listVertex.addLast(A2);
        grafo2.listVertex.addLast(B2);
        grafo2.listVertex.addLast(C2);

        A2.listAdj.addLast(new Edge<>(B2, 1));
        B2.listAdj.addLast(new Edge<>(C2, 1));

        System.out.println("==== IDENTIFICAR GRAFO DIRIGIDO ====");
        grafo2.identificarTipoGrafoDirigido();

        System.out.println("\n==== FORMAS GRAFO DIRIGIDO ====");
        grafo2.mostrarFormasGrafoDirigido();

        /*GraphLink<String> grafo = new GraphLink<>();

        // Crear vértices
        Vertex<String> A = new Vertex<>("A"); // Centro
        Vertex<String> B = new Vertex<>("B");
        Vertex<String> C = new Vertex<>("C");
        Vertex<String> D = new Vertex<>("D");

        // Agregar vértices al grafo
        grafo.listVertex.addLast(A);
        grafo.listVertex.addLast(B);
        grafo.listVertex.addLast(C);
        grafo.listVertex.addLast(D);

        // Aristas del centro A hacia todos
        A.listAdj.addLast(new Edge<>(B, 1));
        A.listAdj.addLast(new Edge<>(C, 1));
        A.listAdj.addLast(new Edge<>(D, 1));

        // Aristas del ciclo
        B.listAdj.addLast(new Edge<>(C, 1));
        C.listAdj.addLast(new Edge<>(D, 1));
        D.listAdj.addLast(new Edge<>(B, 1));

        // Llamar a métodos
        System.out.println("==== IDENTIFICAR GRAFO DIRIGIDO ====");
        grafo.identificarTipoGrafoDirigido();

        System.out.println("\n==== FORMAS GRAFO DIRIGIDO ====");
        grafo.mostrarFormasGrafoDirigido();*/
    }
}

