package SESION08;

import SESION07.Ejercicio02.ExceptionIsEmptyejer2;
import SESION08.ItemDuplicated;

public class TestEjer2 {
    public static void main(String[] args) throws ExceptionIsEmptyejer2, ItemDuplicated {
        System.out.println("=== TEST AVL CON ELIMINACIONES QUE REQUIEREN ROTACIONES ===");
        AVLTreeWithRemove<Integer> avl = new AVLTreeWithRemove<>();

        // Caso 1: Eliminación que requiere rotación simple izquierda
        System.out.println("\nCaso 1: Eliminación con rotación simple izquierda");
        avl.insertAVL(30);
        avl.insertAVL(20);
        avl.insertAVL(40);
        avl.insertAVL(35);
        avl.insertAVL(50); // Estructura que al eliminar 20 requerirá rotación

        System.out.println("\nÁrbol AVL inicial:");
        avl.drawAVL();
        /*
         * Debería mostrar:
         * 30
         * / \
         * 20 40
         * / \
         * 35 50
         */
        System.out.println("Altura: " + avl.height());

        System.out.println("\nEliminando 20...");
        avl.remove(20);
        avl.drawAVL();
        /*
         * Debería mostrar:
         * 40
         * / \
         * 30 50
         * \
         * 35
         */
        System.out.println("Altura después: " + avl.height());

        // Caso 2: Eliminación que requiere rotación doble (derecha-izquierda)
        System.out.println("\nCaso 2: Eliminación con rotación doble derecha-izquierda");
        avl = new AVLTreeWithRemove<>();
        avl.insertAVL(30);
        avl.insertAVL(20);
        avl.insertAVL(40);
        avl.insertAVL(35);
        avl.insertAVL(50);
        avl.insertAVL(33); // Estructura que al eliminar 50 requerirá rotación doble

        System.out.println("\nÁrbol AVL inicial:");
        avl.drawAVL();
        /*
         * Debería mostrar:
         * 30
         * / \
         * 20 40
         * / \
         * 35 50
         * /
         * 33
         */
        System.out.println("Altura: " + avl.height());

        System.out.println("\nEliminando 50...");
        avl.remove(50);
        avl.drawAVL();
        /*
         * Debería mostrar:
         * 35
         * / \
         * 30 40
         * / \ /
         * 20 33 35
         */
        System.out.println("Altura después: " + avl.height());

        // Caso 3: Eliminación del nodo raíz que requiere rebalanceo
        System.out.println("\nCaso 3: Eliminación del nodo raíz con rebalanceo");
        avl = new AVLTreeWithRemove<>();
        avl.insertAVL(30);
        avl.insertAVL(20);
        avl.insertAVL(40);
        avl.insertAVL(10);
        avl.insertAVL(25);
        avl.insertAVL(35);
        avl.insertAVL(50);
        avl.insertAVL(5); // Estructura que al eliminar 30 requerirá rebalanceo

        System.out.println("\nÁrbol AVL inicial:");
        avl.drawAVL();
        /*
         * Debería mostrar:
         * 30
         * / \
         * 20 40
         * / \ / \
         * 10 25 35 50
         * /
         * 5
         */
        System.out.println("Altura: " + avl.height());

        System.out.println("\nEliminando 30 (raíz)...");
        avl.remove(30);
        avl.drawAVL();
        /*
         * Debería mostrar:
         * 35
         * / \
         * 20 40
         * / \ \
         * 10 25 50
         * /
         * 5
         */
        System.out.println("Altura después: " + avl.height());

        // Caso 4: Eliminación que no requiere rotación
        System.out.println("\nCaso 4: Eliminación sin necesidad de rotación");
        System.out.println("\nEliminando 5...");
        avl.remove(5);
        avl.drawAVL();
        /*
         * Debería mostrar:
         * 35
         * / \
         * 20 40
         * / \ \
         * 10 25 50
         */
        System.out.println("Altura después: " + avl.height());
    }
}