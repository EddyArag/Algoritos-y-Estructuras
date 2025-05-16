package SESION07.Ejercicio02;

public class NodoEjercicio2<E extends Comparable<E>> {
    private E elem;
    private NodoEjercicio2<E> left, right;

    public NodoEjercicio2(E elem) {
        this.elem = elem;
        this.left = this.right = null;
    }

    public E getElem() {
        return elem;
    }

    public NodoEjercicio2<E> getLeft() {
        return left;
    }

    public NodoEjercicio2<E> getRight() {
        return right;
    }

    public void setLeft(NodoEjercicio2<E> left) {
        this.left = left;
    }

    public void setRight(NodoEjercicio2<E> right) {
        this.right = right;
    }
}
