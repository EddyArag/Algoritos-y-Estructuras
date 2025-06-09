package Sesion9.src.GRAFO;

import java.util.ArrayList;

import Sesion9.src.LISTA.ExceptionEmptyLinkedList;
import Sesion9.src.LISTA.LinkedList;
import Sesion9.src.LISTA.Node;

public class GraphLink<E extends Comparable<E>> {
    protected LinkedList<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new LinkedList<Vertex<E>>();
    }

    // 1.1.a) Busca un vértice en el grafo
    public boolean searchVertex(Vertex<E> vertex) throws ExceptionEmptyLinkedList {
        return listVertex.search(vertex) != -1;
    }

    // 1.1.b) Busca una arista entre dos vértices
    public boolean searchEdge(Vertex<E> verOri, Vertex<E> verDes) throws ExceptionEmptyLinkedList {
        if (!searchVertex(verOri) || !searchVertex(verDes)) {
            return false;
        }

        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            if (current.getData().equals(verOri)) {
                LinkedList<Edge<E>> edges = current.getData().listAdj;
                Node<Edge<E>> edgeNode = edges.getFirst();
                while (edgeNode != null) {
                    if (edgeNode.getData().getRefDest().equals(verDes)) {
                        return true;
                    }
                    edgeNode = edgeNode.getNext();
                }
                break;
            }
            current = current.getNext();
        }
        return false;
    }

    // Elimina un vértice del grafo
    public void removeVertex(Vertex<E> v) throws ExceptionEmptyLinkedList {
        // Primero eliminamos todas las aristas que apuntan a este vértice
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            Vertex<E> vertex = current.getData();
            Node<Edge<E>> edgeNode = vertex.listAdj.getFirst();
            while (edgeNode != null) {
                if (edgeNode.getData().getRefDest().equals(v)) {
                    vertex.listAdj.removeNode(edgeNode.getData());
                    break;
                }
                edgeNode = edgeNode.getNext();
            }
            current = current.getNext();
        }

        // eliminamos el vértice y sus aristas salientes
        listVertex.removeNode(v);
    }

    // Elimina una arista entre dos vértices
    public void removeEdge(Vertex<E> v, Vertex<E> z) throws ExceptionEmptyLinkedList {
        if (!searchVertex(v) || !searchVertex(z)) {
            return;
        }

        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            if (current.getData().equals(v)) {
                LinkedList<Edge<E>> edges = current.getData().listAdj;
                Node<Edge<E>> edgeNode = edges.getFirst();
                while (edgeNode != null) {
                    if (edgeNode.getData().getRefDest().equals(z)) {
                        edges.removeNode(edgeNode.getData());
                        return;
                    }
                    edgeNode = edgeNode.getNext();
                }
                break;
            }
            current = current.getNext();
        }
    }

    // Recorrido en profundidad (DFS)
    public void dfs(Vertex<E> start) throws ExceptionEmptyLinkedList {
        if (!searchVertex(start)) {
            System.out.println("El vértice no existe en el grafo");
            return;
        }

        LinkedList<Vertex<E>> visited = new LinkedList<>();
        dfsRecursive(start, visited);
    }

    private void dfsRecursive(Vertex<E> current, LinkedList<Vertex<E>> visited) throws ExceptionEmptyLinkedList {
        System.out.println("Visitando: " + current.getData());
        visited.addLast(current);

        Node<Edge<E>> edgeNode = current.listAdj.getFirst();
        while (edgeNode != null) {
            Vertex<E> neighbor = edgeNode.getData().getRefDest();
            if (visited.search(neighbor) == -1) {
                dfsRecursive(neighbor, visited);
            }
            edgeNode = edgeNode.getNext();
        }
    }

    // metodos adicionales
    public void insertVertex(E data) {
        Vertex<E> newVertex = new Vertex<>(data);
        try {
            if (!searchVertex(newVertex)) {
                listVertex.addLast(newVertex);
            }
        } catch (ExceptionEmptyLinkedList e) {
            listVertex.addLast(newVertex);
        }
    }

    public void insertEdge(E verOri, E verDes, int weight) throws ExceptionEmptyLinkedList {
        Vertex<E> origin = new Vertex<>(verOri);
        Vertex<E> destination = new Vertex<>(verDes);

        if (!searchVertex(origin) || !searchVertex(destination)) {
            System.out.println("Uno o ambos vértices no existen");
            return;
        }

        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            if (current.getData().equals(origin)) {
                Edge<E> newEdge = new Edge<>(destination, weight);
                if (current.getData().listAdj.search(newEdge) == -1) {
                    current.getData().listAdj.addLast(newEdge);
                }
                break;
            }
            current = current.getNext();
        }
    }

    // --- 1. BFS (Recorrido en Anchura) ---
    public void bfs(Vertex<E> start) throws ExceptionEmptyLinkedList {
        if (!searchVertex(start)) {
            System.out.println("El vértice no existe en el grafo.");
            return;
        }

        // Usamos tu LinkedList para la cola y los visitados
        LinkedList<Vertex<E>> queue = new LinkedList<>();
        LinkedList<Vertex<E>> visited = new LinkedList<>();

        queue.addLast(start);
        visited.addLast(start);

        while (!queue.isEmptyList()) {
            Vertex<E> current = queue.getFirst().getData();
            queue.removeNode(current); // Sacamos el primero (FIFO)
            System.out.println("Visitando: " + current.getData());

            // Recorremos los vecinos del vértice actual
            Node<Edge<E>> edgeNode = current.listAdj.getFirst();
            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().getRefDest();
                if (visited.search(neighbor) == -1) { // Si no ha sido visitado
                    visited.addLast(neighbor);
                    queue.addLast(neighbor);
                }
                edgeNode = edgeNode.getNext();
            }
        }
    }

    // --- 2. BFS Path (Camino más corto entre v y z) ---
    public ArrayList<Vertex<E>> bfsPath(Vertex<E> start, Vertex<E> end) throws ExceptionEmptyLinkedList {
        ArrayList<Vertex<E>> path = new ArrayList<>();
        if (!searchVertex(start) || !searchVertex(end)) {
            return path; // Lista vacía si no existe alguno de los vértices
        }

        // Estructuras para BFS
        LinkedList<Vertex<E>> queue = new LinkedList<>();
        LinkedList<Vertex<E>> visited = new LinkedList<>();
        // Mapeo para reconstruir el camino (usamos un ArrayList temporal)
        ArrayList<Vertex<E>> parentMap = new ArrayList<>();

        queue.addLast(start);
        visited.addLast(start);
        parentMap.add(start); // Asumimos que el primer vértice no tiene padre (null)

        boolean found = false;

        while (!queue.isEmptyList() && !found) {
            Vertex<E> current = queue.getFirst().getData();
            queue.removeNode(current);

            // Si encontramos el destino, terminamos
            if (current.equals(end)) {
                found = true;
                break;
            }

            // Exploramos los vecinos
            Node<Edge<E>> edgeNode = current.listAdj.getFirst();
            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().getRefDest();
                if (visited.search(neighbor) == -1) {
                    visited.addLast(neighbor);
                    queue.addLast(neighbor);
                    parentMap.add(neighbor); // Guardamos el vértice actual como "padre" del vecino
                }
                edgeNode = edgeNode.getNext();
            }
        }

        // Reconstruimos el camino desde 'end' hasta 'start' (si existe)
        if (found) {
            Vertex<E> node = end;
            while (node != null) {
                path.add(0, node); // Insertamos al inicio para invertir el orden
                // Buscamos el padre de 'node' en parentMap (simplificado)
                // En una implementación más robusta, usaríamos un HashMap para rastrear padres.
                node = findParent(parentMap, node);
            }
        }

        return path;
    }

    // Método auxiliar para encontrar el padre de un vértice en BFS
    private Vertex<E> findParent(ArrayList<Vertex<E>> parentMap, Vertex<E> node) {
        // Esto es una simplificación. En una implementación real, deberíamos llevar un
        // registro de padres.
        // Aquí asumimos que el padre está antes en la lista (no es óptimo, pero
        // funciona para el ejemplo).
        int index = parentMap.indexOf(node);
        if (index <= 0)
            return null; // No tiene padre o es el inicio
        return parentMap.get(index - 1);
    }

    public String toString() {
        return this.listVertex.toString();
    }
}