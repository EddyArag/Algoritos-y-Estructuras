/**
 * Clase Nodo genérica
 * Representa un nodo dentro de una lista enlazada simple
 */
public class Nodo<T> {
    T dato; // Dato almacenado en el nodo
    Nodo<T> enlace; // Referencia al siguiente nodo en la lista

    /**
     * Constructor del nodo
     * 
     * @param dato Dato que se almacenará en este nodo
     */
    public Nodo(T dato) {
        this.dato = dato; // Se guarda el dato proporcionado
        this.enlace = null; // Inicialmente no hay siguiente nodo (nodo aislado)
    }
}

/**
 * Clase ListaEnlazada personalizada
 * Implementa una lista enlazada simple con varias operaciones
 */
class ListaEnlazada<T extends Comparable<T>> {
    private Nodo<T> primero; // Referencia al primer nodo de la lista

    /**
     * Constructor de la lista enlazada
     * Inicializa la lista como vacía
     */
    public ListaEnlazada() {
        this.primero = null; // Al principio, no hay nodos en la lista
    }

    /**
     * Verifica si la lista está vacía
     * 
     * @return true si no hay nodos, false en caso contrario
     */
    public boolean estaVacia() {
        return primero == null; // Si primero es null, la lista está vacía
    }

    /**
     * Calcula la longitud (número de nodos) de la lista
     * 
     * @return Cantidad de nodos en la lista
     */
    public int longitud() {
        int cont = 0; // Contador de nodos
        Nodo<T> actual = primero; // Nodo actual para recorrer la lista desde el principio
        while (actual != null) { // Mientras no lleguemos al final
            cont++; // Aumentamos el contador
            actual = actual.enlace; // Avanzamos al siguiente nodo
        }
        return cont; // Devolvemos el total contado
    }

    /**
     * Inserta un nuevo dato al final de la lista
     * 
     * @param dato Dato a insertar
     */
    public void insertaLastOrdenada(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato); // Creamos un nuevo nodo que contiene el dato que queremos insertar

        if (primero == null) { // Si la lista está vacía
            primero = nuevoNodo; // El nuevo nodo se convierte en el primer nodo de la lista
        } else {
            Nodo<T> actual = primero; // Creamos un nodo auxiliar para recorrer la lista desde el inicio

            if (dato.compareTo(primero.dato) < 0) { // Si el nuevo dato es menor que el primer dato
                nuevoNodo.enlace = primero; // El nuevo nodo apunta al nodo que antes era el primero
                primero = nuevoNodo; // Ahora el nuevo nodo es el primer nodo de la lista
                return; // Terminamos porque ya insertamos
            }

            actual = primero; // Inicializamos 'actual' para recorrer desde el primer nodo

            // Avanzamos mientras no lleguemos al final de la lista y el dato a insertar sea
            // mayor que el dato siguiente
            while (actual.enlace != null && dato.compareTo(actual.enlace.dato) > 0) {
                actual = actual.enlace; // Avanzamos al siguiente nodo
            }

            // Insertamos el nuevo nodo entre 'actual' y 'actual.enlace'
            nuevoNodo.enlace = actual.enlace; // El nuevo nodo apunta al siguiente de 'actual'
            actual.enlace = nuevoNodo; // 'actual' ahora apunta al nuevo nodo
        }
    }

    /*
     * public void insertarAlFinal(T dato) {
     * Nodo<T> nuevoNodo = new Nodo<>(dato); // Creamos un nuevo nodo con el dato
     * dado
     * if (primero == null) { // Si la lista está vacía
     * primero = nuevoNodo; // El nuevo nodo se convierte en el primero
     * } else {
     * Nodo<T> actual = primero; // Nodo actual para recorrer desde el principio
     * while (actual.enlace != null) { // Mientras haya un siguiente nodo
     * actual = actual.enlace; // Avanzamos al siguiente
     * }
     * actual.enlace = nuevoNodo; // Enlazamos el último nodo al nuevo nodo
     * }
     * }
     */

    /**
     * Elimina un nodo que contenga el dato especificado
     * 
     * @param dato Dato del nodo a eliminar
     * @return true si se eliminó exitosamente, false si no se encontró
     */
    public boolean eliminar(T dato) {
        if (primero == null) // Si la lista está vacía, no hay nada que eliminar
            return false;

        if (primero.dato.equals(dato)) { // Caso especial: eliminar el primer nodo
            primero = primero.enlace; // El primer nodo ahora será el siguiente
            return true;
        }

        Nodo<T> actual = primero; // Nodo actual para buscar el nodo a eliminar
        while (actual.enlace != null && !actual.enlace.dato.equals(dato)) {
            actual = actual.enlace; // Avanzamos al siguiente nodo
        }

        if (actual.enlace != null) { // Si encontramos el nodo
            actual.enlace = actual.enlace.enlace; // Saltamos el nodo eliminado
            return true;
        }

        return false; // No se encontró el nodo a eliminar
    }

    /**
     * Verifica si un dato está en la lista
     * 
     * @param dato Dato a buscar
     * @return true si existe, false si no
     */
    public boolean contiene(T dato) {
        Nodo<T> actual = primero; // Empezamos desde el primer nodo
        while (actual != null) { // Mientras haya nodos
            if (actual.dato.equals(dato)) { // Si encontramos el dato
                return true;
            }
            actual = actual.enlace; // Avanzamos al siguiente nodo
        }
        return false; // No se encontró el dato
    }

    /**
     * Invierte el orden de la lista enlazada
     */
    public void invertir() {
        Nodo<T> anterior = null; // Nodo previo al actual, inicialmente ninguno
        Nodo<T> actual = primero; // Nodo actual que estamos procesando
        Nodo<T> siguiente = null; // Nodo siguiente al actual (para no perder la referencia)

        while (actual != null) { // Mientras no lleguemos al final
            siguiente = actual.enlace; // Guardamos el siguiente nodo
            actual.enlace = anterior; // Invertimos el enlace del nodo actual
            anterior = actual; // Avanzamos anterior (lo movemos al actual)
            actual = siguiente; // Avanzamos actual (lo movemos al que habíamos guardado)
        }

        primero = anterior; // Al terminar, el anterior será el nuevo primer nodo
    }

    /**
     * Obtiene el dato con más prioridad (el menor según compareTo)
     * Solo funciona si los elementos son comparables
     * 
     * @return Dato más prioritario o null si la lista está vacía
     */
    @SuppressWarnings("unchecked")
    public T obtenerMasPrioritario() {
        if (primero == null) // Si la lista está vacía, no hay prioridad
            return null;

        Nodo<T> actual = primero; // Nodo actual para recorrer desde el principio
        T maxima = actual.dato; // Inicialmente, el primer dato es el de mayor prioridad

        while (actual != null) { // Recorremos todos los nodos
            if (actual.dato instanceof Comparable) { // Solo comparamos si es Comparable
                if (((Comparable<T>) actual.dato).compareTo(maxima) < 0) {
                    maxima = actual.dato; // Actualizamos la máxima si encontramos una más prioritaria
                }
            }
            actual = actual.enlace; // Avanzamos al siguiente nodo
        }

        return maxima; // Devolvemos el dato más prioritario
    }

    /**
     * Imprime todos los elementos de la lista
     */
    public void imprimir() {
        Nodo<T> actual = primero; // Nodo actual para recorrer desde el principio
        while (actual != null) { // Mientras existan nodos
            System.out.println("- " + actual.dato); // Imprimimos el dato
            actual = actual.enlace; // Avanzamos al siguiente nodo
        }
    }
}
