package Sesion9.src.GRAFO;

import Sesion9.src.LISTA.ExceptionEmptyLinkedList;
import Sesion9.src.LISTA.LinkedList;
import Sesion9.src.LISTA.Node;
import Sesion9.src.PILA.ExceptionIsEmpty;
import Sesion9.src.PILA.Stack;
import Sesion9.src.PILA.StackArray;

public class GraphLink<E extends Comparable<E>> {
    protected LinkedList<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new LinkedList<>();
    }

    // Métodos básicos existentes (sin cambios)
    public boolean searchVertex(Vertex<E> vertex) throws ExceptionEmptyLinkedList {
        return listVertex.search(vertex) != -1;
    }

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

    // Métodos modificados/mejorados
    public void insertEdgeWeight(E verOri, E verDes, int weight) throws ExceptionEmptyLinkedList {
        Vertex<E> origin = new Vertex<>(verOri);
        Vertex<E> destination = new Vertex<>(verDes);

        if (!searchVertex(origin) || !searchVertex(destination)) {
            System.out.println("Uno o ambos vértices no existen");
            return;
        }

        // Insertar en ambas direcciones (grafo no dirigido)
        insertDirectedEdge(origin, destination, weight);
        insertDirectedEdge(destination, origin, weight);
    }

    private void insertDirectedEdge(Vertex<E> from, Vertex<E> to, int weight) throws ExceptionEmptyLinkedList {
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            if (current.getData().equals(from)) {
                Edge<E> newEdge = new Edge<>(to, weight);
                if (current.getData().listAdj.search(newEdge) == -1) {
                    current.getData().listAdj.addLast(newEdge);
                }
                break;
            }
            current = current.getNext();
        }
    }

    // Implementación completa de Dijkstra
    public ArrayList<Vertex<E>> shortPath(Vertex<E> start, Vertex<E> end) throws ExceptionEmptyLinkedList {
        if (!searchVertex(start) || !searchVertex(end)) {
            return new ArrayList<>();
        }

        // Estructuras para Dijkstra
        LinkedList<Vertex<E>> vertices = getAllVertices();
        LinkedList<Integer> distances = new LinkedList<>();
        LinkedList<Vertex<E>> predecessors = new LinkedList<>();
        LinkedList<Vertex<E>> unvisited = new LinkedList<>();

        // Inicialización
        Node<Vertex<E>> current = vertices.getFirst();
        while (current != null) {
            distances.addLast(Integer.MAX_VALUE);
            predecessors.addLast(null);
            unvisited.addLast(current.getData());
            current = current.getNext();
        }
        setDistance(distances, vertices, start, 0);

        while (!unvisited.isEmptyList()) {
            Vertex<E> u = getVertexWithMinDistance(unvisited, distances, vertices);
            unvisited.removeNode(u);

            // Para cada vecino de u
            Node<Edge<E>> edgeNode = getVertexNode(u).getData().listAdj.getFirst();
            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().getRefDest();
                int alt = getDistance(distances, vertices, u) + edgeNode.getData().getWeight();
                if (alt < getDistance(distances, vertices, neighbor)) {
                    setDistance(distances, vertices, neighbor, alt);
                    setPredecessor(predecessors, vertices, neighbor, u);
                }
                edgeNode = edgeNode.getNext();
            }
        }

        // Reconstruir camino
        return reconstructPath(predecessors, vertices, start, end);
    }

    // Métodos auxiliares para Dijkstra
    private LinkedList<Vertex<E>> getAllVertices() throws ExceptionEmptyLinkedList {
        LinkedList<Vertex<E>> vertices = new LinkedList<>();
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            vertices.addLast(current.getData());
            current = current.getNext();
        }
        return vertices;
    }

    private int getDistance(LinkedList<Integer> distances, LinkedList<Vertex<E>> vertices, Vertex<E> v) throws ExceptionEmptyLinkedList {
        int index = vertices.search(v);
        return index != -1 ? distances.get(index) : Integer.MAX_VALUE;
    }

    private void setDistance(LinkedList<Integer> distances, LinkedList<Vertex<E>> vertices, Vertex<E> v, int value) throws ExceptionEmptyLinkedList {
        int index = vertices.search(v);
        if (index != -1) {
            distances.set(index, value);
        }
    }

    private void setPredecessor(LinkedList<Vertex<E>> predecessors, LinkedList<Vertex<E>> vertices, Vertex<E> v, Vertex<E> pred) throws ExceptionEmptyLinkedList {
        int index = vertices.search(v);
        if (index != -1) {
            predecessors.set(index, pred);
        }
    }

    private Vertex<E> getVertexWithMinDistance(LinkedList<Vertex<E>> unvisited, LinkedList<Integer> distances, LinkedList<Vertex<E>> vertices) throws ExceptionEmptyLinkedList {
        Vertex<E> minVertex = null;
        int minDistance = Integer.MAX_VALUE;
        
        Node<Vertex<E>> current = unvisited.getFirst();
        while (current != null) {
            int currentDist = getDistance(distances, vertices, current.getData());
            if (currentDist < minDistance) {
                minDistance = currentDist;
                minVertex = current.getData();
            }
            current = current.getNext();
        }
        return minVertex;
    }

    private ArrayList<Vertex<E>> reconstructPath(LinkedList<Vertex<E>> predecessors, LinkedList<Vertex<E>> vertices, Vertex<E> start, Vertex<E> end) throws ExceptionEmptyLinkedList {
        ArrayList<Vertex<E>> path = new ArrayList<>();
        Vertex<E> step = end;
        
        if (getPredecessor(predecessors, vertices, end) == null && !end.equals(start)) {
            return path; // No hay camino
        }
        
        while (step != null) {
            path.add(0, step);
            step = getPredecessor(predecessors, vertices, step);
        }
        return path;
    }

    private Vertex<E> getPredecessor(LinkedList<Vertex<E>> predecessors, LinkedList<Vertex<E>> vertices, Vertex<E> v) throws ExceptionEmptyLinkedList {
        int index = vertices.search(v);
        return index != -1 ? predecessors.get(index) : null;
    }

    private Node<Vertex<E>> getVertexNode(Vertex<E> v) throws ExceptionEmptyLinkedList {
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            if (current.getData().equals(v)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    // Implementación de isConexo() mejorada
    public boolean isConexo() throws ExceptionEmptyLinkedList {
        if (listVertex.isEmptyList()) {
            return true;
        }

        Vertex<E> start = listVertex.getFirst().getData();
        LinkedList<Vertex<E>> visited = new LinkedList<>();
        bfs(start, visited);

        // Verificar si todos los vértices fueron visitados
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            if (visited.search(current.getData()) == -1) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    private void bfs(Vertex<E> start, LinkedList<Vertex<E>> visited) throws ExceptionEmptyLinkedList {
        LinkedList<Vertex<E>> queue = new LinkedList<>();
        queue.addLast(start);
        visited.addLast(start);

        while (!queue.isEmptyList()) {
            Vertex<E> current = queue.getFirst().getData();
            queue.removeNode(current);

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

    // Implementación de Dijsktra() usando StackArray
    public StackArray<Vertex<E>> Dijsktra(Vertex<E> start, Vertex<E> end) throws ExceptionEmptyLinkedList {
        ArrayList<Vertex<E>> path = shortPath(start, end);
        StackArray<Vertex<E>> stack = new StackArray<>(path.size());
        
        for (int i = path.size() - 1; i >= 0; i--) {
            stack.push(path.get(i));
        }
        return stack;
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

    LinkedList<Vertex<E>> queue = new LinkedList<>();
    LinkedList<Vertex<E>> visited = new LinkedList<>();
    ArrayList<Vertex<E>> padres = new ArrayList<>();
    ArrayList<Vertex<E>> hijos = new ArrayList<>();

    queue.addLast(start);
    visited.addLast(start);

    boolean found = false;

    while (!queue.isEmptyList() && !found) {
        Vertex<E> current = queue.getFirst().getData();
        queue.removeNode(current);

        if (current.equals(end)) {
            found = true;
            break;
        }

        Node<Edge<E>> edgeNode = current.listAdj.getFirst();
        while (edgeNode != null) {
            Vertex<E> neighbor = edgeNode.getData().getRefDest();
            if (visited.search(neighbor) == -1) {
                visited.addLast(neighbor);
                queue.addLast(neighbor);
                hijos.add(neighbor);
                padres.add(current);
            }
            edgeNode = edgeNode.getNext();
        }
    }

    if (found) {
        Vertex<E> node = end;
        while (node != null) {
            path.add(0, node);
            node = findParent(padres, hijos, node);
        }
    }

    return path;
}

private Vertex<E> findParent(ArrayList<Vertex<E>> padres, ArrayList<Vertex<E>> hijos, Vertex<E> node) {
    for (int i = 0; i < hijos.size(); i++) {
        if (hijos.get(i).equals(node)) {
            return padres.get(i);
        }
    }
    return null;
}

   
    public String toString() {
        return this.listVertex.toString();
    }
}
