package Sesion9.src.Cola;

public interface Queue<E> {
    public void enqueue(E x);

    public E dequeue() throws ExceptionIsEmpty;

    public E front() throws ExceptionIsEmpty;

    public E back() throws ExceptionIsEmpty;

    public boolean isEmpty();
}
