package SESION07.Ejercicio03;

public class Node<E extends Comparable<E>> implements Comparable<E> {
    private E elem;
    private Node<E> left;
    private Node<E> right;

    public Node(E elem) {
        this.elem = elem;
        this.left = null;
        this.right = null;
    }

    public void setElem(E elem) { this.elem = elem; }
    public E getElem() { return elem; }
    public void setLeft(Node<E> left) { this.left = left; }
    public Node<E> getLeft() { return left; }
    public void setRight(Node<E> right) { this.right = right; }
    public Node<E> getRight() { return right; }

    public int compareTo(E otroElem) {
        return this.elem.compareTo(otroElem);
    }

    public String toString() {
        return elem.toString();
    }
}
