package SESION07.Ejercicio01;

public class Nodoejer1<E extends Comparable<E>> {
    private E elemento;
    private Nodoejer1<E> left;
    private Nodoejer1<E> right;

    public Nodoejer1(E elemento) {
        this.elemento = elemento;
        this.left = null;
        this.right = null;
    }

    // Getters y Setters
    public E getElem() {
        return elemento;
    }

    public Nodoejer1<E> getLeft() {
        return left;
    }

    public void setLeft(Nodoejer1<E> left) {
        this.left = left;
    }

    public Nodoejer1<E> getRight() {
        return right;
    }

    public void setRight(Nodoejer1<E> right) {
        this.right = right;
    }
}