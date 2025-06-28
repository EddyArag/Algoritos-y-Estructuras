package SESION11.Ejercicios;

import SESION11.Register;

public class TestEjercicio3 {
    public static void main(String[] args) {
        int size = 5;
        java.util.LinkedList<Register<String>>[] tabla = new java.util.LinkedList[size];
        for (int i = 0; i < size; i++) tabla[i] = new java.util.LinkedList<>();
        Register<String>[] datos = new Register[] {
            new Register<>(10, "Juan"),
            new Register<>(15, "Ana"),
            new Register<>(20, "Luis"),
            new Register<>(25, "Rosa")
        };
        for (Register<String> r : datos) {
            int pos = r.getKey() % size;
            tabla[pos].add(r);
        }
        // Buscar clave 20
        int buscar = 20;
        int pos = buscar % size;
        Register<String> encontrado = null;
        for (Register<String> r : tabla[pos]) {
            if (r.getKey() == buscar) encontrado = r;
        }
        System.out.println("Buscar clave 20: " + (encontrado != null ? encontrado.getValue() : "No encontrado"));
        // Buscar clave 30
        buscar = 30;
        pos = buscar % size;
        encontrado = null;
        for (Register<String> r : tabla[pos]) {
            if (r.getKey() == buscar) encontrado = r;
        }
        System.out.println("Buscar clave 30: " + (encontrado != null ? encontrado.getValue() : "No encontrado"));
    }
}
