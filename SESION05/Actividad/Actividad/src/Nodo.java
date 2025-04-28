/**
 * Clase Nodo genérica
 */
public class Nodo<T> {
    T dato;
    Nodo<T> enlace;

    public Nodo(T dato) {
        this.dato = dato;
        this.enlace = null;
    }
}

/**
 * Clase ListaEnlazada personalizada
 */
class ListaEnlazada<T> {
    private Nodo<T> primero;

    public ListaEnlazada() {
        this.primero = null;
    }

    public boolean estaVacia() {
        return primero == null;
    }

    public int longitud() {
        int cont = 0;
        Nodo<T> actual = primero;
        while (actual != null) {
            cont++;
            actual = actual.enlace;
        }
        return cont;
    }

    public void insertarAlFinal(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (primero == null) {
            primero = nuevoNodo;
        } else {
            Nodo<T> actual = primero;
            while (actual.enlace != null) {
                actual = actual.enlace;
            }
            actual.enlace = nuevoNodo;
        }
    }

    public boolean eliminar(T dato) {
        if (primero == null)
            return false;

        if (primero.dato.equals(dato)) {
            primero = primero.enlace;
            return true;
        }

        Nodo<T> actual = primero;
        while (actual.enlace != null && !actual.enlace.dato.equals(dato)) {
            actual = actual.enlace;
        }

        if (actual.enlace != null) {
            actual.enlace = actual.enlace.enlace;
            return true;
        }

        return false;
    }

    public boolean contiene(T dato) {
        Nodo<T> actual = primero;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                return true;
            }
            actual = actual.enlace;
        }
        return false;
    }

    public void invertir() {
        Nodo<T> anterior = null;
        Nodo<T> actual = primero;
        Nodo<T> siguiente = null;

        while (actual != null) {
            siguiente = actual.enlace;
            actual.enlace = anterior;
            anterior = actual;
            actual = siguiente;
        }

        primero = anterior;
    }

    @SuppressWarnings("unchecked")
    public T obtenerMasPrioritario() {
        if (primero == null)
            return null;

        Nodo<T> actual = primero;
        T maxima = actual.dato;

        while (actual != null) {
            if (actual.dato instanceof Comparable) {
                if (((Comparable<T>) actual.dato).compareTo(maxima) < 0) {
                    maxima = actual.dato;
                }
            }
            actual = actual.enlace;
        }

        return maxima;
    }

    public void imprimir() {
        Nodo<T> actual = primero;
        while (actual != null) {
            System.out.println("- " + actual.dato);
            actual = actual.enlace;
        }
    }
}