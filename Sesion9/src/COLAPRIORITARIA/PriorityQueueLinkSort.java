package Sesion9.src.COLAPRIORITARIA;
import Sesion9.src.ExceptionIsEmpty;
import Sesion9.src.Node;

public class PriorityQueueLinkSort<E, N extends Comparable<N>> implements PriorityQueue<E, N> {
    class EntryNode implements Comparable<EntryNode>{
        E dato;
        N prioridad;

        public EntryNode(E dato, N prioridad) {
            this.dato = dato;
            this.prioridad = prioridad;
        }

        public int compareTo(N pr) {
            return this.prioridad.compareTo(pr);
        }

        @Override
        public int compareTo(PriorityQueueLinkSort<E, N>.EntryNode o) {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
        }
    }

    private Node<EntryNode> first;
    private Node<EntryNode> last;

    public PriorityQueueLinkSort() {
        this.first = null;
        this.last = null;
    }

    public void enqueue(E x, N pr) {
        Node<EntryNode> nuevoNodo = new Node<>(new EntryNode(x, pr));

        // Caso: la cola está vacía
        if (isEmpty()) {
            this.first = this.last = nuevoNodo;
            return;
        }

        // Caso: el nuevo nodo tiene mayor prioridad que el primero (va al frente)
        if (pr.compareTo(this.first.getElemento().prioridad) < 0) {
            nuevoNodo.setNext(this.first);
            this.first = nuevoNodo;
            return;
        }

        // Recorremos para encontrar el lugar adecuado
        Node<EntryNode> actual = this.first;
        Node<EntryNode> anterior = null;

        while (actual != null && pr.compareTo(actual.getElemento().prioridad) >= 0) {
            anterior = actual;
            actual = actual.getNext();
        }

        // Insertar en el medio o al final
        nuevoNodo.setNext(actual);
        anterior.setNext(nuevoNodo);

        // Si se insertó al final, actualizar el puntero 'last'
        if (nuevoNodo.getNext() == null) {
            this.last = nuevoNodo;
        }
    }

    public E dequeue() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        E aux = this.first.getElemento().dato;
        this.first = this.first.getNext();
        if (this.first == null) {
            this.last = null;
        }
        return aux;
    }

    public E back() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        return this.last.getElemento().dato;
    }

    public E front() throws ExceptionIsEmpty {
        if (this.isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        }
        return this.first.getElemento().dato;
    }

    public boolean isEmpty() {
        if (this.first == null) {
            return true;
        }
        return false;
    }

    public String toString() {
        String cad = "";
        Node<EntryNode> actual = this.first;
        while (actual != null) {
            cad += actual.getElemento().dato + " ";
            actual = actual.getNext();
        }
        return cad;
    }
}
