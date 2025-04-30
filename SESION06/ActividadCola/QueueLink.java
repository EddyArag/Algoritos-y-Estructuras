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
        if(this.isEmpty()){
            this.first = aux;
        } else{
            this.last.setNext(aux);
        }
        this.last = aux;
    }
    public E dequeue() throws ExceptionIsEmpty{

    }
    public E back() throws ExceptionIsEmpty{

    }
    public E front() throws ExceptionIsEmpy{

    }
    public boolean isEmpty() {

    }
    public String toString(){
        
    }
}
