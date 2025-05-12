package SESION07.Ejercicio01;

public class Nodoejer1<E extends Comparable<E>> implements Comparable<Nodoejer1<E>> {
    private E elem;
    private Nodoejer1<E> left;
    private Nodoejer1<E> right;

    public Nodoejer1(E elem) {
        this.elem = elem;
        this.left = null;
        this.right = null;
    }

    public void setElem(E elem) { this.elem = elem; }
    public E getElem() { return elem; }
    public void setLeft(Nodoejer1<E> left) { this.left = left; }
    public Nodoejer1<E> getLeft() { return left; }
    public void setRight(Nodoejer1<E> right) { this.right = right; }
    public Nodoejer1<E> getRight() { return right; }

    public int compareTo(Nodoejer1<E> otroNodo) {
        return this.elem.compareTo(otroNodo.getElem());
    }

    public String toString() {
        return elem.toString();
    }
}
