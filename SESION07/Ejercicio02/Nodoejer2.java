package SESION07.Ejercicio02;

public class Nodoejer2<E extends Comparable<E>> implements Comparable<Nodoejer2<E>> {
    private E elem;
    private Nodoejer2<E> left;
    private Nodoejer2<E> right;

    public Nodoejer2(E elem) {
        this.elem = elem;
        this.left = null;
        this.right = null;
    }

    public void setElem(E elem) { this.elem = elem; }
    public E getElem() { return elem; }
    public void setLeft(Nodoejer2<E> left) { this.left = left; }
    public Nodoejer2<E> getLeft() { return left; }
    public void setRight(Nodoejer2<E> right) { this.right = right; }
    public Nodoejer2<E> getRight() { return right; }

    @Override
    public int compareTo(Nodoejer2<E> otroNodo) {
        return this.elem.compareTo(otroNodo.getElem());
    }

    @Override
    public String toString() {
        return elem.toString();
    }
}

