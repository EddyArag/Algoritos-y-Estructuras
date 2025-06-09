package SESION09.GRAFO;

public class Edge<E extends Comparable<E>> implements Comparable<Edge<E>> {
    private Vertex<E> refDest;
    private int weight;

    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    public Vertex<E> getRefDest() {
        return this.refDest;
    }

    public int getWeight() {
        return this.weight;
    }

    public boolean equals(Object o) {
        if(o instanceof Edge<?>) {
            Edge<E> e = (Edge<E>) o;
            return this.refDest.equals(e.refDest);
        }
        return false;
    }

    public int compareTo(Edge<E> otherEdge) {
        return Integer.compare(this.weight, otherEdge.weight);
    }

    public String toString() {
        if(this.weight > -1) {
            return refDest.getData() + " [" + this.weight + "], ";
        } else {
            return refDest.getData() + ", ";
        }
    }
}