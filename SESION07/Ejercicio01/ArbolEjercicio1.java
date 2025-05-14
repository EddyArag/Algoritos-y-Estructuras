package SESION07.Ejercicio01;

public class ArbolEjercicio1<E extends Comparable<E>> {
    private Nodoejer1<E> root;

    public ArbolEjercicio1() {
        this.root = null;
    }

    // a. Eliminar todos los nodos
    public void destroyNodes() throws ExceptionIsEmptyejer1 {
        if (root == null) {
            throw new ExceptionIsEmptyejer1("El árbol ya está vacío");
        }
        destroyNodesRec(root);
        root = null;
    }

    private void destroyNodesRec(Nodoejer1<E> node) {
        if (node == null) return;
        destroyNodesRec(node.getLeft());
        destroyNodesRec(node.getRight());
        node.setLeft(null);
        node.setRight(null);
    }

    // b. Contar todos los nodos (hojas y no hojas)
    public int countAllNodes() {
        return countAllNodesRec(root);
    }

    private int countAllNodesRec(Nodoejer1<E> node) {
        if (node == null) return 0;
        return 1 + countAllNodesRec(node.getLeft()) + countAllNodesRec(node.getRight());
    }

    // c. Contar solo nodos no-hoja
    public int countNodes() {
        return countNodesRec(root);
    }

    private int countNodesRec(Nodoejer1<E> node) {
        if (node == null || (node.getLeft() == null && node.getRight() == null)) {
            return 0;
        }
        return 1 + countNodesRec(node.getLeft()) + countNodesRec(node.getRight());
    }

    // d. Altura del subárbol con raíz 'x' (iterativo)
    public int height(E x) {
        Nodoejer1<E> subtreeRoot = search(root, x);
        if (subtreeRoot == null) return -1;

        QueueLink<Nodoejer1<E>> queue = new QueueLink<>();
        queue.enqueue(subtreeRoot);
        int height = -1;

        while (!queue.isEmpty()) {
            int levelSize = queueSize(queue);
            height++;
            for (int i = 0; i < levelSize; i++) {
                Nodoejer1<E> current = queue.dequeue();
                if (current.getLeft() != null) queue.enqueue(current.getLeft());
                if (current.getRight() != null) queue.enqueue(current.getRight());
            }
        }
        return height;
    }

    // e. Amplitud en un nivel específico
    public int amplitude(int nivel) {
        if (root == null) return 0;

        QueueLink<Nodoejer1<E>> queue = new QueueLink<>();
        queue.enqueue(root);
        int currentLevel = 0;

        while (!queue.isEmpty()) {
            if (currentLevel == nivel) {
                return queueSize(queue);
            }
            int levelSize = queueSize(queue);
            for (int i = 0; i < levelSize; i++) {
                Nodoejer1<E> current = queue.dequeue();
                if (current.getLeft() != null) queue.enqueue(current.getLeft());
                if (current.getRight() != null) queue.enqueue(current.getRight());
            }
            currentLevel++;
        }
        return 0; // Nivel no existe
    }

    // Métodos auxiliares
    private Nodoejer1<E> search(Nodoejer1<E> node, E x) {
        if (node == null || node.getElem().equals(x)) {
            return node;
        }
        return x.compareTo(node.getElem()) < 0
                ? search(node.getLeft(), x)
                : search(node.getRight(), x);
    }

    private int queueSize(QueueLink<Nodoejer1<E>> queue) {
        int size = 0;
        QueueLink<Nodoejer1<E>> temp = new QueueLink<>();
        while (!queue.isEmpty()) {
            temp.enqueue(queue.dequeue());
            size++;
        }
        while (!temp.isEmpty()) {
            queue.enqueue(temp.dequeue());
        }
        return size;
    }

    // Insertar (para pruebas)
    public void insert(E x) throws ExceptionDuplicateejer1 {
        root = insertRec(root, x);
    }

    private Nodoejer1<E> insertRec(Nodoejer1<E> node, E x) throws ExceptionDuplicateejer1 {
        if (node == null) return new Nodoejer1<>(x);
        int cmp = x.compareTo(node.getElem());
        if (cmp < 0) node.setLeft(insertRec(node.getLeft(), x));
        else if (cmp > 0) node.setRight(insertRec(node.getRight(), x));
        else throw new ExceptionDuplicateejer1("Elemento duplicado: " + x);
        return node;
    }
}