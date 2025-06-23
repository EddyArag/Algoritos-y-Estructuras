package SESION11;

import java.util.LinkedList;

public class HashO {
    private LinkedList<Register>[] tabla;
    private int size;

    public HashO(int size) {
        this.size = size;
        tabla = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(Register reg) {
        int pos = hash(reg.getKey());
        tabla[pos].add(reg);
        System.out.println("Insertado en índice " + pos);
    }

    public Register search(int key) {
        int pos = hash(key);
        for (Register reg : tabla[pos]) {
            if (reg.getKey() == key) {
                System.out.println("Encontrado en índice " + pos);
                return reg;
            }
        }
        System.out.println("Clave " + key + " no encontrada");
        return null;
    }

    public void delete(int key) {
        int pos = hash(key);
        for (Register reg : tabla[pos]) {
            if (reg.getKey() == key) {
                tabla[pos].remove(reg);
                System.out.println("Clave " + key + " eliminada de índice " + pos);
                return;
            }
        }
        System.out.println("Clave " + key + " no encontrada para eliminar");
    }

    public void printTable() {
        System.out.println("\nContenido de la tabla hash (abierta):");
        for (int i = 0; i < size; i++) {
            System.out.print("Índice " + i + ": ");
            if (tabla[i].isEmpty()) {
                System.out.println("---");
            } else {
                for (Register reg : tabla[i]) {
                    System.out.print(reg + " -> ");
                }
                System.out.println("null");
            }
        }
    }
}
