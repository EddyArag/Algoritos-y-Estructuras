package SESION11;

public class TestHashC {
    public static void main(String[] args) {
        HashC<String> hashTable = new HashC<>(11);
        try {
            hashTable.insert(new Register<>(34, "Juan"));
            hashTable.insert(new Register<>(3, "Ana"));
            hashTable.insert(new Register<>(7, "Luis"));
            hashTable.insert(new Register<>(30, "Maria"));
            hashTable.insert(new Register<>(11, "Pedro"));
            hashTable.insert(new Register<>(8, "Sofia"));
            hashTable.insert(new Register<>(7, "Carlos")); // clave repetida, diferente nombre
            hashTable.insert(new Register<>(23, "Lucia"));
            hashTable.insert(new Register<>(41, "Miguel"));
            hashTable.insert(new Register<>(16, "Elena"));
            hashTable.insert(new Register<>(34, "Paula")); // clave repetida, diferente nombre
        } catch (ExceptionIsFull e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Tabla hash antes de eliminar la clave 30:");
        hashTable.printTable();

        // Eliminar clave 30
        hashTable.delete(30);
        System.out.println("\nTabla hash después de eliminar la clave 30:");
        hashTable.printTable();

        // Buscar clave 23
        Register<String> result = hashTable.search(23);
        if (result != null) {
            System.out.println("\nRegistro encontrado para clave 23: " + result);
        } else {
            System.out.println("\nNo se encontró registro para clave 23");
        }
    }
}
