package SESION05.Ejercicio;

public class Lista<T extends Comparable<T>> {
    private Node<T> first;

    // Constructor de la clase Lista
    public Lista() {
        this.first = null;
    }

    // Método para verificar si la lista está vacía
    public boolean isEmptyList() {
        if (first == null) {
            return true;
        }
        return false;
    }

    // Setters y getters de first
    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public Node<T> getFirst() {
        return first;
    }

    // Método para mostrar los elementos de la lista
    public void mostrarElementos() {
        if (first == null) {
            System.out.println("Lista vacía.");
        } else {
            Node<T> actual = first;
            while (actual != null) {
                System.out.print(actual.getElemento() + " ");
                actual = actual.getNext();
            }
        }
    }
}