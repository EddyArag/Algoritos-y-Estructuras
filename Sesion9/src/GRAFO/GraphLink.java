package Sesion9.src.GRAFO;

import Sesion9.src.LISTA.*;
import Sesion9.src.PILA.*;
import Sesion9.src.Cola.*;
import Sesion9.src.Node;
import Sesion9.src.ExceptionIsEmpty;
import Sesion9.src.COLAPRIORITARIA.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            } catch (ExceptionIsEmpty e) {
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
            } catch (ExceptionIsEmpty e) {
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
        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
        }
    }

    // Metodos para remover vértices y aristas en grafo NO DIRIGIDO
    public void removeVertex(E v) {
        try {
            Vertex<E> toRemove = null;
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(v) == 0) {
                    toRemove = vertex;
                    break;
                }
            }

            if (toRemove != null) {
                for (int i = 0; i < toRemove.listAdj.length(); i++) {
                    Edge<E> edge = toRemove.listAdj.get(i);
                    Vertex<E> destino = edge.getRefDest();
                    for (int j = 0; j < destino.listAdj.length(); ) {
                        Edge<E> reverseEdge = destino.listAdj.get(j);
                        if (reverseEdge.getRefDest().compareTo(toRemove) == 0) {
                            destino.listAdj.removeNode(reverseEdge);
                        } else {
                            j++;
                        }
                    }
                }
                listVertex.removeNode(toRemove);
            }
        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
        }
    }
    public void removeEdge(E v, E z) {
        try {
            Vertex<E> origin = null;
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(v) == 0) {
                    origin = vertex;
                    break;
                }
            }

            if (origin != null) {
                Edge<E> toRemove = null;
                Vertex<E> destination = null;
                for (int i = 0; i < origin.listAdj.length(); i++) {
                    Edge<E> edge = origin.listAdj.get(i);
                    if (edge.getRefDest().getData().compareTo(z) == 0) {
                        toRemove = edge;
                        destination = edge.getRefDest();
                        break;
                    }
                }
                if (toRemove != null && destination != null) {
                    for (int i = 0; i < destination.listAdj.length(); ) {
                        Edge<E> edge = destination.listAdj.get(i);
                        if (edge.getRefDest().getData().compareTo(v) == 0) {
                            destination.listAdj.removeNode(edge);
                            break;
                        } else {
                            i++;
                        }
                    }
                    origin.listAdj.removeNode(toRemove);
                }
            }
        } catch (ExceptionIsEmpty e) {
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
        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
        }
    }

    // Métodos para remover vértices y aristas en grafo DIRIGIDO
    public void removeVertexDirigido(E v) {
        try {
            // Buscar el vértice que queremos eliminar
            Vertex<E> toRemove = null;
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
        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
        }
    }
    public void removeEdgeDirigido(E v, E z) {
        try {
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);
                //Eliminamos la unica arista que va desde v hasta z
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
        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
        }
    }

    // Método para recorrido en profundidad (dfs) en grafo NO DIRIGIDO y DIRIGIDO
    public void dfs(E data) {
        try {
            // Buscar el vértice de inicio
            Vertex<E> startVertex = null;
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
            // Poner todos los vértices como NO VISITADOS
            for (int i = 0; i < listVertex.length(); i++) {
                listVertex.get(i).setVisited(false);
            }
            // Empezar el recorrido en profundidad
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
        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
        }
    }

    // Método para recorrido en anchura (bfs) en grafo NO DIRIGIDO y DIRIGIDO
    public void bfs(E data) {
        try {
            // Buscar el vértice de inicio
            Vertex<E> startVertex = null;
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
            // Inicializar la cola
            QueueLink<Vertex<E>> queue = new QueueLink<>();
            // Poner todos los vértices como NO VISITADOS
            for (int i = 0; i < listVertex.length(); i++) {
                listVertex.get(i).setVisited(false);
            }
            // Empezar el recorrido en anchura
            queue.enqueue(startVertex);
            startVertex.setVisited(true);
            System.out.print("Recorrido BFS: ");
            while (!queue.isEmpty()) {
                Vertex<E> current = queue.dequeue();
                System.out.print(current.getData() + " ");
                for (int i = 0; i < current.listAdj.length(); i++) {
                    Edge<E> edge = current.listAdj.get(i);
                    Vertex<E> neighbor = edge.getRefDest();
                    if (!neighbor.isVisited()) {
                        queue.enqueue(neighbor);
                        neighbor.setVisited(true);
                    }
                }
            }
            System.out.println();
        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
        }
    }

    // Método para recorrido en anchura (bfs) con el camino de vértices que se recorrió
    public ArrayList<E> bfsPath(E verOri, E verDest) {
        ArrayList<E> path = new ArrayList<>();
        try {
            Vertex<E> startVertex = null;
            Vertex<E> endVertex = null;
            // Buscar los vértices de inicio y fin
            for (int i = 0; i < listVertex.length(); i++) {
                Vertex<E> vertex = listVertex.get(i);
                if (vertex.getData().compareTo(verOri) == 0) {
                    startVertex = vertex;
                }
                if (vertex.getData().compareTo(verDest) == 0) {
                    endVertex = vertex;
                }
            }
            if (startVertex == null || endVertex == null) {
                System.out.println("Uno o ambos vértices no existen.");
                return path;
            }
            // Inicializar la cola
            QueueLink<Vertex<E>> queue = new QueueLink<>();
            // Listas paralelas: vértices y sus respectivos padres
            LinkedList<Vertex<E>> verticesList = new LinkedList<>();
            LinkedList<Vertex<E>> parentList = new LinkedList<>();
            // Marcar todos los vértices como no visitados
            for (int i = 0; i < listVertex.length(); i++) {
                listVertex.get(i).setVisited(false);
            }
            queue.enqueue(startVertex);
            startVertex.setVisited(true);

            verticesList.addLast(startVertex);
            parentList.addLast(null);

            boolean found = false;
            while (!queue.isEmpty() && !found) {
                Vertex<E> current = queue.dequeue();
                if (current.getData().compareTo(verDest) == 0) {
                    found = true;
                    break;
                }
                for (int i = 0; i < current.listAdj.length(); i++) {
                    Edge<E> edge = current.listAdj.get(i);
                    Vertex<E> neighbor = edge.getRefDest();
                    if (!neighbor.isVisited()) {
                        queue.enqueue(neighbor);
                        neighbor.setVisited(true);
                        verticesList.addLast(neighbor);
                        parentList.addLast(current);
                    }
                }
            }
            // Reconstrucción del camino
            if (found) {
                LinkedList<E> reversePath = new LinkedList<>();
                Vertex<E> current = endVertex;
                while (current != null) {
                    reversePath.addFirst(current.getData());
                    int index = verticesList.search(current);
                    if (index != -1) {
                        current = parentList.get(index);
                    } else {
                        current = null;
                    }
                }
                Node<E> node = reversePath.getFirst();
                while (node != null) {
                    path.add(node.getElemento());
                    node = node.getNext();
                }
            } else {
                System.out.println("No existe un camino entre " + verOri + " y " + verDest);
            }
        } catch (ExceptionIsEmpty e) {
            e.printStackTrace();
        }
        return path;
    }

    // Método que encuentra la ruta más corta entre dos vértices
    public ArrayList<Vertex<E>> shortPath(Vertex<E> source, Vertex<E> destination) throws ExceptionIsEmpty {
        // Distancias mínimas desde el origen
        Map<Vertex<E>, Integer> distances = new HashMap<>();
        // Predecesores para reconstruir el camino
        Map<Vertex<E>, Vertex<E>> predecessors = new HashMap<>();
        // Cola de prioridad basada en distancia
        PriorityQueueLinkSort<Vertex<E>, Integer> pq = new PriorityQueueLinkSort<>();

        // Inicializar todos los vértices con distancia infinita
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            Vertex<E> vertex = current.getElemento();
            distances.put(vertex, Integer.MAX_VALUE);
            vertex.setVisited(false);
            current = current.getNext();
        }
        // Distancia del origen es 0
        distances.put(source, 0);
        pq.enqueue(source, 0);
        while (!pq.isEmpty()) {
            Vertex<E> u = pq.dequeue();
            if (u.isVisited()) continue;
            u.setVisited(true);
            Node<Edge<E>> edgeNode = u.listAdj.getFirst();
            while (edgeNode != null) {
                Edge<E> edge = edgeNode.getElemento();
                Vertex<E> neighbor = edge.getRefDest();
                int weight = edge.getWeight();
                int alt = distances.get(u) + weight;

                if (alt < distances.get(neighbor)) {
                    distances.put(neighbor, alt);
                    predecessors.put(neighbor, u);
                    pq.enqueue(neighbor, alt);
                }

                edgeNode = edgeNode.getNext();
            }
        }
        // Reconstruir el camino desde destino a origen
        ArrayList<Vertex<E>> path = new ArrayList<>();
        Vertex<E> step = destination;
        if (!predecessors.containsKey(step) && !step.equals(source)) {
            return path;
        }
        while (step != null) {
            path.add(0, step);
            step = predecessors.get(step);
        }
        return path;
    }

    // Método para corroborar que un grafo es conexo
    public boolean isConexo() throws ExceptionEmptyLinkedList {
        // Un grafo vacío es un grafo conexo
        if (listVertex.isEmptyList()) {
            return true;
        }
        Vertex<E> startVertex = listVertex.getFirst().getElemento();
        bfs(startVertex.getData());
        // Verificamos si todos los vértices fueron visitados
        Node<Vertex<E>> current = listVertex.getFirst();
        while (current != null) {
            if (!current.getElemento().isVisited()) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    // Método de Dijkstra para hallar el menor recorrido de un vértice a otro
    public StackArray<E> dijkstra(E v, E w) throws ExceptionEmptyLinkedList, ExceptionIsEmpty {
        HashMap<Vertex<E>, Integer> distancias = new HashMap<>();
        HashMap<Vertex<E>, Vertex<E>> predecesores = new HashMap<>();
        PriorityQueueLinkSort<Vertex<E>, Integer> pq = new PriorityQueueLinkSort<>();
        // Inicializar todas las distancias a infinito (usaremos Integer.MAX_VALUE)
        for (int i = 0; i < listVertex.length(); i++) {
            Vertex<E> vertex = listVertex.get(i);
            distancias.put(vertex, Integer.MAX_VALUE);
            predecesores.put(vertex, null);
        }
        // Encontrar vértice de inicio y de destino
        Vertex<E> startVertex = null;
        Vertex<E> endVertex = null;
        for (int i = 0; i < listVertex.length(); i++) {
            Vertex<E> vertex = listVertex.get(i);
            if (vertex.getData().compareTo(v) == 0) {
                startVertex = vertex;
            }
            if (vertex.getData().compareTo(w) == 0) {
                endVertex = vertex;
            }
        }
        if (startVertex == null || endVertex == null) {
            System.out.println("Alguno de los vértices no existe.");
            return new StackArray<>(listVertex.length());
        }
        // Inicializamos distancia del vértice de inicio a 0
        distancias.put(startVertex, 0);
        pq.enqueue(startVertex, 0);
        // Algoritmo de Dijkstra
        while (!pq.isEmpty()) {
            Vertex<E> current = pq.dequeue();
            for (int i = 0; i < current.listAdj.length(); i++) {
                Edge<E> edge = current.listAdj.get(i);
                Vertex<E> neighbor = edge.getRefDest();
                int nuevaDist = distancias.get(current) + edge.getWeight();
                if (nuevaDist < distancias.get(neighbor)) {
                    distancias.put(neighbor, nuevaDist);
                    predecesores.put(neighbor, current);
                    pq.enqueue(neighbor, nuevaDist);
                }
            }
        }
        // Reconstruir la ruta más corta (de w hacia v)
        StackArray<E> ruta = new StackArray<>(listVertex.length());
        Vertex<E> step = endVertex;
        if (predecesores.get(step) == null && step != startVertex) {
            System.out.println("No existe ruta entre los vértices.");
            return ruta;
        }
        while (step != null) {
            ruta.push(step.getData());
            step = predecesores.get(step);
        }
        return ruta;
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
            } catch (ExceptionIsEmpty e) {
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

            } catch (ExceptionIsEmpty e) {
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
            } catch (ExceptionIsEmpty e) {
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
            } catch (ExceptionIsEmpty e) {
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
            } catch (ExceptionIsEmpty e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Mostrar la matriz
        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> vertex = listVertex.get(i);
                System.out.print(vertex.getData() + " ");
            } catch (ExceptionIsEmpty e) {
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
            } catch (ExceptionIsEmpty e) {
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
            } catch (ExceptionIsEmpty e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        // Mostrar grados de los vértices
        System.out.println("\n--- Grados de los nodos (Dirigido) ---");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> v = listVertex.get(i);
                System.out.println("Nodo " + v.getData() + ": Entrada=" + entrada[i] + ", Salida=" + salida[i]);
            } catch (ExceptionIsEmpty e) {
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
            // Rueda (centro emisor o receptor)
            boolean centroEmisor = (salida[i] == n - 1 && entrada[i] == 0);
            boolean centroReceptor = (entrada[i] == n - 1 && salida[i] == 0);
            if (centroEmisor || centroReceptor) {
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
            } catch (ExceptionIsEmpty e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("\n--- Lista de ADYACENCIAS (Dirigido) ---");
        for (int i = 0; i < n; i++) {
            try {
                Vertex<E> v = listVertex.get(i);
                System.out.print(v.getData() + " --> ");
                System.out.println(v.listAdj.toString());
            } catch (ExceptionIsEmpty e) {
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
            } catch (ExceptionIsEmpty e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.print("   ");
        for (int i = 0; i < n; i++) {
            try {
                System.out.print(listVertex.get(i).getData() + " ");
            } catch (ExceptionIsEmpty e) {
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
            } catch (ExceptionIsEmpty e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public String toString() {
        return this.listVertex.toString();
    }
}