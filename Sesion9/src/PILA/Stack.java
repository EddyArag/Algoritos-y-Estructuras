package Sesion9.src.PILA;
import Sesion9.src.ExceptionIsEmpty;

public interface Stack<E> {
    void push(E x);
    E pop() throws ExceptionIsEmpty;
    E top() throws ExceptionIsEmpty;
    boolean isEmpty();
    String toString();
}