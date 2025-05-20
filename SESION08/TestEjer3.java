package SESION08;

import SESION07.Ejercicio02.ExceptionIsEmptyejer2;
import SESION08.ItemDuplicated;

public class TestEjer3 {
    public static void main(String[] args) {
        try {
            AVLTreeWithLevelOrder<Integer> avl = new AVLTreeWithLevelOrder<>();

            // Insertar nodos para formar un árbol como en la figura y ejemplo dado
            avl.insertAVL(50);
            avl.insertAVL(30);
            avl.insertAVL(70);
            avl.insertAVL(20);
            avl.insertAVL(40);
            avl.insertAVL(60);
            avl.insertAVL(80);
            avl.insertAVL(10);
            avl.insertAVL(25);
            avl.insertAVL(65);
            avl.drawAVL();
            System.out.println("Recorrido por amplitud (BFS) del árbol AVL:");
            avl.levelOrderTraversal(); // Se espera que imprima: 50 30 70 20 40 60 80 10 25 65

        } catch (ExceptionIsEmptyejer2 | ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
    }
}
