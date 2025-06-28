package SESION11.Ejercicios;
public class TestEjercicio4 {
    public static void main(String[] args) {
        int size = 7;
        Integer[] tabla = new Integer[size];
        int[] valores = {5, 12, 19};
        for (int v : valores) {
            int pos = v % size;
            while (tabla[pos] != null) {
                pos = (pos + 1) % size;
            }
            tabla[pos] = v;
        }
        // Eliminar clave 12 (eliminación lógica: marcar como -1)
        int eliminar = 12;
        int pos = eliminar % size;
        while (tabla[pos] != null && tabla[pos] != eliminar) {
            pos = (pos + 1) % size;
        }
        if (tabla[pos] != null && tabla[pos] == eliminar) {
            tabla[pos] = -1; // Eliminación lógica
            System.out.println("Clave 12 eliminada lógicamente en posición " + pos);
        }
        // Buscar clave 19 después de eliminar 12
        int buscar = 19;
        pos = buscar % size;
        boolean encontrado = false;
        int start = pos;
        do {
            if (tabla[pos] != null && tabla[pos] == buscar) {
                encontrado = true;
                break;
            }
            pos = (pos + 1) % size;
        } while (tabla[pos] != null && pos != start);
        System.out.println("Buscar clave 19: " + (encontrado ? "Encontrado" : "No encontrado"));
    }
}