package SESION11;
import java.util.ArrayList;
import java.util.List;

public class HashC<E extends Comparable<E>> {
    private static class Element<E extends Comparable<E>> {
        Register<E> register;
        int isAvailable;
        // 1: Elemento lleno
        // 0: Elemento vacío
        // -1: Elemento vacío pero estaba lleno

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

    public void insert(Register<E> reg) throws ExceptionIsFull {
        int key = reg.getKey();
        int pos = linearProbing(hash(key));
        if(pos == -1) {
            throw new ExceptionIsFull("La tabla está llena.");
        } else {
            table[pos].register = reg;
            table[pos].isAvailable = 1; // Marcar como ocupado
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
        } while (pos != start);
        return null;
    }

    public void delete(int key) {
        int pos = hash(key);
        int start = pos;
        do {
            if (table[pos].isAvailable == 1 && table[pos].register.getKey() == key) {
                table[pos].isAvailable = -1; // Marcar como borrado
                table[pos].register = null;
                System.out.println("Se eliminó el registro con la clave " + key);
                return;
            }
            pos = (pos + 1) % size;
        } while (pos != start);
        System.out.println("No se encontró la clave " + key + " para eliminar");
    }

    public void printTable() {
        System.out.println("\nEstado de la tabla:");
        for (int pos = 0; pos < size; pos++) {
            if (table[pos].isAvailable == 1) {
                System.out.println(table[pos].register.toString());
            }
        }
    }

    // Método público para obtener el tamaño de la tabla
    public int getTableSize() {
        return size;
    }

    // Método público para obtener el registro en una posición específica
    public Register<E> getRegisterAt(int pos) {
        if (pos >= 0 && pos < size && table[pos].isAvailable == 1) {
            return table[pos].register;
        }
        return null;
    }
}