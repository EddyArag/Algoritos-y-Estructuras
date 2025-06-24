package SESION11;
import SESION11.LinkedList.*;

public class HashO<E> {
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

    public Register<E> search(int key) {
        int pos = hash(key);
        Node<Register<E>> current = table[pos].list.getFirst();
        while (current != null) {
            if (current.getElemento().getKey() == key) {
                System.out.println("Encontrado en posición " + pos);
                return current.getElemento();
            }
            current = current.getNext();
        }
        System.out.println("No se encontró la clave " + key);
        return null;
    }

    public void delete(int key) {
        int pos = hash(key);
        Node<Register<E>> current = table[pos].list.getFirst();
        Node<Register<E>> prev = null;

        Node<Register<E>> lastMatch = null;
        Node<Register<E>> lastMatchPrev = null;

        while (current != null) {
            if (current.getElemento().getKey() == key) {
                lastMatch = current;
                lastMatchPrev = prev;
            }
            prev = current;
            current = current.getNext();
        }

        if (lastMatch != null) {
            if (lastMatchPrev == null) {
                table[pos].list.setFirst(lastMatch.getNext());
            } else {
                lastMatchPrev.setNext(lastMatch.getNext());
            }
            System.out.println("Se eliminó el último registro con clave " + key + " en posición " + pos);
        } else {
            System.out.println("No se encontró la clave " + key + " para eliminar");
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
