package SESION11;
import SESION11.LinkedList.*;

public class HashO<E extends Comparable<E>> {
    private static class Element<T extends Comparable<T>> {
        LinkedList<T> list;

        public Element() {
            this.list = new LinkedList<>();
        }
    }
    private Element<Register<E>>[] table;
    private int size;

    public HashO(int size) {
        this.size = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element<>();
        }
    }

    public int getSize() {
        return size;
    }

    public LinkedList<Register<E>> getBucket(int index) {
        return table[index].list;
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(Register<E> reg) {
        int key = reg.getKey();
        int pos = hash(key);
        table[pos].list.addLast(reg);
        System.out.println("Insertado en posición " + pos);
    }

    public Register<E> search(int key, E value) {
        int pos = hash(key);
        try {
            int index = table[pos].list.search(new Register<>(key, value));
            if (index != -1) {
                System.out.println("Encontrado en posición " + pos + ", índice " + index);
                return table[pos].list.get(index);
            } else {
                System.out.println("No se encontró el registro con clave " + key + " y valor " + value);
                return null;
            }
        } catch (ExceptionIsEmpty e) {
            System.out.println("La lista en la posición " + pos + " está vacía.");
            return null;
        }
    }

    public void delete(int key, E value) {
    int pos = hash(key);
    try {
        table[pos].list.removeNode(new Register<>(key, value));
        System.out.println("Se eliminó un registro con clave " + key + " en posición " + pos);
    } catch (ExceptionIsEmpty e) {
        System.out.println("La lista en la posición " + pos + " está vacía.");
    }
}


    public void printTable() {
        System.out.println("\nEstado de la tabla:");
        for (int i = 0; i < size; i++) {
            System.out.print("Posición " + i + ": ");
            if (table[i].list.isEmptyList()) {
                System.out.println("---");
            } else {
                System.out.println(table[i].list.toString());
            }
        }
    }
}
