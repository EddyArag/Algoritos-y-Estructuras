package SESION07.Ejercicio02;

import SESION07.Ejercicio01.QueueLink;
import SESION07.Ejercicio01.ExceptionIsEmpty;

public class ArbolEjercicio2<E extends Comparable<E>> {
    private Nodoejer2<E> root;

    // [Métodos anteriores iguales...]

    public void drawBST() {
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }

        int height = getHeight(root);
        int width = (int) Math.pow(2, height) * 4;

        QueueLink<PrintableNode<E>> queue = new QueueLink<>();
        queue.enqueue(new PrintableNode<>(root, width/2, width, 0));

        int currentLevel = 0;
        StringBuilder nodeLine = new StringBuilder();
        StringBuilder connectionLine = new StringBuilder();

        while (!queue.isEmpty()) {
            try {
                PrintableNode<E> printable = queue.dequeue();
                Nodoejer2<E> node = printable.node;
                int pos = printable.pos;
                int space = printable.space;
                int level = printable.level;

                if (level != currentLevel) {
                    System.out.println(nodeLine.toString());
                    System.out.println(connectionLine.toString());
                    nodeLine = new StringBuilder();
                    connectionLine = new StringBuilder();
                    currentLevel = level;
                }

                // Centrar el nodo
                for (int i = nodeLine.length(); i < pos; i++) {
                    nodeLine.append(" ");
                }
                nodeLine.append(node.getElem());

                // Conexiones
                if (node.getLeft() != null) {
                    int leftPos = pos - space/4;
                    for (int i = connectionLine.length(); i < leftPos; i++) {
                        connectionLine.append(" ");
                    }
                    connectionLine.append("/");
                    queue.enqueue(new PrintableNode<>(node.getLeft(), leftPos, space/2, level+1));
                }

                if (node.getRight() != null) {
                    int rightPos = pos + space/4;
                    for (int i = connectionLine.length(); i < rightPos; i++) {
                        connectionLine.append(" ");
                    }
                    connectionLine.append("\\");
                    queue.enqueue(new PrintableNode<>(node.getRight(), rightPos, space/2, level+1));
                }
            } catch (ExceptionIsEmpty e) {
                e.printStackTrace();
            }
        }
        System.out.println(nodeLine.toString());
    }

    private int getHeight(Nodoejer2<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    private static class PrintableNode<E> {
        Nodoejer2<E> node;
        int pos;
        int space;
        int level;

        PrintableNode(Nodoejer2<E> node, int pos, int space, int level) {
            this.node = node;
            this.pos = pos;
            this.space = space;
            this.level = level;
        }
    }
}