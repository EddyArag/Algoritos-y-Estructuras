package SESION07.Ejercicio03;

import SESION07.Actividad01.*;

public class ArbolEjer3<E extends Comparable<E>> extends Arbol<E> {

    public void parenthesize() {
        parenthesizeR(super.root, 0); // Accede al root de la clase padre
    }

    private void parenthesizeR(Node<E> root, int nivel) {
        if (root == null)
            return;

        // Imprime la sangría
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        // Imprime el nodo
        System.out.print(root.getElem());

        // Verifica si el nodo tiene hijos
        if (root.getLeft() != null || root.getRight() != null) {
            System.out.println(" (");
            // Llamadas recursivas para los hijos, respetando la jerarquía
            if (root.getLeft() != null) {
                parenthesizeR(root.getLeft(), nivel + 1);
            }
            if (root.getRight() != null) {
                parenthesizeR(root.getRight(), nivel + 1);
            }
            // Cierra el paréntesis con sangría
            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }
            System.out.println(")");
        } else {
            // Si no tiene hijos, solo imprime un salto de línea
            System.out.println();
        }
    }
}