package SESION11.Ejercicios;
public class TestEjercicio1 {
    public static void main(String[] args) {
        int size = 7;
        int[] valores = {3, 10, 17, 24};
        Integer[] tabla = new Integer[size];
        for (int v : valores) {
            int pos = v % size;
            tabla[pos] = v;
        }
        System.out.println("Tabla hash final:");
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + (tabla[i] != null ? tabla[i] : "---"));
        }
    }
}