package SESION08;

public class AVLTreeWithRemove<E extends Comparable<E>> extends ArbolAVL<E> {

    @Override
    public E remove(E x) throws ItemDuplicated {
        this.height = false;
        NodeAVL removedNode = (NodeAVL) search(this.root, x);
        if (removedNode == null) {
            throw new ItemDuplicated(x + " no se encuentra en el árbol");
        }
        this.root = removeAVL(x, (NodeAVL) this.root);
        return removedNode.getElem();
    }

    protected NodeAVL removeAVL(E x, NodeAVL node) throws ItemDuplicated {
        if (node == null) {
            this.height = false;
            return null;
        }

        int resC = node.getElem().compareTo(x);
        if (resC < 0) {
            node.setRight(removeAVL(x, (NodeAVL) node.getRight()));
            if (this.height) {
                node = balanceAfterRemoval(node, false);
            }
        } else if (resC > 0) {
            node.setLeft(removeAVL(x, (NodeAVL) node.getLeft()));
            if (this.height) {
                node = balanceAfterRemoval(node, true);
            }
        } else {
            // Nodo encontrado, proceder a eliminarlo
            if (node.getLeft() == null || node.getRight() == null) {
                // Caso 1: Nodo tiene 0 o 1 hijo
                this.height = true;
                return (NodeAVL) (node.getLeft() != null ? node.getLeft() : node.getRight());
            } else {
                // Caso 2: Nodo tiene 2 hijos
                NodeAVL minRight = (NodeAVL) minSearch(node.getRight());
                node.setElem(minRight.getElem());
                node.setRight(removeAVL(minRight.getElem(), (NodeAVL) node.getRight()));
                if (this.height) {
                    node = balanceAfterRemoval(node, false);
                }
            }
        }
        return node;
    }

    private NodeAVL balanceAfterRemoval(NodeAVL node, boolean wasLeft) {
        if (wasLeft) {
            // Eliminación ocurrió en el subárbol izquierdo
            switch (node.getBF()) {
                case -1: // Se balanceó
                    node.setBF(0);
                    this.height = true;
                    break;
                case 0: // Se hizo más alto
                    node.setBF(1);
                    this.height = false;
                    break;
                case 1: // Desbalanceado
                    NodeAVL right = (NodeAVL) node.getRight();
                    int rightBF = right.getBF();

                    if (rightBF >= 0) {
                        node = balanceToLeft(node);
                        this.height = node.getBF() != 0;
                    } else {
                        node = balanceToRightThenLeft(node);
                        this.height = true;
                    }
                    break;
            }
        } else {
            // Eliminación ocurrió en el subárbol derecho
            switch (node.getBF()) {
                case 1: // Se balanceó
                    node.setBF(0);
                    this.height = true;
                    break;
                case 0: // Se hizo más alto
                    node.setBF(-1);
                    this.height = false;
                    break;
                case -1: // Desbalanceado
                    NodeAVL left = (NodeAVL) node.getLeft();
                    int leftBF = left.getBF();

                    if (leftBF <= 0) {
                        node = balanceToRight(node);
                        this.height = node.getBF() != 0;
                    } else {
                        node = balanceToLeftThenRight(node);
                        this.height = true;
                    }
                    break;
            }
        }
        return node;
    }

    private NodeAVL balanceToRightThenLeft(NodeAVL node) {
        node.setRight(balanceToRight((NodeAVL) node.getRight()));
        return balanceToLeft(node);
    }

    private NodeAVL balanceToLeftThenRight(NodeAVL node) {
        node.setLeft(balanceToLeft((NodeAVL) node.getLeft()));
        return balanceToRight(node);
    }
}