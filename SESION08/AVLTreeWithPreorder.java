package SESION08;

import SESION07.Ejercicio02.ExceptionIsEmptyejer2;

public class AVLTreeWithPreorder<E extends Comparable<E>> extends AVLTreeWithLevelOrder<E> {
    // Método público para iniciar el recorrido en preorden desde la raíz
    public void preOrderTraversal() throws ExceptionIsEmptyejer2 {
        if (this.root == null) {
            throw new ExceptionIsEmptyejer2("Árbol vacío");
        }
        System.out.print("Recorrido en preorden: ");
        preOrderRecursive((NodeAVL) this.root);
        System.out.println(); // Salto de línea al final del recorrido
    }

    // Método recursivo privado que realiza el recorrido en preorden
    private void preOrderRecursive(NodeAVL currentNode) {
        if (currentNode == null)
            return;

        System.out.print(currentNode.getElem() + " "); // Visitar el nodo raíz
        preOrderRecursive((NodeAVL) currentNode.getLeft()); // Recorrer subárbol izquierdo
        preOrderRecursive((NodeAVL) currentNode.getRight()); // Recorrer subárbol derecho
    }
}