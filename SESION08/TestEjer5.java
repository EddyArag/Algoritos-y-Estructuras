package SESION08;

import SESION07.Ejercicio02.ExceptionIsEmptyejer2;
import SESION08.ItemDuplicated;

public class TestEjer5 {
    public static void main(String[] args) {
        try {
            AVLTreeWithPreorder<Integer> avl = new AVLTreeWithPreorder<>();

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
            avl.drawAVL(); // Dibuja el árbol AVL para visualizar su estructura

            // Realizar el recorrido en preorden
            avl.preOrderTraversal(); // Se espera que imprima: 50 30 20 10 25 40 70 60 65 80

        } catch (ExceptionIsEmptyejer2 | ItemDuplicated e) {
            System.out.println(e.getMessage());
        }
    }
}
