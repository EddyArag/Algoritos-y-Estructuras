package Sesion9.src.GRAFO;

import Sesion9.src.LISTA.LinkedList;

public class Vertex<E extends Comparable<E>> implements Comparable<Vertex<E>> {
    private E data;
    protected LinkedList<Edge<E>> listAdj;

    public Vertex(E data) {
        this.data = data;
        listAdj = new LinkedList<Edge<E>>();
    }

    public E getData() {
        return data;
    }

    public boolean equals(Object o) {
        if (o instanceof Vertex<?>) {
            Vertex<E> v = (Vertex<E>) o;
            return this.data.compareTo(v.data) == 0;
        }
        return false;
    }

    public int compareTo(Vertex<E> otherVertex) {
        return this.data.compareTo(otherVertex.data);
    }

    public String toString() {
        return this.data + " --> " + this.listAdj.toString() + "\n";
    }
}