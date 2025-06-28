package SESION11.Ejercicios;
public class TestEjercicio2 {
    public static void main(String[] args) {
        int size = 6;
        Integer[] tabla = new Integer[size];
        int[] valores = {12, 18, 24, 30};
        for (int v : valores) {
            int pos = v % size;
            int original = pos;
            System.out.print("Insertando " + v + ": ");
            while (tabla[pos] != null) {
                System.out.print("Colisión en " + pos + " -> ");
                pos = (pos + 1) % size;
            }
            tabla[pos] = v;
            System.out.println("Insertado en " + pos);
        }
        System.out.println("\nTabla hash final:");
        for (int i = 0; i < size; i++) {
            System.out.println(i + ": " + (tabla[i] != null ? tabla[i] : "---"));
        }
    }
}