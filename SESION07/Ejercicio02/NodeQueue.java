package SESION07.Ejercicio02;

public class NodeQueue<E> {
    E elem;
    NodeQueue<E> next;

    public NodeQueue(E elem) {
        this.elem = elem;
        this.next = null;
    }
}
