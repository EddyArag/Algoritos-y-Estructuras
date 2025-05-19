package SESION08;

import SESION08.ItemDuplicated;

public class TestEjer2 {
    public static void main(String[] args) {
        try {
            AVLTreeWithRemove<Integer> avl = new AVLTreeWithRemove<>();

            // Insertar elementos
            int[] values = {10, 20, 30, 40, 50, 25};
            for (int val : values) {
                avl.insertAVL(val);
            }

            System.out.println("Árbol AVL inicial:");
            System.out.println("Altura: " + avl.height());

            // Eliminar elementos
            avl.remove(20);
            avl.remove(30);

            System.out.println("\nÁrbol AVL después de eliminar 20 y 30:");
            System.out.println("Altura: " + avl.height());

        } catch (ItemDuplicated e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}