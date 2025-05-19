package SESION08;

import SESION07.Ejercicio02.ArbolEjercicio2;
import SESION07.Ejercicio02.ExceptionDuplicateejer2;
import SESION07.Ejercicio02.ExceptionIsEmptyejer2;
import SESION08.ArbolAVL;

public class TestEjer1 {
    public static void main(String[] args) throws ExceptionIsEmptyejer2, ItemDuplicated, ExceptionDuplicateejer2 {
        // Caso 1: Inserción en orden ascendente
        System.out.println("Caso 1: Inserción en orden ascendente");
        ArbolEjercicio2<Integer> bst = new ArbolEjercicio2<>();
        ArbolAVL<Integer> avl = new ArbolAVL<>();

        // Insertar elementos en orden ascendente
        /*
         * for (int i = 1; i <= 10; i++) {
         * try {
         * bst.insert(i);
         * avl.insertAVL(i);
         * } catch (ExceptionDuplicateejer2 | ItemDuplicated e) {
         * System.err.println(e.getMessage());
         * }
         * }
         */
        bst.insert(50);
        bst.insert(30);
        bst.insert(60);

        avl.insertAVL(50);
        avl.insertAVL(40);
        avl.insertAVL(45);

        // Mostrar alturas
        System.out.println("Altura del BST: " + bst.height(1)); // Altura del nodo raíz
        System.out.println("Altura del AVL: " + avl.height());

        // Dibujar los árboles
        System.out.println("\nDibujo del BST:");
        try {
            bst.drawBST();
        } catch (ExceptionIsEmptyejer2 e) {
            System.err.println(e.getMessage());
        }

        System.out.println("\nDibujo del AVL:");
        avl.drawAVL(); // Llamada al método drawAVL()

        // Caso 2: Tiempo de búsqueda
        System.out.println("\nCaso 2: Tiempo de búsqueda en árboles grandes");
        long startTime, endTime;

        // Crear un árbol BST grande
        ArbolEjercicio2<Integer> largeBst = new ArbolEjercicio2<>();
        ArbolAVL<Integer> largeAvl = new ArbolAVL<>();
        for (int i = 1; i <= 10000; i++) {
            try {
                largeBst.insert(i);
                largeAvl.insertAVL(i);
            } catch (ExceptionDuplicateejer2 | ItemDuplicated e) {
                System.err.println(e.getMessage());
            }
        }

        // Medir tiempo de búsqueda en BST
        startTime = System.nanoTime();
        boolean foundInBst = largeBst.search(largeBst.root, 5000) != null;
        endTime = System.nanoTime();
        System.out.println("Buscar 5000 en BST: " + foundInBst + " (Tiempo: " + (endTime - startTime) + " ns)");

        // Medir tiempo de búsqueda en AVL
        startTime = System.nanoTime();
        boolean foundInAvl = largeAvl.search(5000);
        endTime = System.nanoTime();
        System.out.println("Buscar 5000 en AVL: " + foundInAvl + " (Tiempo: " + (endTime - startTime) + " ns)");
    }
}