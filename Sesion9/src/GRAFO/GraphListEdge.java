package Sesion9.src.GRAFO;

import Sesion9.src.ExceptionIsEmpty;
import Sesion9.src.Cola.Queue;
import Sesion9.src.Cola.QueueLink;

import java.util.ArrayList;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;
    private boolean directed; // Indica si el grafo es dirigido o no

    public GraphListEdge(boolean directed) {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
        this.directed = directed;
    }

    public boolean isDirected() {
        return directed;
    }

    // Ejercicio 3a: insertVertex(v): inserta el vértice ‘v’ en caso no exista.
    public void insertVertex(V v) {
        // Primero, verificar si el vértice ya existe
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(v)) {
                return; // El vértice ya existe, no hacer nada
            }
        }
        // Si no existe, crear un nuevo objeto VertexObj y añadirlo a la lista de
        // vértices
        VertexObj<V, E> newVertex = new VertexObj<>(v, secVertex.size());
        secVertex.add(newVertex);
    }

    // Ejercicio 3b: insertEdge(v, z): inserta la arista entre los vértices ‘v’ y
    // ‘z’ en caso aún no haya sido insertada.
    public void insertEdge(V v, V z, E info) {
        // Primero, buscar los objetos VertexObj correspondientes a v y z
        VertexObj<V, E> vertexV = null;
        VertexObj<V, E> vertexZ = null;
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(v)) {
                vertexV = vertex;
            }
            if (vertex.getInfo().equals(z)) {
                vertexZ = vertex;
            }
        }

        // Si alguno de los vértices no existe, no se puede insertar la arista
        if (vertexV == null || vertexZ == null) {
            return;
        }

        // Verificar si la arista ya existe
        if (searchEdge(vertexV, vertexZ)) {
            return; // La arista ya existe, no hacer nada
        }

        // Si no existe, crear un nuevo objeto EdgeObj y añadirlo a la lista de aristas
        EdgeObj<V, E> newEdge = new EdgeObj<>(vertexV, vertexZ, info, secEdge.size());
        secEdge.add(newEdge);

        // Si el grafo no es dirigido, añadir también la arista en sentido contrario
        if (!directed) {
            EdgeObj<V, E> newEdgeReverse = new EdgeObj<>(vertexZ, vertexV, info, secEdge.size());
            secEdge.add(newEdgeReverse);
        }
    }

    // Ejercicio 3c: searchVertex(v): busca el vértice ‘v’ y retorna un verdadero si
    // existe el vértice o un falso en caso contrario.
    public boolean searchVertex(V v) {
        // Iterar sobre la lista de vértices y verificar si alguno coincide con el
        // vértice buscado
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(v)) {
                return true; // El vértice existe
            }
        }
        return false; // El vértice no existe
    }

    // Ejercicio 3d: searchEdge(v, z): busca la arista entre los vértices ‘v’ y ‘z’.
    // Retorna verdadero si existe y en caso contrario un falso.
    public boolean searchEdge(V v, V z) {
        // Primero, buscar los objetos VertexObj correspondientes a v y z
        VertexObj<V, E> vertexV = null;
        VertexObj<V, E> vertexZ = null;
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(v)) {
                vertexV = vertex;
            }
            if (vertex.getInfo().equals(z)) {
                vertexZ = vertex;
            }
        }

        // Si alguno de los vértices no existe, la arista no puede existir
        if (vertexV == null || vertexZ == null) {
            return false;
        }

        // Iterar sobre la lista de aristas y verificar si alguna coincide con la arista
        // buscada
        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.getEndVertex1().equals(vertexV) && edge.getEndVertex2().equals(vertexZ)) {
                return true; // La arista existe
            }
            // Si el grafo no es dirigido, también verificar la arista en sentido contrario
            if (!directed && edge.getEndVertex1().equals(vertexZ) && edge.getEndVertex2().equals(vertexV)) {
                return true; // La arista existe en sentido contrario
            }
        }
        return false; // La arista no existe
    }

    // Sobrecarga del método searchEdge para aceptar objetos VertexObj directamente
    public boolean searchEdge(VertexObj<V, E> v1, VertexObj<V, E> v2) {
        for (EdgeObj<V, E> e : secEdge) {
            if (e.getEndVertex1().equals(v1) && e.getEndVertex2().equals(v2)) {
                return true;
            }
            if (!directed && e.getEndVertex1().equals(v2) && e.getEndVertex2().equals(v1)) {
                return true; // Para grafos no dirigidos, la arista puede estar en ambos sentidos
            }
        }
        return false;
    }

    // Ejercicio 3e: bfs (v): realice el recorrido en anchura a partir del vértice v
    // y muestre los vértices que se vayan visitando
    public void bfs(V startInfo) {
        // Primero, buscar el objeto VertexObj correspondiente al vértice inicial
        VertexObj<V, E> start = null;
        for (VertexObj<V, E> vertex : secVertex) {
            if (vertex.getInfo().equals(startInfo)) {
                start = vertex;
                break;
            }
        }

        // Si el vértice inicial no existe, no se puede realizar el recorrido
        if (start == null) {
            return;
        }

        // Utilizar una cola para el recorrido en anchura
        Queue<VertexObj<V, E>> queue = new QueueLink<>();
        // Utilizar una lista para marcar los vértices visitados
        ArrayList<VertexObj<V, E>> visited = new ArrayList<>();

        // Añadir el vértice inicial a la cola y marcarlo como visitado
        queue.enqueue(start);
        visited.add(start);

        // Mientras la cola no esté vacía
        while (!queue.isEmpty()) {
            // Obtener el primer vértice de la cola
            VertexObj<V, E> current = null;
            try {
                current = queue.dequeue();
            } catch (ExceptionIsEmpty e) {
                System.out.println("Error: " + e.getMessage());
                return;
            }
            // Mostrar el vértice actual
            System.out.println(current.getInfo());

            // Iterar sobre las aristas para encontrar los vértices adyacentes
            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> neighbor = null;
                // Si la arista sale del vértice actual, el vecino es el vértice destino
                if (edge.getEndVertex1().equals(current)) {
                    neighbor = edge.getEndVertex2();
                }
                // Si el grafo no es dirigido y la arista llega al vértice actual, el vecino es
                // el vértice origen
                else if (!directed && edge.getEndVertex2().equals(current)) {
                    neighbor = edge.getEndVertex1();
                }

                // Si el vecino existe y no ha sido visitado
                if (neighbor != null && !visited.contains(neighbor)) {
                    // Añadir el vecino a la cola y marcarlo como visitado
                    visited.add(neighbor);
                    queue.enqueue(neighbor);
                }
            }
        }
    }

    // ////////////////////////////////////////////////////////////////////////
    // Ejercicio 7: Implementar para identificar si un grafo dirigido ingresado
    // indique el grado un nodo, de tipo camino, de tipo ciclo, de tipo rueda, de
    // tipo rueda; de acuerdo a las consideraciones del ejercicio 5.
    public String identifyGraphType(V startInfo) {
        if (isPath()) {
            return "Camino";
        } else if (isCycle()) {
            return "Ciclo";
        } else if (isWheel()) {
            return "Rueda";
        } else if (isComplete()) {
            return "Completo";
        } else {
            return "Desconocido";
        }
    }

    // Método auxiliar para verificar si el grafo es de tipo camino
    private boolean isPath() {
        if (secVertex.size() < 3)
            return false; // Minimo 3 vertices
        int edgesCount = secEdge.size();
        if (edgesCount != secVertex.size() - 1)
            return false;
        int startVertices = 0;
        int endVertices = 0;
        int middleVertices = 0;
        for (VertexObj<V, E> vertex : secVertex) {
            int inDegree = getInDegree(vertex);
            int outDegree = getOutDegree(vertex);
            if (inDegree == 0 && outDegree == 1) {
                startVertices++;
            } else if (inDegree == 1 && outDegree == 0) {
                endVertices++;
            } else if (inDegree == 1 && outDegree == 1) {
                middleVertices++;
            } else {
                return false; // Invalid path
            }
        }
        return startVertices == 1 && endVertices == 1 && middleVertices == secVertex.size() - 2;
    }

    // Método auxiliar para verificar si el grafo es de tipo ciclo
    private boolean isCycle() {
        if (secVertex.size() < 3)
            return false;
        int edgesCount = secEdge.size(); // No dividir por 2 en grafos dirigidos
        if (edgesCount != secVertex.size())
            return false;
        for (VertexObj<V, E> vertex : secVertex) {
            int inDegree = getInDegree(vertex);
            int outDegree = getOutDegree(vertex);
            if (inDegree != 1 || outDegree != 1) {
                return false; // Invalid cycle
            }
        }
        return true;
    }

    // Método auxiliar para verificar si el grafo es de tipo rueda (wheel)
    private boolean isWheel() {
        if (secVertex.size() < 4)
            return false;

        VertexObj<V, E> center = null;

        // Buscar un vértice que se pueda considerar como el centro de la rueda
        for (VertexObj<V, E> vertex : secVertex) {
            int inDegree = getInDegree(vertex);
            int outDegree = getOutDegree(vertex);

            // El centro debe estar conectado con todos los demás vértices
            if (inDegree == secVertex.size() - 1 && outDegree == secVertex.size() - 1) {
                center = vertex;
                break; // Se encontró un posible centro
            }
        }

        // Si no se encontró un centro, no es una rueda
        if (center == null)
            return false;

        // Verificar si los vértices restantes forman un ciclo
        ArrayList<VertexObj<V, E>> cycleVertices = new ArrayList<>(secVertex);
        cycleVertices.remove(center);

        if (cycleVertices.size() != secVertex.size() - 1)
            return false;

        GraphListEdge<V, E> cycleGraph = new GraphListEdge<>(true); // Ciclo dirigido
        cycleGraph.secVertex = new ArrayList<>(cycleVertices); // Añadir los vértices del ciclo
        // Agregar solo las aristas que conectan los vértices del ciclo
        for (EdgeObj<V, E> edge : secEdge) {
            if (cycleVertices.contains(edge.getEndVertex1()) && cycleVertices.contains(edge.getEndVertex2())) {
                boolean edgeExists = false;

                // Evitar agregar aristas duplicadas
                for (EdgeObj<V, E> cycleEdge : cycleGraph.secEdge) {
                    if (cycleEdge.getEndVertex1().equals(edge.getEndVertex1())
                            && cycleEdge.getEndVertex2().equals(edge.getEndVertex2())) {
                        edgeExists = true;
                        break;
                    }
                }

                // Insertar la arista si no ha sido añadida
                if (!edgeExists) {
                    cycleGraph.insertEdge((V) edge.getEndVertex1().getInfo(), (V) edge.getEndVertex2().getInfo(),
                            edge.getInfo());
                }
            }
        }
        if (cycleGraph.secVertex.size() < 3)
            return false;
        if (cycleGraph.secEdge.size() != cycleGraph.secVertex.size())
            return false;
        // Verificar si los vértices restantes realmente forman un ciclo
        if (!cycleGraph.isCycle())
            return false;

        return true; // Es una rueda
    }

    // Método auxiliar para verificar si el grafo es de tipo completo
    private boolean isComplete() {
        int n = secVertex.size();
        int expectedEdges = n * (n - 1); // Cada par tiene dos aristas (A->B y B->A)
        if (secEdge.size() != expectedEdges)
            return false;
        for (VertexObj<V, E> v1 : secVertex) {
            for (VertexObj<V, E> v2 : secVertex) {
                if (v1 != v2) {
                    if (!searchEdge(v1, v2) || !searchEdge(v2, v1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Método auxiliar para obtener el grado de un vértice
    private int getInDegree(VertexObj<V, E> vertex) {
        int degree = 0;
        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.getEndVertex2().equals(vertex)) {
                degree++;
            }
        }
        return degree;
    }

    private int getOutDegree(VertexObj<V, E> vertex) {
        int degree = 0;
        for (EdgeObj<V, E> edge : secEdge) {
            if (edge.getEndVertex1().equals(vertex)) {
                degree++;
            }
        }
        return degree;
    }
    /*
     * private int getDegree(VertexObj<V, E> vertex) {
     * int degree = 0;
     * for (EdgeObj<V, E> edge : secEdge) {
     * if (edge.getEndVertex1().equals(vertex) || (!directed &&
     * edge.getEndVertex2().equals(vertex))) {
     * degree++;
     * }
     * }
     * return degree;
     * }
     */

    // ////////////////////////////////////////////////////////////////////////
    // Ejercicio 8: Utilizando TAD Graph, GraphLink y GraphListEdge, y la
    // implementación del ejercicio 7, definir formas de definir un grafo no
    // dirigido: formal, lista y matriz de adyacencia; de acuerdo a las
    // consideraciones del ejercicio 6.
    public void displayGraphRepresentations() {
        System.out.println("\n--- Representación FORMAL ---");
        displayFormalRepresentation();

        System.out.println("\n--- Lista de ADYACENCIAS ---");
        displayAdjacencyListRepresentation();

        System.out.println("\n--- MATRIZ de ADYACENCIA ---");
        displayAdjacencyMatrixRepresentation();
    }

    // Representación Formal
    private void displayFormalRepresentation() {
        for (VertexObj<V, E> vertex : secVertex) {
            System.out.print("Nodo " + vertex.getInfo() + " conectado a: ");
            for (EdgeObj<V, E> edge : secEdge) {
                if (edge.getEndVertex1().equals(vertex)) {
                    System.out.print(edge.getEndVertex2().getInfo() + " ");
                } else if (!directed && edge.getEndVertex2().equals(vertex)) {
                    System.out.print(edge.getEndVertex1().getInfo() + " ");
                }
            }
            System.out.println();
        }
    }

    // Representación de Lista de Adyacencia
    private void displayAdjacencyListRepresentation() {
        for (VertexObj<V, E> vertex : secVertex) {
            System.out.print(vertex.getInfo() + " --> ");
            ArrayList<V> neighbors = new ArrayList<>();
            for (EdgeObj<V, E> edge : secEdge) {
                if (edge.getEndVertex1().equals(vertex)) {
                    neighbors.add(edge.getEndVertex2().getInfo());
                } else if (!directed && edge.getEndVertex2().equals(vertex)) {
                    neighbors.add(edge.getEndVertex1().getInfo());
                }
            }
            System.out.println(neighbors.toString());
        }
    }

    // Representación de la Matriz de Adyacencia
    private void displayAdjacencyMatrixRepresentation() {
        int n = secVertex.size();
        int[][] matrix = new int[n][n];

        // Inicializar la matriz con ceros
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = 0;
            }
        }

        // Llenar la matriz con las adyacencias
        for (EdgeObj<V, E> edge : secEdge) {
            int row = edge.getEndVertex1().getPosition();
            int col = edge.getEndVertex2().getPosition();
            matrix[row][col] = 1;
            if (!directed) {
                matrix[col][row] = 1;
            }
        }

        // Imprimir la matriz
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // ////////////////////////////////////////////////////////////////////////
    // Ejercicio 9: Utilizando TAD Graph, GraphLink y GraphListEdge, implementar
    // para identificar si un grafo dirigido ingresado de tipo isomorfo, de tipo
    // plano, de tipo conexo; considerando:
    public boolean isIsomorphic(GraphListEdge<V, E> otherGraph) {
        // Implementación de isomorfismo (compleja, requiere comparar estructuras)
        // Esta es una versión simplificada que solo compara el número de vértices y
        // aristas
        if (secVertex.size() != otherGraph.secVertex.size() || secEdge.size() != otherGraph.secEdge.size()) {
            return false;
        }
        // Una implementación completa requeriría un algoritmo de búsqueda de
        // isomorfismo
        return true;
    }

    public boolean isPlanar() {
        // Implementación de planaridad (compleja, requiere algoritmos de detección de
        // cruces)
        // Esta es una versión simplificada que siempre devuelve falso
        return false;
    }

    public boolean isConnected() {
        if (secVertex.isEmpty())
            return true; // Un grafo vacío se considera conexo

        // Usar BFS para verificar si todos los vértices son alcanzables desde un
        // vértice inicial
        VertexObj<V, E> start = secVertex.get(0);
        Queue<VertexObj<V, E>> queue = new QueueLink<>();
        ArrayList<VertexObj<V, E>> visited = new ArrayList<>();

        queue.enqueue(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            VertexObj<V, E> current = null;
            try {
                current = queue.dequeue();
            } catch (ExceptionIsEmpty e) {
                System.out.println("Error: " + e.getMessage());
                return false;
            }
            for (EdgeObj<V, E> edge : secEdge) {
                VertexObj<V, E> neighbor = null;
                if (edge.getEndVertex1().equals(current))
                    neighbor = edge.getEndVertex2();
                else if (!directed && edge.getEndVertex2().equals(current))
                    neighbor = edge.getEndVertex1();

                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    try {
                        queue.enqueue(neighbor);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        // Si todos los vértices fueron visitados, el grafo es conexo
        return visited.size() == secVertex.size();
    }

    public boolean isSelfComplementary() {
        // Implementación de autocomplementariedad (compleja, requiere generar el
        // complemento y verificar isomorfismo)
        // Esta es una versión simplificada que siempre devuelve falso
        return false;
    }

    // Método para obtener el grafo complemento
    public GraphListEdge<V, E> getComplement() {
        GraphListEdge<V, E> complement = new GraphListEdge<>(directed);

        // Copiar los vértices
        for (VertexObj<V, E> vertex : secVertex) {
            complement.insertVertex(vertex.getInfo());
        }

        // Añadir las aristas que no existen en el grafo original
        for (VertexObj<V, E> v1 : secVertex) {
            for (VertexObj<V, E> v2 : secVertex) {
                if (!v1.equals(v2) && !searchEdge(v1, v2)) {
                    // Crear una arista con información nula (o un valor por defecto)
                    complement.insertEdge(v1.getInfo(), v2.getInfo(), null);
                }
            }
        }

        return complement;
    }
}
