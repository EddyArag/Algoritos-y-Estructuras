package SESION09.GRAFO;
import SESION09.LISTA.*;

public class GraphLink<E extends Comparable<E>> {
    protected LinkedList<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new LinkedList<Vertex<E>>();
    }

    public boolean searchVertex(Vertex<E> vertex) throws ExceptionEmptyLinkedList {
        if(listVertex.search(vertex) == -1) {
            return false;
        }
        return true;
    }
    public boolean searchEdge(Vertex<E> verOri, Vertex<E> verDes) {

    }
    public void insertVertext(E vertex) {
        
    }
    public void insertEdge(E verOri, E verDes) {

    }

    public String toString() {
        return this.listVertex.toString();
    }
}
