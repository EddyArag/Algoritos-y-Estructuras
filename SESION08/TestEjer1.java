package SESION08;

import SESION07.Actividad01.Arbol;
import SESION08.ItemDuplicated;

public class TestEjer1 {
    public static void main(String[] args) {
        try {
            // Caso 1: Inserción en orden ascendente
            System.out.println("--- Caso 1: Inserción ordenada ---");

            // Prueba con BST
            Arbol<Integer> bst = new Arbol<>();
            for (int i = 1; i <= 10; i++) {
                bst.insert(i);
            }
            System.out.println("Altura BST: " + bst.height());

            // Prueba con AVL
            ArbolAVL<Integer> avl = new ArbolAVL<>();
            for (int i = 1; i <= 10; i++) {
                avl.insertAVL(i);
            }
            System.out.println("Altura AVL: " + avl.height());

            // Caso 2: Búsqueda en árbol grande
            System.out.println("\n--- Caso 2: Tiempo de búsqueda ---");

            // BST grande desbalanceado
            Arbol<Integer> bstLarge = new Arbol<>();
            for (int i = 1; i <= 10000; i++) {
                bstLarge.insert(i);
            }

            // AVL grande balanceado
            ArbolAVL<Integer> avlLarge = new ArbolAVL<>();
            for (int i = 1; i <= 10000; i++) {
                avlLarge.insertAVL(i);
            }

            // Medir tiempo de búsqueda (esto es una aproximación)
            long start = System.nanoTime();
            bstLarge.search(10000);
            long end = System.nanoTime();
            System.out.println("BST search time (ns): " + (end - start));

            start = System.nanoTime();
            avlLarge.search(10000);
            end = System.nanoTime();
            System.out.println("AVL search time (ns): " + (end - start));

        } catch (ItemDuplicated e) {
            System.err.println(e.getMessage());
        }
    }
}