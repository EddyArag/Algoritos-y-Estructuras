package Sesion9.src;

public class Node<E extends Comparable<E>> implements Comparable<E> {
    private E elemento;
    private Node<E> next;

    // Constructor de la clase Node
    public Node(E elemento) {
        this.elemento = elemento;
        this.next = null;
    }

    // Setters y Getters de elemento y next
    public void setElemento(E elemento) {
        this.elemento = elemento;
    }

    public E getElemento() {
        return elemento;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public Node<E> getNext() {
        return next;
    }

    // Método comparteTo para comparar tipos genéricos
    @Override
    public int compareTo(E otroElemento) {
        return elemento.compareTo(otroElemento);
    }
}
