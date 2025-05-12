package SESION07.Ejercicio02;

import java.util.Queue;
import java.util.LinkedList;

public class ArbolEjercicio2<E extends Comparable<E>> {
    private Nodoejer2<E> root;

    public ArbolEjercicio2() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void destroy() {
        root = null;
    }

    public void destroyNodes() throws ExceptionIsEmptyejer2 {
        if (isEmpty()) {
            throw new ExceptionIsEmptyejer2("El árbol ya está vacío");
        }
        destroyNodes(this.root);
        this.root = null;
    }

    private void destroyNodes(Nodoejer2<E> node) {
        if (node == null) return;
        destroyNodes(node.getLeft());
        destroyNodes(node.getRight());
        node.setLeft(null);
        node.setRight(null);
    }

    public int countAllNodes() {
        return countAllNodes(this.root);
    }

    private int countAllNodes(Nodoejer2<E> node) {
        if (node == null) return 0;
        return 1 + countAllNodes(node.getLeft()) + countAllNodes(node.getRight());
    }

    // Método para insertar un elemento en el BST
    public void insert(E x) throws ExceptionDuplicateejer2 {
        this.root = insert(this.root, x);
    }

    private Nodoejer2<E> insert(Nodoejer2<E> actual, E x) throws ExceptionDuplicateejer2 {
        if (actual == null) {
            return new Nodoejer2<E>(x);
        }
        int comp = actual.getElem().compareTo(x);
        if (comp > 0) {
            actual.setLeft(insert(actual.getLeft(), x));
        } else if (comp < 0) {
            actual.setRight(insert(actual.getRight(), x));
        } else {
            throw new ExceptionDuplicateejer2("No se aceptan valores duplicados.");
        }
        return actual;
    }

    private Nodoejer2<E> search(Nodoejer2<E> node, E x) {
        if (node == null || node.getElem().equals(x)) {
            return node;
        }
        int comp = node.getElem().compareTo(x);
        if (comp > 0) {
            return search(node.getLeft(), x);
        } else {
            return search(node.getRight(), x);
        }
    }

    // Método iterativo para calcular área: hojas * altura
    public int areaBST() {
        if (isEmpty()) return 0;

        Queue<Nodoejer2<E>> queue = new LinkedList<>();
        queue.offer(root);
        int leafCount = 0;
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;

            for (int i = 0; i < levelSize; i++) {
                Nodoejer2<E> current = queue.poll();
                if (current.getLeft() == null && current.getRight() == null) {
                    leafCount++;
                }
                if (current.getLeft() != null) queue.offer(current.getLeft());
                if (current.getRight() != null) queue.offer(current.getRight());
            }
        }
        return leafCount * height;
    }

    // Método para dibujar el árbol por niveles mostrando nodos y relaciones
    public void drawBST() {
        if (isEmpty()) {
            System.out.println("El árbol está vacío.");
            return;
        }

        Queue<Nodoejer2<E>> queue = new LinkedList<>();
        queue.offer(root);
        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.print("Nivel " + level + ": ");

            for (int i = 0; i < levelSize; i++) {
                Nodoejer2<E> current = queue.poll();
                System.out.print(current.getElem() + " ");
                if (current.getLeft() != null) queue.offer(current.getLeft());
                if (current.getRight() != null) queue.offer(current.getRight());
            }
            System.out.println();
            level++;
        }
    }
}
