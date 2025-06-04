package SESION09.GRAFO;

public class Edge<E extends Comparable<E>> implements Comparable<Edge<E>> {
    private Vertex<E> refDest;
    private int weight;

    public Edge(Vertex<E> refDest, int weight) {
        this.refDest = refDest;
        this.weight = weight;
    }

    public boolean equals(Object o) {
        if(o instanceof Edge<?>) {
            Edge<E> e = (Edge<E>) o;
            return this.refDest.equals(e.refDest);
        }
        return false;
    }
    public int compareTo(Edge<E> otherEdge) {
        if(this.weight > otherEdge.weight) { return 1; } 
        else if(this.weight < otherEdge.weight) { return -1; } 
        else { return 0; }
    }
    public String toString() {
        if(this.weight > -1) {
            return refDest.getData() + " [" + this.weight + "], ";
        } else {
            return refDest.getData() + ", ";
        }
    }
}
