package Sesion9.src.GRAFO;

public class TestGrafo {
    public static void main(String[] args) {
        // Probando los métodos identificarTipoGrafo() y mostrarFormasGrafo()
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
    }
}

