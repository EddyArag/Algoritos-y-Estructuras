package SESION11.LinkedList;

public class Node<T extends Comparable<T>> implements Comparable<T> {
    private T elemento;
    private Node<T> next;

    // Constructor de la clase Node
    public Node(T elemento) {
        this.elemento = elemento;
        this.next = null;
    }

    // Setters y Getters de elemento y next
    public void setElemento(T elemento) {
        this.elemento = elemento;
    }

    public T getElemento() {
        return elemento;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    // Método comparteTo para comparar tipos genéricos
    @Override
    public int compareTo(T otroElemento) {
        return elemento.compareTo(otroElemento);
    }
}
