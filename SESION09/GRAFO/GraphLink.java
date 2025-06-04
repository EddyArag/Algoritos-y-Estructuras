package SESION09.GRAFO;
import SESION09.LISTA.*;;

public class GraphLink<E extends Comparable<E>> {
    protected LinkedList<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new LinkedList<Vertex<E>>();
    }

    public void insertVertext(E data) {

    }
    public void insertEdge(E verOri, E verDes) {

    }

    public String toString() {
        return this.listVertex.toString();
    }
}
