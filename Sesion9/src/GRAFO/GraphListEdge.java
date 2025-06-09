package Sesion9.src.GRAFO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public boolean searchVertex(VertexObj<V, E> v) {
        return secVertex.contains(v);
    }

    public void insertVertex(V info) {
        for (VertexObj<V, E> v : secVertex) {
            if (v.getInfo().equals(info)) return;
        }
        secVertex.add(new VertexObj<>(info, secVertex.size()));
    }

    public boolean searchEdge(VertexObj<V, E> v1, VertexObj<V, E> v2) {
        for (EdgeObj<V, E> e : secEdge) {
            if (e.getEndVertex1().equals(v1) && e.getEndVertex2().equals(v2)) {
                return true;
            }
        }
        return false;
    }

    public void insertEdge(V info1, V info2, E infoEdge) {
        VertexObj<V, E> v1 = null, v2 = null;
        for (VertexObj<V, E> v : secVertex) {
            if (v.getInfo().equals(info1)) v1 = v;
            if (v.getInfo().equals(info2)) v2 = v;
        }
        if (v1 == null || v2 == null) return;
        if (!searchEdge(v1, v2)) {
            secEdge.add(new EdgeObj<>(v1, v2, infoEdge, secEdge.size()));
        }
    }

    public void bfs(V startInfo) {
        VertexObj<V, E> start = null;
        for (VertexObj<V, E> v : secVertex) {
            if (v.getInfo().equals(startInfo)) {
                start = v;
                break;
            }
        }
        if (start == null) return;
        Queue<VertexObj<V, E>> queue = new LinkedList<>();
        ArrayList<VertexObj<V, E>> visited = new ArrayList<>();
        queue.add(start);
        visited.add(start);
        while (!queue.isEmpty()) {
            VertexObj<V, E> current = queue.poll();
            System.out.println(current.getInfo());
            for (EdgeObj<V, E> e : secEdge) {
                VertexObj<V, E> neighbor = null;
                if (e.getEndVertex1().equals(current)) neighbor = e.getEndVertex2();
                else if (e.getEndVertex2().equals(current)) neighbor = e.getEndVertex1();
                if (neighbor != null && !visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
    }
}