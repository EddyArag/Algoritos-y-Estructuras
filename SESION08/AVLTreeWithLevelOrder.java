package SESION08;

import SESION07.Ejercicio02.ExceptionIsEmptyejer2;
import SESION07.Ejercicio02.QueueLink;

public class AVLTreeWithLevelOrder<E extends Comparable<E>> extends AVLTreeWithRemove<E> {

    // Método que realiza el recorrido por amplitud (nivel por nivel)
    public void levelOrderTraversal() throws ExceptionIsEmptyejer2 {
        if (this.root == null) {
            throw new ExceptionIsEmptyejer2("Árbol vacío"); // Verificamos si el árbol está vacío
        }

        QueueLink<NodeAVL> queue = new QueueLink<>(); // Creamos una cola para el recorrido
        queue.enqueue((NodeAVL) this.root); // Encolamos la raíz

        while (!queue.isEmpty()) {
            NodeAVL currentNode = queue.dequeue(); // Desencolamos el nodo actual
            System.out.print(currentNode.getElem() + " "); // Procesamos el nodo (en este caso, lo imprimimos)

            // Encolamos los hijos izquierdo y derecho
            if (currentNode.getLeft() != null) {
                queue.enqueue((NodeAVL) currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.enqueue((NodeAVL) currentNode.getRight());
            }
        }
        System.out.println(); // Nueva línea después de completar el recorrido
    }
}
