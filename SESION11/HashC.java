package SESION11;

public class HashC {
    private static class Element {
        Register register;
        boolean isAvailable;

        public Element() {
            this.register = null;
            this.isAvailable = true;
        }
    }

    private Element[] table;
    private int size;

    public HashC(int size) {
        this.size=size;this.table=new Element[size];
        for(int i = 0; i < size; i++) {
            table[i] = new Element();
        }
    }


    

    public void insert(Register reg) {
        int key = reg.getKey();
        int pos = hash(key);
        int start = pos;
        do {
            if (table[pos].isAvailable) {
                table[pos].register = reg;
                table[pos].isAvailable = false;
                System.out.println("Insertado en posición " + pos);
                return;
            }
            pos = (pos + 1) % size;
        } while (pos != start);

        System.out.println("Error: tabla llena");
    }

    public Register search(int key) {
        int pos = hash(key);
        int start = pos;
        do {
            if (!table[pos].isAvailable && table[pos].register.getKey() == key) {
                System.out.println("Encontrado en posición " + pos);
                return table[pos].register;
            }
            pos = (pos + 1) % size;
        } while (pos != start);
        System.out.println("No se encontró la clave " + key);
        return null;
    }

    public void delete(int key) {
        int pos = hash(key);
        int start = pos;
        do {
            if (!table[pos].isAvailable && table[pos].register != null && table[pos].register.getKey() == key) {
                table[pos].register = null;
                table[pos].isAvailable = true;
                System.out.println("Clave " + key + " eliminada lógicamente en posición " + pos);
                return;
            }
            pos = (pos + 1) % size;
        } while (pos != start);
        System.out.println("No se encontró la clave " + key + " para eliminar");
    }

    public void printTable() {
        System.out.println("\nEstado de la tabla:");
        for (int i = 0; i < size; i++) {
            if (!table[i].isAvailable && table[i].register != null) {
                System.out.println("Posición " + i + ": " + table[i].register);
            } else {
                System.out.println("Posición " + i + ": ---");
            }
        }
    }
}