package Sesion9.src.GRAFO;

import Sesion9.src.LISTA.LinkedList;
import Sesion9.src.LISTA.ExceptionEmptyLinkedList;

public class GraphListEdge<V, E> {
    private LinkedList<VertexObj<V, E>> secVertex;
    private LinkedList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new LinkedList<>();
        this.secEdge = new LinkedList<>();
    }

    public boolean searchVertex(V v) {
        var node = secVertex.getFirst();
        while (node != null) {
            if (node.getData().info.equals(v)) return true;
            node = node.getNext();
        }
        return false;
    }

    public void insertVertex(V v) {
        if (!searchVertex(v)) {
            int pos = secVertex.length();
            secVertex.addLast(new VertexObj<>(v, pos));
        }
    }

    public boolean searchEdge(V v, V z) {
        var node = secEdge.getFirst();
        while (node != null) {
            EdgeObj<V, E> edge = node.getData();
            V v1 = edge.getEndVertex1().info;
            V v2 = edge.getEndVertex2().info;
            if ((v1.equals(v) && v2.equals(z)) || (v1.equals(z) && v2.equals(v))) {
                return true;
            }
            node = node.getNext();
        }
        return false;
    }

    public void insertEdge(V v, V z) {
        if (searchEdge(v, z)) return;

        insertVertex(v);
        insertVertex(z);

        VertexObj<V, E> v1 = getVertexObj(v);
        VertexObj<V, E> v2 = getVertexObj(z);
        int pos = secEdge.length();
        secEdge.addLast(new EdgeObj<>(v1, v2, null, pos));
    }

    private VertexObj<V, E> getVertexObj(V data) {
        var node = secVertex.getFirst();
        while (node != null) {
            if (node.getData().info.equals(data)) return node.getData();
            node = node.getNext();
        }
        return null;
    }

    public void bfs(V start) throws ExceptionEmptyLinkedList {
        boolean[] visited = new boolean[secVertex.length()];
        StackArray<VertexObj<V, E>> stack = new StackArray<>(secVertex.length());

        VertexObj<V, E> startVertex = getVertexObj(start);
        if (startVertex == null) return;

        stack.push(startVertex);
        visited[startVertex.position] = true;

        while (!stack.isEmpty()) {
            VertexObj<V, E> current = stack.pop();
            System.out.println(current.info);

            var edgeNode = secEdge.getFirst();
            while (edgeNode != null) {
                EdgeObj<V, E> edge = edgeNode.getData();
                VertexObj<V, E> neighbor = null;

                if (edge.getEndVertex1().equals(current)) {
                    neighbor = edge.getEndVertex2();
                } else if (edge.getEndVertex2().equals(current)) {
                    neighbor = edge.getEndVertex1();
                }

                if (neighbor != null && !visited[neighbor.position]) {
                    stack.push(neighbor);
                    visited[neighbor.position] = true;
                }
                edgeNode = edgeNode.getNext();
            }
        }
    }
}
