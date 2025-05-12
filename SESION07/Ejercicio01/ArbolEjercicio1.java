package SESION07.Ejercicio01;

import java.util.LinkedList;
import java.util.Queue;

class ArbolEjercicio1<E extends Comparable<E>> {
    private Nodoejer1<E> root;

    public ArbolEjercicio1() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void destroy() {
        root = null;
    }

    public void destroyNodes() throws ExceptionIsEmptyejer1 {
        if (isEmpty()) {
            throw new ExceptionIsEmptyejer1("El árbol ya está vacío");
        }
        destroyNodes(this.root);
        this.root = null;
    }

    private void destroyNodes(Nodoejer1<E> node) {
        if (node == null) return;
        destroyNodes(node.getLeft());
        destroyNodes(node.getRight());
        node.setLeft(null);
        node.setRight(null);
    }

    public int countAllNodes() {
        return countAllNodes(this.root);
    }

    private int countAllNodes(Nodoejer1<E> node) {
        if (node == null) return 0;
        return 1 + countAllNodes(node.getLeft()) + countAllNodes(node.getRight());
    }

    public int countNodes() {
        return countNodes(this.root);
    }

    private int countNodes(Nodoejer1<E> node) {
        if (node == null) return 0;
        int count = 0;
        if (node.getLeft() != null || node.getRight() != null) {
            count = 1; // Nodo no-hoja
        }
        return count + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    // Metodo height(x) iterativo (altura del subárbol cuya raíz tiene dato x)
    public int height(E x) {
        Nodoejer1<E> subtreeRoot = search(this.root, x);
        if (subtreeRoot == null) return -1;

        Queue<Nodoejer1<E>> queue = new LinkedList<>();
        queue.add(subtreeRoot);
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            for (int i = 0; i < levelSize; i++) {
                Nodoejer1<E> current = queue.poll();
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }
        return height;
    }

    // Metodo amplitude(Nivel) que retorna la amplitud en ese nivel
    public int amplitude(int nivel) {
        if (isEmpty()) return 0;

        Queue<Nodoejer1<E>> queue = new LinkedList<>();
        queue.add(root);
        int currentLevel = 0;

        while (!queue.isEmpty()) {
            if (currentLevel == nivel) {
                return queue.size(); // cantidad nodos en nivel dado
            }
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Nodoejer1<E> current = queue.poll();
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
            currentLevel++;
        }
        return 0; // nivel no existe
    }

    public void insert(E x) throws ExceptionDuplicateejer1 {
        this.root = insert(this.root, x);
    }

    private Nodoejer1<E> insert(Nodoejer1<E> actual, E x) throws ExceptionDuplicateejer1 {
        if (actual == null) {
            return new Nodoejer1<E>(x);
        }
        int comp = actual.getElem().compareTo(x);
        if (comp > 0) {
            actual.setLeft(insert(actual.getLeft(), x));
        } else if (comp < 0) {
            actual.setRight(insert(actual.getRight(), x));
        } else {
            throw new ExceptionDuplicateejer1("No se aceptan valores duplicados.");
        }
        return actual;
    }

    private Nodoejer1<E> search(Nodoejer1<E> node, E x) {
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
}

/**
Metodo height(x) iterativo (altura del subárbol cuya raíz tiene dato x)
    public int height(E x) {
        Nodoejer1<E> subtreeRoot = search(this.root, x);
        if (subtreeRoot == null) return -1;

        Queue<Nodoejer1<E>> queue = new LinkedList<>();
        queue.add(subtreeRoot);
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;
            for (int i = 0; i < levelSize; i++) {
                Nodoejer1<E> current = queue.poll();
                if (current.getLeft() != null) queue.add(current.getLeft());
                if (current.getRight() != null) queue.add(current.getRight());
            }
        }
        return height;
    }
    **/