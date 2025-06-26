package SESION10;

import java.util.ArrayList;

/**
 * Clase que representa un nodo de un árbol B.
 * 
 * @param <E> Tipo de dato que almacena el nodo (debe ser comparable).
 */
public class BNode<E> {
    // Lista de claves almacenadas en el nodo
    protected ArrayList<E> keys;
    // Lista de hijos del nodo
    protected ArrayList<BNode<E>> childs;
    // Número de claves actualmente en el nodo
    protected int count;

    /**
     * Constructor de un nodo B.
     * 
     * @param n Número máximo de claves (orden del árbol).
     */
    public BNode(int n) {
        this.keys = new ArrayList<E>(n);
        this.childs = new ArrayList<BNode<E>>(n);
        this.count = 0;
        // Inicializa las listas con valores nulos
        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
    }

    /**
     * Verifica si el nodo está lleno (tiene el número máximo de claves).
     * 
     * @param maxKeys Número máximo de claves permitidas.
     * @return true si está lleno, false en caso contrario.
     */
    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
    }

    /**
     * Verifica si el nodo está vacío (sin claves).
     */
    public boolean nodeEmpty() {
        return count == 0;
    }

    /**
     * Busca una clave en el nodo.
     * 
     * @param key Clave a buscar.
     * @param pos Arreglo de tamaño 1 donde se almacena la posición encontrada o
     *            donde debería insertarse.
     * @return true si la clave existe en el nodo, false en caso contrario.
     */
    public boolean searchNode(E key, int pos[]) {
        pos[0] = 0;
        // Busca la posición donde debería estar la clave
        while (pos[0] < count && ((Comparable<E>) key).compareTo(keys.get(pos[0])) > 0) {
            pos[0]++;
        }
        // Si la clave está en la posición encontrada
        if (pos[0] < count && ((Comparable<E>) key).compareTo(keys.get(pos[0])) == 0) {
            return true;
        } else {
            return false;
        }
    }
}