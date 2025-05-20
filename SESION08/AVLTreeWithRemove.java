package SESION08;

import SESION07.Actividad01.Node;

public class AVLTreeWithRemove<E extends Comparable<E>> extends ArbolAVL<E> {

    @Override
    public E remove(E x) throws ItemDuplicated {
        this.height = false; // Inicializamos la altura como falsa antes de la eliminación
        NodeAVL removedNode = searchNode((NodeAVL) this.root, x); // Buscamos el nodo a eliminar
        if (removedNode == null) {
            throw new ItemDuplicated(x + " no se encuentra en el árbol"); // Si no se encuentra, lanzamos excepción
        }
        this.root = removeAVL(x, (NodeAVL) this.root); // Llamamos al método de eliminación
        return removedNode.getElem(); // Retornamos el elemento eliminado
    }

    private NodeAVL searchNode(NodeAVL node, E x) {
        if (node == null)
            return null; // Si el nodo es nulo, retornamos null
        int cmp = x.compareTo(node.getElem()); // Comparamos el elemento a eliminar con el nodo actual
        if (cmp < 0)
            return searchNode((NodeAVL) node.getLeft(), x); // Buscamos en el subárbol izquierdo
        else if (cmp > 0)
            return searchNode((NodeAVL) node.getRight(), x); // Buscamos en el subárbol derecho
        else
            return node; // Si encontramos el nodo, lo retornamos
    }

    protected NodeAVL removeAVL(E x, NodeAVL node) throws ItemDuplicated {
        if (node == null) {
            this.height = false; // Si el nodo es nulo, la altura no cambia
            return null;
        }

        int resC = node.getElem().compareTo(x); // Comparamos el nodo actual con el elemento a eliminar

        if (resC < 0) {
            node.setRight(removeAVL(x, (NodeAVL) node.getRight())); // Eliminamos en el subárbol derecho
            if (this.height) {
                node = updateBalanceAfterRemoval(node, false); // Actualizamos el balance si la altura cambió
            }
        } else if (resC > 0) {
            node.setLeft(removeAVL(x, (NodeAVL) node.getLeft())); // Eliminamos en el subárbol izquierdo
            if (this.height) {
                node = updateBalanceAfterRemoval(node, true); // Actualizamos el balance si la altura cambió
            }
        } else {
            // Nodo a eliminar encontrado
            if (node.getLeft() == null || node.getRight() == null) {
                this.height = true; // Si el nodo tiene uno o ningún hijo, la altura cambia
                return (NodeAVL) (node.getLeft() != null ? node.getLeft() : node.getRight()); // Retornamos el hijo
                                                                                              // existente
            } else {
                // Nodo con dos hijos: reemplazamos con el mínimo del subárbol derecho
                NodeAVL minRight = (NodeAVL) minSearch(node.getRight());
                node.setElem(minRight.getElem()); // Reemplazamos el valor del nodo a eliminar
                node.setRight(removeAVL(minRight.getElem(), (NodeAVL) node.getRight())); // Eliminamos el mínimo
                                                                                         // encontrado
                if (this.height) {
                    node = updateBalanceAfterRemoval(node, false); // Actualizamos el balance si la altura cambió
                }
            }
        }
        return node; // Retornamos el nodo actualizado
    }

    private NodeAVL updateBalanceAfterRemoval(NodeAVL node, boolean deletedLeft) {
        // Actualizamos el factor de balance dependiendo de qué subárbol se redujo
        if (deletedLeft)
            node.setBF(node.getBF() + 1); // Si se eliminó en el subárbol izquierdo, incrementamos el BF
        else
            node.setBF(node.getBF() - 1); // Si se eliminó en el subárbol derecho, decrementamos el BF

        int bf = node.getBF(); // Obtenemos el nuevo factor de balance

        // Verificamos si el árbol necesita rebalanceo
        if (bf == 2) {
            NodeAVL right = (NodeAVL) node.getRight(); // Obtenemos el hijo derecho
            if (right.getBF() >= 0) {
                node = balanceToLeft(node); // Rotación simple a la izquierda
                this.height = (node.getBF() == 0);
            } else {
                node = balanceToRightThenLeft(node); // Rotación doble derecha-izquierda
                this.height = (node.getBF() == 0);
            }
        } else if (bf == -2) {
            NodeAVL left = (NodeAVL) node.getLeft(); // Obtenemos el hijo izquierdo
            if (left.getBF() <= 0) {
                node = balanceToRight(node); // Rotación simple a la derecha
                this.height = (node.getBF() == 0);
            } else {
                node = balanceToLeftThenRight(node); // Rotación doble izquierda-derecha
                this.height = (node.getBF() == 0);
            }
        } else if (bf == 0) {
            // La altura se reduce si el nodo está perfectamente balanceado después de la
            // eliminación
            this.height = true;
        } else {
            // La altura no cambió (bf es ±1)
            this.height = false;
        }

        return node; // Retornamos el nodo actualizado
    }

    public Node minSearch(Node node) {
        if (node.getLeft() == null)
            return node; // Retornamos el nodo si no tiene hijo izquierdo
        return minSearch(node.getLeft()); // Buscamos el mínimo en el subárbol izquierdo
    }
}
