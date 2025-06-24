package SESION11;
import java.util.ArrayList;
import java.util.List;

public class HashC<E extends Comparable<E>> {
    private static class Element<E extends Comparable<E>> {
        Register<E> register;
        int isAvailable;
        //1: Elemento lleno
        //0: Elemento vacío
        //-1: Elemento vacío pero estaba lleno

        public Element() {
            this.register = null;
            this.isAvailable = 0;
        }
    }

    private Element<E>[] table;
    private int size;

    public HashC(int size) {
        this.size = size;
        this.table = new Element[size];
        for (int i = 0; i < size; i++) {
            table[i] = new Element<>();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public int linearProbing(int pos) {
        int start = pos;
        do {
            if(table[pos].isAvailable == 0) {
                return pos;
            }
            pos = (pos + 1) % size;
        } while (pos != start);
        return -1;
    }
    public void insert(Register<E> reg) throws ExceptionIsFull{
        int key = reg.getKey();
        int pos = linearProbing(hash(key));
        if(pos == -1) {
            throw new ExceptionIsFull("La tabla está llena.");
        } else {
            table[pos].register = reg;
        }
    }

    public Register<E> search(int key) {
        int pos = hash(key);
        int start = pos;
        do {
            if (table[pos].isAvailable == 1 && table[pos].register.getKey() == key) {
                return table[pos].register;
            }
            pos = (pos + 1) % size;
        } while (pos != start || table[pos].isAvailable == 0);
        return null;
    }

    public void delete(int key) {
        int pos = hash(key);
        int start = pos;
        do {
            if (table[pos].isAvailable == 1 && table[pos].register.getKey() == key) {
                
                System.out.println("Se eliminó el registro con la clase " + key);
                return;
            }
            pos = (pos + 1) % size;
        } while (pos != start || table[pos].isAvailable == 0);
        System.out.println("No se encontró la clave " + key + " para eliminar");
    }

    public void printTable() {
        System.out.println("\nEstado de la tabla:");
        int pos = 0;
        do {
            if (table[pos].isAvailable == 1) {
                System.out.println(table[pos].register.toString());
                return;
            }
            pos = (pos + 1) % size;
        } while (pos != 0 || table[pos].isAvailable == 0);
    }
}