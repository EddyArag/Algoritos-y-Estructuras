package SESION07.Ejercicio02;

public class ArbolEjercicio2<E extends Comparable<E>> {
    public NodoEjercicio2<E> root;

    public ArbolEjercicio2() {
        this.root = null;
    }

    public void insert(E x) throws ExceptionDuplicateejer2 {
        root = insertRec(root, x);
    }

    private NodoEjercicio2<E> insertRec(NodoEjercicio2<E> node, E x) throws ExceptionDuplicateejer2 {
        if (node == null)
            return new NodoEjercicio2<>(x);
        int cmp = x.compareTo(node.getElem());
        if (cmp < 0)
            node.setLeft(insertRec(node.getLeft(), x));
        else if (cmp > 0)
            node.setRight(insertRec(node.getRight(), x));
        else
            throw new ExceptionDuplicateejer2("Elemento duplicado: " + x);
        return node;
    }

    public void destroyNodes() throws ExceptionIsEmptyejer2 {
        if (root == null)
            throw new ExceptionIsEmptyejer2("Árbol vacío");
        destroyRec(root);
        root = null;
    }

    private void destroyRec(NodoEjercicio2<E> node) {
        if (node == null)
            return;
        destroyRec(node.getLeft());
        destroyRec(node.getRight());
        node.setLeft(null);
        node.setRight(null);
    }

    public int countAllNodes() {
        return countAllRec(root);
    }

    private int countAllRec(NodoEjercicio2<E> node) {
        if (node == null)
            return 0;
        return 1 + countAllRec(node.getLeft()) + countAllRec(node.getRight());
    }

    public int height(E x) throws ExceptionIsEmptyejer2 {
        NodoEjercicio2<E> node = search(root, x);
        if (node == null)
            return -1;

        QueueLink<NodoEjercicio2<E>> queue = new QueueLink<>();
        queue.enqueue(node);
        int height = -1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            for (int i = 0; i < size; i++) {
                NodoEjercicio2<E> current = queue.dequeue();
                if (current.getLeft() != null)
                    queue.enqueue(current.getLeft());
                if (current.getRight() != null)
                    queue.enqueue(current.getRight());
            }
        }
        return height;
    }

    public int amplitude(int nivel) throws ExceptionIsEmptyejer2 {
        if (root == null)
            return 0;

        QueueLink<NodoEjercicio2<E>> queue = new QueueLink<>();
        queue.enqueue(root);
        int currentLevel = 0;

        while (!queue.isEmpty()) {
            if (currentLevel == nivel)
                return queue.size();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NodoEjercicio2<E> current = queue.dequeue();
                if (current.getLeft() != null)
                    queue.enqueue(current.getLeft());
                if (current.getRight() != null)
                    queue.enqueue(current.getRight());
            }
            currentLevel++;
        }

        return 0; // nivel no existe
    }

    public int areaBST() {
        return countLeafRec(root);
    }

    private int countLeafRec(NodoEjercicio2<E> node) {
        if (node == null)
            return 0;
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
        return countLeafRec(node.getLeft()) + countLeafRec(node.getRight());
    }

    public void drawBST() throws ExceptionIsEmptyejer2 {
        if (root == null)
            throw new ExceptionIsEmptyejer2("Árbol vacío");

        int height = getTreeHeight(root);
        int maxLevel = height;
        QueueLink<NodoEjercicio2<E>> queue = new QueueLink<>();
        queue.enqueue(root);

        int level = 1;
        while (!queue.isEmpty() && level <= height) {
            int currentElements = (int) Math.pow(2, level - 1);
            int spacing = (int) Math.pow(2, maxLevel - level + 1) - 1;
            int sideSpacing = (int) Math.pow(2, maxLevel - level) - 1;

            // Asegurar que los espacios no sean negativos
            spacing = Math.max(0, spacing);
            sideSpacing = Math.max(0, sideSpacing);

            // Imprimir nodos
            StringBuilder nodeLine = new StringBuilder();
            StringBuilder branchLine = new StringBuilder();
            NodoEjercicio2<E>[] currentNodes = new NodoEjercicio2[currentElements];

            for (int i = 0; i < currentElements; i++) {
                NodoEjercicio2<E> node = queue.dequeue();
                currentNodes[i] = node;

                // Espacios antes del nodo
                nodeLine.append(" ".repeat(sideSpacing));

                if (node != null) {
                    nodeLine.append(String.format("%2s", node.getElem()));
                    queue.enqueue(node.getLeft());
                    queue.enqueue(node.getRight());
                } else {
                    nodeLine.append("  ");
                    queue.enqueue(null);
                    queue.enqueue(null);
                }

                // Espacios después del nodo
                nodeLine.append(" ".repeat(spacing));
            }
            System.out.println(nodeLine.toString());

            // Imprimir ramas (excepto último nivel)
            if (level < height) {
                for (int i = 0; i < currentElements; i++) {
                    NodoEjercicio2<E> node = currentNodes[i];

                    branchLine.append(" ".repeat(sideSpacing > 0 ? sideSpacing - 1 : 0));

                    if (node != null) {
                        branchLine.append(node.getLeft() != null ? "/" : " ");
                        branchLine.append(" ".repeat(spacing));
                        branchLine.append(node.getRight() != null ? "\\" : " ");
                    } else {
                        branchLine.append("   ").append(" ".repeat(spacing)).append(" ");
                    }

                    branchLine.append(" ".repeat(sideSpacing > 0 ? sideSpacing - 1 : 0));
                }
                System.out.println(branchLine.toString());
            }

            level++;
        }
    }

    private int getTreeHeight(NodoEjercicio2<E> node) {
        if (node == null)
            return 0;
        int leftHeight = getTreeHeight(node.getLeft());
        int rightHeight = getTreeHeight(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    private void printTree(NodoEjercicio2<E> node, int level) {
        if (node == null)
            return;
        printTree(node.getRight(), level + 1);
        System.out.println("  ".repeat(level) + node.getElem());
        printTree(node.getLeft(), level + 1);
    }

    public NodoEjercicio2<E> search(NodoEjercicio2<E> node, E x) {
        if (node == null || node.getElem().equals(x))
            return node;
        return x.compareTo(node.getElem()) < 0
                ? search(node.getLeft(), x)
                : search(node.getRight(), x);
    }
}
