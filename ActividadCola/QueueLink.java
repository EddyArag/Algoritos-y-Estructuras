package SESION06.ActividadCola;

public class QueueLink<E extends Comparable<E>> implements Queue<E>{
    private Node<E> first;
    private Node<E> last;

    public QueueLink(){
        this.first = null;
        this.last = null;
    }

    public void enqueue(E x){
        Node<E> aux = new Node<E>(x);
        if(this.isEmpty()) {
            this.first = aux;
        } else{
            this.last.setNext(aux);
        }
        this.last = aux;
    }
    public E dequeue() throws ExceptionIsEmpty{
        if(this.isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        } else{
            E elemento = first.getElemento();
            first = first.getNext();
            if(first == null) {
                last = null;
            }
            return elemento;
        }
    }
    public E back() throws ExceptionIsEmpty{
        if(this.isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        } else{
            return last.getElemento();
        }
    }
    public E front() throws ExceptionIsEmpty{
        if(this.isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía.");
        } else{
            return first.getElemento();
        }
    }
    public boolean isEmpty() {
        if(first == null){
            return true;
        }
        return false;
    }
    public String toString(){
        String cad = "";
        Node<E> actual = first;
        while(actual != null) {
            cad += actual.getElemento() + " ";
            actual = actual.getNext();
        }
        return cad;
    }
}
