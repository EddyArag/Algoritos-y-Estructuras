package SESION08;

import SESION07.Ejercicio02.ExceptionIsEmptyejer2;

public class TestArbolAVL {
    public static void main(String[] args) throws ExceptionIsEmptyejer2 {
        ArbolAVL<Integer> avl = new ArbolAVL<>();

        // Insertar elementos en el árbol AVL
        try {
            for (int i = 1; i <= 10; i++) {
                avl.insertAVL(i);
            }
        } catch (ItemDuplicated e) {
            System.err.println(e.getMessage());
        }

        // Mostrar la altura del árbol
        System.out.println("Altura del árbol AVL: " + avl.height());

        // Probar la búsqueda de elementos
        System.out.println("Buscar 5 en el árbol AVL: " + avl.search(5));
        System.out.println("Buscar 15 en el árbol AVL: " + avl.search(15));

        // Dibujar el árbol
        System.out.println("Dibujo del árbol AVL:");
        drawAVL(avl);
    }

    // Método para dibujar el árbol AVL
    public static <E extends Comparable<E>> void drawAVL(ArbolAVL<E> avl) throws ExceptionIsEmptyejer2 {
        if (avl.root == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        SESION07.Ejercicio02.QueueLink<ArbolAVL<E>.NodeAVL> queue = new SESION07.Ejercicio02.QueueLink<>();
        queue.enqueue(avl.new NodeAVL(avl.root.getElem()));
        enqueueChildren(queue, (ArbolAVL<E>.NodeAVL) avl.root);

        int height = avl.height();
        int maxWidth = (int) Math.pow(2, height + 1) - 1; // Ancho máximo para el dibujo

        for (int level = 0; level <= height; level++) {
            int nodesInLevel = (int) Math.pow(2, level);
            int spacesBetween = maxWidth / nodesInLevel;

            // Espacio inicial para centrar el primer nodo en la línea
            StringBuilder line = new StringBuilder(" ".repeat(spacesBetween / 2));

            for (int i = 0; i < nodesInLevel; i++) {
                if (queue.isEmpty()) {
                    line.append(" ".repeat(spacesBetween));
                    continue;
                }

                ArbolAVL<E>.NodeAVL currentNode = queue.dequeue();
                if (currentNode != null) {
                    line.append(currentNode.getElem()).append(" ".repeat(spacesBetween));
                    enqueueChildren(queue, currentNode);
                } else {
                    line.append(" ".repeat(spacesBetween + 1));
                    queue.enqueue(null);
                    queue.enqueue(null);
                }
            }
            System.out.println(line.toString());
        }
    }

    private static <E extends Comparable<E>> void enqueueChildren(
            SESION07.Ejercicio02.QueueLink<ArbolAVL<E>.NodeAVL> queue,
            ArbolAVL<E>.NodeAVL node) {
        if (node.getLeft() != null) {
            queue.enqueue((ArbolAVL<E>.NodeAVL) node.getLeft());
        } else {
            queue.enqueue(null);
        }
        if (node.getRight() != null) {
            queue.enqueue((ArbolAVL<E>.NodeAVL) node.getRight());
        } else {
            queue.enqueue(null);
        }
    }
}
