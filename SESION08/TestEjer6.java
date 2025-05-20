package SESION08;

import SESION07.Ejercicio02.ExceptionIsEmptyejer2;
import SESION08.ItemDuplicated;

public class TestEjer6 {
    public static void main(String[] args) throws ExceptionIsEmptyejer2, ItemDuplicated {
        System.out.println("=== TEST AVL CON INSERCIONES Y ELIMINACIONES QUE REQUIEREN ROTACIONES ===");
        AVLTreeWithRemove<Integer> avl = new AVLTreeWithRemove<>();

        // Caso 1: Inserciones que requieren rotación simple izquierda
        System.out.println("\nCaso 1: Inserciones que requieren rotación simple izquierda");
        avl.insertAVL(30);
        avl.insertAVL(20);
        avl.insertAVL(40);
        avl.insertAVL(35);
        avl.insertAVL(50); // Estructura que al eliminar 20 requerirá rotación

        System.out.println("\nÁrbol AVL inicial:");
        avl.drawAVL();
        System.out.println("Altura: " + avl.height());

        System.out.println("\nEliminando 20...");
        avl.remove(35);
        System.out.println("Árbol AVL después de eliminar 20:");
        avl.drawAVL();
        System.out.println("Altura después: " + avl.height());

        // Caso 2: Eliminación que requiere rotación doble derecha-izquierda (ajustado
        // para rotación real)
        System.out.println("\nCaso 2: Eliminación que requiere rotación doble derecha-izquierda");
        avl = new AVLTreeWithRemove<>();
        // Inserciones para crear forma que provoque rotación doble derecha-izquierda
        avl.insertAVL(50);
        avl.insertAVL(30);
        avl.insertAVL(70);
        avl.insertAVL(60);
        avl.insertAVL(80);
        avl.insertAVL(55); // hijo izquierdo del 60, provoca desequilibrio cruzado
        avl.insertAVL(65); // hijo derecho del 60
        avl.insertAVL(58); // inserción para aumentar peso en subárbol izquierdo de 60

        System.out.println("\nÁrbol AVL inicial:");
        avl.drawAVL();
        System.out.println("Altura: " + avl.height());

        System.out.println("\nEliminando 80...");
        avl.remove(80);
        System.out.println("Árbol AVL después de eliminar 80:");
        avl.drawAVL();
        System.out.println("\nEliminando 70... (debería provocar rotación doble derecha-izquierda)");
        avl.remove(70);
        System.out.println("Árbol AVL después de eliminar 70:");
        avl.drawAVL();
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
        System.out.println("Altura: " + avl.height());

        System.out.println("\nEliminando 30 (raíz)...");
        avl.remove(30);
        System.out.println("Árbol AVL después de eliminar 30:");
        avl.drawAVL();
        System.out.println("Altura después: " + avl.height());

        // Caso 4: Eliminación que no requiere rotación
        System.out.println("\nCaso 4: Eliminación sin necesidad de rotación");
        System.out.println("\nEliminando 5...");
        avl.remove(5);
        System.out.println("Árbol AVL después de eliminar 5:");
        avl.drawAVL();
        System.out.println("Altura después: " + avl.height());
    }
}
