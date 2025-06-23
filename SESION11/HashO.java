import SESION11.LinkedList.LinkedList;
import SESION11.LinkedList.ExceptionIsEmpty;

public class HashO<E> {
    private LinkedList<Register<E>>[] tabla;
    private int size;

    @SuppressWarnings("unchecked")
    public HashO(int size) {
        this.size = size;
        this.tabla = new LinkedList[size];

        for (int i = 0; i < size; i++) {
            tabla[i] = new LinkedList<>();
        }
    }

    private int hash(int key) {
        return key % size;
    }

    public void insert(Register<E> reg) {
        int pos = hash(reg.getKey());
        tabla[pos].addLast(reg);
        System.out.println("Insertado en índice " + pos);
    }

    public Register<E> search(int key) {
        int pos = hash(key);
        try {
            for (int i = 0; i < tabla[pos].length(); i++) {
                Register<E> reg = tabla[pos].get(i);
                if (reg.getKey() == key) {
                    System.out.println("Encontrado en índice " + pos);
                    return reg;
                }
            }
        } catch (ExceptionIsEmpty e) {
            // La lista está vacía, no hay nada que buscar
        }
        System.out.println("Clave " + key + " no encontrada");
        return null;
    }

    public void delete(int key) {
        int pos = hash(key);
        try {
            for (int i = 0; i < tabla[pos].length(); i++) {
                Register<E> reg = tabla[pos].get(i);
                if (reg.getKey() == key) {
                    tabla[pos].removeNode(reg);
                    System.out.println("Clave " + key + " eliminada de índice " + pos);
                    return;
                }
            }
        } catch (ExceptionIsEmpty e) {
            // La lista está vacía, no hay nada que eliminar
        }
        System.out.println("Clave " + key + " no encontrada para eliminar");
    }

    public void printTable() {
        System.out.println("\nContenido de la tabla hash (abierta):");
        for (int i = 0; i < size; i++) {
            System.out.print("Índice " + i + ": ");
            try {
                if (tabla[i].isEmptyList()) {
                    System.out.println("---");
                } else {
                    for (int j = 0; j < tabla[i].length(); j++) {
                        System.out.print(tabla[i].get(j) + " -> ");
                    }
                    System.out.println("null");
                }
            } catch (ExceptionIsEmpty e) {
                System.out.println("---");
            }
        }
    }
}