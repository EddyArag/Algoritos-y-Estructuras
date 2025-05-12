package Actividad1;

class ItemDuplicated extends Exception {
    public ItemDuplicated(String message) {
        super(message);
    }
}

class ItemNotFound extends Exception {
    public ItemNotFound(String message) {
        super(message);
    }
}

class ExceptionIsEmpty extends Exception {
    public ExceptionIsEmpty(String message) {
        super(message);
    }
}

public class LinkedBST<E extends Comparable<E>> implements BinarySearchTree<E> {
    class Node {
        public E data;
        public Node left;
        public Node right;

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    private Node root;

    public LinkedBST() {
        this.root = null;
    }

    @Override
    public void insert(E data) throws ItemDuplicated {
        root = insertRec(root, data);
    }

    private Node insertRec(Node node, E data) throws ItemDuplicated {
        if (node == null) {
            return new Node(data);
        }

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insertRec(node.left, data);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, data);
        } else {
            throw new ItemDuplicated("Item already exists: " + data);
        }
        return node;
    }

    @Override
    public E search(E data) throws ItemNotFound {
        return searchRec(root, data);
    }

    private E searchRec(Node node, E data) throws ItemNotFound {
        if (node == null) {
            throw new ItemNotFound("Item not found: " + data);
        }

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            return searchRec(node.left, data);
        } else if (cmp > 0) {
            return searchRec(node.right, data);
        } else {
            return node.data;
        }
    }

@Override
public void delete(E data) throws ExceptionIsEmpty {
    // Verifica si el árbol está vacío
    if (isEmpty()) {
        throw new ExceptionIsEmpty("El árbol está vacío.");
    }
    // Llama al método recursivo para eliminar el nodo
    root = deleteRec(root, data);
}

private Node deleteRec(Node node, E data) {
    // Si el nodo es nulo, simplemente retorna nulo
    if (node == null) {
        return node;
    }

    // Compara el dato a eliminar con el dato del nodo actual
    int cmp = data.compareTo(node.data);
    if (cmp < 0) {
        // Si el dato es menor, busca en el subárbol izquierdo
        node.left = deleteRec(node.left, data);
    } else if (cmp > 0) {
        // Si el dato es mayor, busca en el subárbol derecho
        node.right = deleteRec(node.right, data);
    } else {
        // Nodo con un solo hijo o sin hijos
        if (node.left == null) {
            return node.right; // Retorna el hijo derecho
        } else if (node.right == null) {
            return node.left; // Retorna el hijo izquierdo
        }

        // Nodo con dos hijos: Obtiene el sucesor en orden (el más pequeño en el subárbol derecho)
        node.data = minValue(node.right);

        // Elimina el sucesor en orden
        node.right = deleteRec(node.right, node.data);
    }
    return node; // Retorna el nodo actualizado
}


    private E minValue(Node node) {
        E minv = node.data;
        while (node.left != null) {
            minv = node.left.data;
            node = node.left;
        }
        return minv;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toStringRec(root, sb);
        return sb.toString();
    }

    private void toStringRec(Node node, StringBuilder sb) {
        if (node != null) {
            toStringRec(node.left, sb);
            sb.append(node.data).append(" ");
            toStringRec(node.right, sb);
        }
    }
}
