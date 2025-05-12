package SESION07.Ejercicio01;

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
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    public int height(E x) {
        Nodoejer1<E> subtreeRoot = search(this.root, x);
        if (subtreeRoot == null) return -1;
        return height(subtreeRoot);
    }

    private int height(Nodoejer1<E> node) {
        if (node == null) return -1; // Altura de un nodo nulo es -1
        return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
    }

    public int amplitude() {
        if (isEmpty()) return 0;
        int maxWidth = 0;
        int height = height(root.getElem());

        for (int level = 0; level <= height; level++) {
            int width = getWidth(root, level);
            if (width > maxWidth) maxWidth = width;
        }
        return maxWidth;
    }

    private int getWidth(Nodoejer1<E> node, int level) {
        if (node == null) return 0;
        if (level == 0) return 1;
        return getWidth(node.getLeft(), level - 1) + getWidth(node.getRight(), level - 1);
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
