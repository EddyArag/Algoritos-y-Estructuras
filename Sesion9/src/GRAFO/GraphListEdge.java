package Sesion9.src.GRAFO;

import Sesion9.src.LISTA.LinkedList;
import Sesion9.src.LISTA.ExceptionEmptyLinkedList;
import Sesion9.src.LISTA.Node;

public class GraphListEdge<E extends Comparable<E>> {
    private LinkedList<Vertex<E>> listVertex;
    private LinkedList<Edge<E>> listEdge;

    public GraphListEdge() {
        this.listVertex = new LinkedList<>();
        this.listEdge = new LinkedList<>();
    }

    public boolean searchVertex(Vertex<E> vertex) throws ExceptionEmptyLinkedList {
        Node<Vertex<E>> node = listVertex.getFirst();
        while (node != null) {
            if (node.getData().equals(vertex)) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    public void insertVertex(E data) throws ExceptionEmptyLinkedList {
        Vertex<E> newVertex = new Vertex<>(data);
        if (!searchVertex(newVertex)) {
            listVertex.addLast(newVertex);
        }
    }

    public boolean searchEdge(Vertex<E> v1, Vertex<E> v2) throws ExceptionEmptyLinkedList {
        Node<Edge<E>> node = listEdge.getFirst();
        while (node != null) {
            Edge<E> edge = node.getData();
            if ((edge.getRefDest().equals(v1) && node.getData().getRefDest().equals(v2)) ||
                (edge.getRefDest().equals(v2) && node.getData().getRefDest().equals(v1))) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    public void insertEdge(E data1, E data2, int weight) throws ExceptionEmptyLinkedList {
        Vertex<E> v1 = getVertex(data1);
        Vertex<E> v2 = getVertex(data2);
        
        if (v1 == null || v2 == null) {
            System.out.println("Uno o ambos vértices no existen");
            return;
        }
        
        if (!searchEdge(v1, v2)) {
            Edge<E> newEdge1 = new Edge<>(v2, weight);
            Edge<E> newEdge2 = new Edge<>(v1, weight);
            v1.listAdj.addLast(newEdge1);
            v2.listAdj.addLast(newEdge2);
            listEdge.addLast(newEdge1);
        }
    }

    private Vertex<E> getVertex(E data) throws ExceptionEmptyLinkedList {
        Node<Vertex<E>> node = listVertex.getFirst();
        while (node != null) {
            if (node.getData().getData().equals(data)) {
                return node.getData();
            }
            node = node.getNext();
        }
        return null;
    }

    public void bfs(E start) throws ExceptionEmptyLinkedList {
        LinkedList<Vertex<E>> queue = new LinkedList<>();
        LinkedList<Vertex<E>> visited = new LinkedList<>();

        Vertex<E> startVertex = getVertex(start);
        if (startVertex == null) return;

        queue.addLast(startVertex);
        visited.addLast(startVertex);

        while (!queue.isEmptyList()) {
            Vertex<E> current = queue.getFirst().getData();
            queue.removeNode(current);
            System.out.println(current.getData());

            Node<Edge<E>> edgeNode = current.listAdj.getFirst();
            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().getRefDest();
                if (visited.search(neighbor) == -1) {
                    visited.addLast(neighbor);
                    queue.addLast(neighbor);
                }
                edgeNode = edgeNode.getNext();
            }
        }
    }

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    Node<Vertex<E>> node = listVertex.getFirst();
    while (node != null) {
        sb.append(node.getData().toString());
        node = node.getNext();
    }
    return sb.toString();
}

}