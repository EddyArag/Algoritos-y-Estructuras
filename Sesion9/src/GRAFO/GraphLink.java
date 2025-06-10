package Sesion9.src.GRAFO;

import Sesion9.src.LISTA.ExceptionEmptyLinkedList;
import Sesion9.src.LISTA.LinkedList;
import Sesion9.src.LISTA.Node;
import Sesion9.src.PILA.ExceptionIsEmpty;
import Sesion9.src.PILA.StackArray;
import java.util.ArrayList;

public class GraphLink<E extends Comparable<E>> {
    protected LinkedList<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new LinkedList<>();
    }

    // Métodos de búsqueda de vértices y aristas para grafos NO DIRIGIDOS y DIRIGIDOS
    public boolean searchVertex(E v) {
        for (int i = 0; i < listVertex.length(); i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(v) == 0) {
                    return true;
                }
            } catch (ExceptionEmptyLinkedList e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public boolean searchEdge(E v, E z) {
        for (int i = 0; i < listVertex.length(); i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(v) == 0) {
                    LinkedList<Edge<E>> adjList = vertex.listAdj;
                    for (int j = 0; j < adjList.length(); j++) {
                        Edge<E> edge = adjList.get(j);
                        if (edge.getRefDest().getData().compareTo(z) == 0) {
                            return true;
                        }
                    }
                }
            } catch (ExceptionEmptyLinkedList e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // Métodos para insertar vértices y aristas en grafo NO DIRIGIDO
    public void insertVertex(E data) {
        if (!searchVertex(data)) {
            Vertex<E> newVertex = new Vertex<>(data);
            listVertex.addLast(newVertex);
        }
    }
    public void insertEdge(E verOri, E verDest, int weight) {
        Vertex<E> origin = null;
        Vertex<E> dest = null;

        try {
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(verOri) == 0) {
                    origin = vertex;
                }
                if (vertex.getData().compareTo(verDest) == 0) {
                    dest = vertex;
                }
            }

            if (origin != null && dest != null) {
                origin.listAdj.addLast(new Edge<>(dest, weight));
                dest.listAdj.addLast(new Edge<>(origin, weight));
            }
        } catch (ExceptionEmptyLinkedList e) {
            e.printStackTrace();
        }
    }

    // Metodos para remover vértices y aristas en grafo NO DIRIGIDO
    public void removeVertex(E v) {
        try {
            Vertex<E> toRemove = null;

            // Buscar el vértice que queremos eliminar
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(v) == 0) {
                    toRemove = vertex;
                    break;
                }
            }

            if (toRemove != null) {
                // Primero eliminar todas las aristas que apuntan a este vértice
                for (int i = 0; i < listVertex.length(); i++) {
                    Vertex<E> vertex = listVertex.get(i);
                    if (vertex != toRemove) { // No limpiar sus propias aristas aún
                        for (int j = 0; j < vertex.listAdj.length(); j++) {
                            Edge<E> edge = vertex.listAdj.get(j);
                            if (edge.getRefDest().compareTo(toRemove) == 0) {
                                vertex.listAdj.removeNode(edge);
                                break;
                            }
                        }
                    }
                }

                // Ahora sí, eliminar el vértice de la lista de vértices
                listVertex.removeNode(toRemove);
            }
        } catch (ExceptionEmptyLinkedList e) {
            e.printStackTrace();
        }
    }
    public void removeEdge(E v, E z) {
        try {
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);

                // Si el vértice es v, eliminar arista hacia z
                if (vertex.getData().compareTo(v) == 0) {
                    for (int j = 0; j < vertex.listAdj.length(); j++) {
                        Edge<E> edge = vertex.listAdj.get(j);
                        if (edge.getRefDest().getData().compareTo(z) == 0) {
                            vertex.listAdj.removeNode(edge);
                            break;
                        }
                    }
                }

                // Si el vértice es z, eliminar arista hacia v
                if (vertex.getData().compareTo(z) == 0) {
                    for (int j = 0; j < vertex.listAdj.length(); j++) {
                        Edge<E> edge = vertex.listAdj.get(j);
                        if (edge.getRefDest().getData().compareTo(v) == 0) {
                            vertex.listAdj.removeNode(edge);
                            break;
                        }
                    }
                }
            }
        } catch (ExceptionEmptyLinkedList e) {
            e.printStackTrace();
        }
    }

    // Métodos para insertar aristas en grafo DIRIGIDO
    public void insertEdgeDirigido(E verOri, E verDest, int weight) {
        Vertex<E> origin = null;
        Vertex<E> dest = null;

        try {
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(verOri) == 0) {
                    origin = vertex;
                }
                if (vertex.getData().compareTo(verDest) == 0) {
                    dest = vertex;
                }
            }

            if (origin != null && dest != null) {
                origin.listAdj.addLast(new Edge<>(dest, weight));
            }
        } catch (ExceptionEmptyLinkedList e) {
            e.printStackTrace();
        }
    }

    // Métodos para remover vértices y aristas en grafo DIRIGIDO
    public void removeVertexDirigido(E v) {
        try {
            Vertex<E> toRemove = null;

            // Buscar el vértice que queremos eliminar
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(v) == 0) {
                    toRemove = vertex;
                    break;
                }
            }

            if (toRemove != null) {
                // Eliminar TODAS las aristas ENTRANTES hacia este vértice
                for (int i = 0; i < listVertex.length(); i++) {
                    Vertex<E> vertex = listVertex.get(i);
                    if (vertex != toRemove) {
                        for (int j = 0; j < vertex.listAdj.length(); j++) {
                            Edge<E> edge = vertex.listAdj.get(j);
                            if (edge.getRefDest().compareTo(toRemove) == 0) {
                                vertex.listAdj.removeNode(edge);
                                break;
                            }
                        }
                    }
                }

                toRemove.listAdj.destroyList();
                listVertex.removeNode(toRemove);
            }
        } catch (ExceptionEmptyLinkedList e) {
            e.printStackTrace();
        }
    }
    public void removeEdgeDirigido(E v, E z) {
        try {
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);

                // SOLO eliminar arista que va de v → z
                if (vertex.getData().compareTo(v) == 0) {
                    for (int j = 0; j < vertex.listAdj.length(); j++) {
                        Edge<E> edge = vertex.listAdj.get(j);
                        if (edge.getRefDest().getData().compareTo(z) == 0) {
                            vertex.listAdj.removeNode(edge);
                            break;
                        }
                    }
                }
            }
        } catch (ExceptionEmptyLinkedList e) {
            e.printStackTrace();
        }
    }

    // Método para búsqueda en profundidad (dfs) en grafo NO DIRIGIDO y DIRIGIDO
    public void dfs(E data) {
        try {
            Vertex<E> startVertex = null;
            // Buscar el vértice de inicio
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(data) == 0) {
                    startVertex = vertex;
                    break;
                }
            }
            if (startVertex == null) {
                System.out.println("El vértice de inicio no existe.");
                return;
            }
            // Inicializar pila
            StackArray<Vertex<E>> stack = new StackArray<>(listVertex.length());

            for (int i = 0; i < listVertex.length(); i++) {
                listVertex.get(i).setVisited(false);
            }

            stack.push(startVertex);
            System.out.print("Recorrido DFS: ");
            while (!stack.isEmpty()) {
                Vertex<E> current = stack.pop();
                if (!current.isVisited()) {
                    current.setVisited(true);
                    System.out.print(current.getData() + " ");
                    for (int i = 0; i < current.listAdj.length(); i++) {
                        Edge<E> edge = current.listAdj.get(i);
                        Vertex<E> neighbor = edge.getRefDest();

                        if (!neighbor.isVisited()) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
            System.out.println();
        } catch (ExceptionEmptyLinkedList | ExceptionIsEmpty e) {
            e.printStackTrace();
        }
    }


    public void insertEdgeWeight(E verOri, E verDes, int weight) throws ExceptionEmptyLinkedList {
        Vertex<E> origin = new Vertex<>(verOri);
        Vertex<E> destination = new Vertex<>(verDes);

        if (!searchVertex(origin) || !searchVertex(destination)) {
            System.out.println("Uno o ambos vértices no existen");
            return;
        }

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

    public ArrayList<Vertex<E>> shortPath(Vertex<E> start, Vertex<E> end) throws ExceptionEmptyLinkedList {
        if (!searchVertex(start) || !searchVertex(end)) {
            return new ArrayList<>();
        }

        LinkedList<Vertex<E>> vertices = getAllVertices();
        LinkedList<Integer> distances = new LinkedList<>();
        LinkedList<Vertex<E>> predecessors = new LinkedList<>();
        LinkedList<Vertex<E>> unvisited = new LinkedList<>();

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

        return reconstructPath(predecessors, vertices, start, end);
    }

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
            return path;
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

    public boolean isConexo() throws ExceptionEmptyLinkedList {
        if (listVertex.isEmptyList()) {
            return true;
        }

        Vertex<E> start = listVertex.getFirst().getData();
        LinkedList<Vertex<E>> visited = new LinkedList<>();
        bfs(start, visited);

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

    public StackArray<Vertex<E>> Dijsktra(Vertex<E> start, Vertex<E> end) throws ExceptionEmptyLinkedList {
        ArrayList<Vertex<E>> path = shortPath(start, end);
        StackArray<Vertex<E>> stack = new StackArray<>(path.size());
        
        for (int i = path.size() - 1; i >= 0; i--) {
            stack.push(path.get(i));
        }
        return stack;
    }

    public void removeVertex(Vertex<E> v) throws ExceptionEmptyLinkedList {
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
        listVertex.removeNode(v);
    }

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

    public void bfs(Vertex<E> start) throws ExceptionEmptyLinkedList {
        if (!searchVertex(start)) {
            System.out.println("El vértice no existe en el grafo.");
            return;
        }

        LinkedList<Vertex<E>> queue = new LinkedList<>();
        LinkedList<Vertex<E>> visited = new LinkedList<>();

        queue.addLast(start);
        visited.addLast(start);

        while (!queue.isEmptyList()) {
            Vertex<E> current = queue.getFirst().getData();
            queue.removeNode(current);
            System.out.println("Visitando: " + current.getData());

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

    public ArrayList<Vertex<E>> bfsPath(Vertex<E> start, Vertex<E> end) throws ExceptionEmptyLinkedList {
        ArrayList<Vertex<E>> path = new ArrayList<>();
        if (!searchVertex(start) || !searchVertex(end)) {
            return path;
        }

        LinkedList<Vertex<E>> queue = new LinkedList<>();
        LinkedList<Vertex<E>> visited = new LinkedList<>();
        LinkedList<Vertex<E>> parents = new LinkedList<>();

        queue.addLast(start);
        visited.addLast(start);
        parents.addLast(null);

        boolean found = false;
        int endIndex = -1;

        while (!queue.isEmptyList() && !found) {
            Vertex<E> current = queue.getFirst().getData();
            queue.removeNode(current);

            if (current.equals(end)) {
                found = true;
                endIndex = visited.search(current);
                break;
            }

            Node<Edge<E>> edgeNode = current.listAdj.getFirst();
            while (edgeNode != null) {
                Vertex<E> neighbor = edgeNode.getData().getRefDest();
                if (visited.search(neighbor) == -1) {
                    visited.addLast(neighbor);
                    queue.addLast(neighbor);
                    parents.addLast(current);
                }
                edgeNode = edgeNode.getNext();
            }
        }

        if (found) {
            Vertex<E> node = end;
            while (node != null) {
                path.add(0, node);
                int nodeIndex = visited.search(node);
                node = parents.get(nodeIndex);
            }
        }

        return path;
    }

    // Método que identifica el tipo de grafo NO DIRIGIDO según el grado de los vértices
    public void identificarTipoGrafo() {
        int n = listVertex.length();

        // Mostrar los grados de todos los vértices
        System.out.println("Grado de cada nodo:");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                int grado = vertex.listAdj.length();
                System.out.println("Nodo " + vertex.getData() + ": G" + grado);
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Revisar tipo de grafo por los grados de los vértices
        int countGrado1 = 0;
        int countGrado2 = 0;
        int countGrado3 = 0;
        int countGradoN_1 = 0;

        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                int grado = vertex.listAdj.length();

                if (grado == 1) {
                    countGrado1++;
                } else if (grado == 2) {
                    countGrado2++;
                } else if (grado == 3) {
                    countGrado3++;
                } else if (grado == n - 1) {
                    countGradoN_1++;
                }

            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Verificar tipo de grafo
        if (countGradoN_1 == n) {
            System.out.println("El grafo es COMPLETO: K" + n);
        } else if (countGrado1 == 2 && countGrado2 == n - 2) {
            System.out.println("El grafo es un CAMINO: P" + n);
        } else if (countGrado2 == n) {
            System.out.println("El grafo es un CICLO: C" + n);
        } else if (countGradoN_1 == 1 && countGrado3 == n - 1) {
            System.out.println("El grafo es una RUEDA: W" + n);
        } else {
            System.out.println("El grafo no corresponde a un tipo definido (Camino, Ciclo, Rueda, Completo).");
        }
    }

    // Método que muestra un grafo NO DIRIGIDO de 3 formas diferentes: Formal, Lista de Adyacencia, Matriz de Adyacencia
    public void mostrarFormasGrafo() {
        int n = listVertex.length();

        // Representación Formal
        System.out.println("\n--- Representación FORMAL ---");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                System.out.print("Nodo " + vertex.getData() + " conectado a: ");
                for (int j = 0; j < vertex.listAdj.length(); j++) {
                    Edge<E> edge = vertex.listAdj.get(j);
                    System.out.print(edge.getRefDest().getData() + " ");
                }
                System.out.println();
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Representación de Lista de Adyacencia
        System.out.println("\n--- Lista de ADYACENCIAS ---");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                System.out.print(vertex.getData() + " --> ");
                System.out.println(vertex.listAdj.toString());
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Representación de la Matriz de Adyacencia
        System.out.println("\n--- MATRIZ de ADYACENCIA ---");
        int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                for (int j = 0; j < vertex.listAdj.length(); j++) {
                    Edge<E> edge = vertex.listAdj.get(j);
                    Vertex<E> dest = edge.getRefDest();
                    int indexDest = listVertex.search(dest);
                    if (indexDest != -1) {
                        matriz[i][indexDest] = 1;
                    }
                }
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Mostrar la matriz
        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                System.out.print(vertex.getData() + " ");
            } catch (ExceptionEmptyLinkedList e) {
                System.out.print("? ");
            }
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                System.out.print(vertex.getData() + " ");
                for (int j = 0; j < n; j++) {
                    System.out.print(" " + matriz[i][j] + " ");
                }
                System.out.println();
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Método que identifica el tipo de grafo DIRIGIDO según el grado de los vértices
    public void identificarTipoGrafoDirigido() {
        int n = listVertex.length();
        int[] entrada = new int[n];
        int[] salida = new int[n];

        // Contar grados de entrada y salida
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> origen = listVertex.get(i);
                salida[i] = origen.listAdj.length();
                for (int j = 0; j < origen.listAdj.length(); j++) {
                    Edge<E> e = origen.listAdj.get(j);
                    Vertex<E> destino = e.getRefDest();
                    int indexDestino = listVertex.search(destino);
                    if (indexDestino != -1) {
                        entrada[indexDestino]++;
                    }
                }
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n--- Grados de los nodos (Dirigido) ---");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> v = listVertex.get(i);
                System.out.println("Nodo " + v.getData() + ": Entrada=" + entrada[i] + ", Salida=" + salida[i]);
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Determinar tipo
        boolean esCamino = true;
        boolean esCiclo = true;
        boolean esCompleto = true;
        boolean esRueda = false;

        int cuentaCentroRueda = 0;
        int cuentaNodosCicloRueda = 0;

        for (int i = 0; i < n; i++) {
            // Camino
            if (entrada[i] > 1 || salida[i] > 1) {
                esCamino = false;
            }

            // Ciclo
            if (entrada[i] != 1 || salida[i] != 1) {
                esCiclo = false;
            }

            // Completo
            if (entrada[i] != n - 1 || salida[i] != n - 1) {
                esCompleto = false;
            }

            // Rueda
            if (salida[i] == n - 1 && entrada[i] == 0) {
                cuentaCentroRueda++;
            } else if (entrada[i] == 1 && salida[i] == 1) {
                cuentaNodosCicloRueda++;
            }
        }

        // Condición de rueda
        if (cuentaCentroRueda == 1 && cuentaNodosCicloRueda == n - 1) {
            esRueda = true;
        }

        // Mostrar resultado
        if (esCompleto) {
            System.out.println("El grafo dirigido es COMPLETO (K" + n + ")");
        } else if (esRueda) {
            System.out.println("El grafo dirigido es una RUEDA (W" + n + ")");
        } else if (esCiclo) {
            System.out.println("El grafo dirigido es un CICLO (C" + n + ")");
        } else if (esCamino) {
            System.out.println("El grafo dirigido es un CAMINO (P" + n + ")");
        } else {
            System.out.println("El grafo dirigido no es un camino, ciclo, rueda ni completo.");
        }
    }

    // Método que muestra un grafo DIRIGIDO de 3 formas diferentes: Formal, Lista de Adyacencia, Matriz de Adyacencia 
    public void mostrarFormasGrafoDirigido() {
        int n = listVertex.length();

        System.out.println("\n--- Representación FORMAL (Dirigido) ---");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> v = listVertex.get(i);
                for (int j = 0; j < v.listAdj.length(); j++) {
                    Edge<E> e = v.listAdj.get(j);
                    System.out.println("Arista: " + v.getData() + " → " + e.getRefDest().getData());
                }
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n--- Lista de ADYACENCIAS (Dirigido) ---");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> v = listVertex.get(i);
                System.out.print(v.getData() + " --> ");
                System.out.println(v.listAdj.toString());
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n--- MATRIZ de ADYACENCIA (Dirigido) ---");
        int[][] matriz = new int[n][n];
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> origen = listVertex.get(i);
                for (int j = 0; j < origen.listAdj.length(); j++) {
                    Edge<E> e = origen.listAdj.get(j);
                    Vertex<E> destino = e.getRefDest();
                    int indexDest = listVertex.search(destino);
                    if (indexDest != -1) {
                        matriz[i][indexDest] = 1;
                    }
                }
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            try {
                System.out.print(listVertex.get(i).getData() + " ");
            } catch (ExceptionEmptyLinkedList e) {
                System.out.print("? ");
            }
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            try {
                System.out.print(listVertex.get(i).getData() + " ");
                for (int j = 0; j < n; j++) {
                    System.out.print(" " + matriz[i][j] + " ");
                }
                System.out.println();
            } catch (ExceptionEmptyLinkedList e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public String toString() {
        return this.listVertex.toString();
    }
}