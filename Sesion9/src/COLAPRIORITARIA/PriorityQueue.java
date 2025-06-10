package Sesion9.src.COLAPRIORITARIA;
import Sesion9.src.ExceptionIsEmpty;

public interface PriorityQueue<E, N> {
    public void enqueue(E x, N pr);
    public E dequeue() throws ExceptionIsEmpty;
    public E front() throws ExceptionIsEmpty;
    public E back() throws ExceptionIsEmpty;
    public boolean isEmpty();
}
