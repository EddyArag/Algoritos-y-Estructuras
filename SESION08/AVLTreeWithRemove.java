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
                // Después de la rotación, si el BF es 0, la altura se reduce
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

    protected NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL right = (NodeAVL) node.getRight(); // Obtenemos el hijo derecho
        switch (right.getBF()) {
            case 1:
                node.setBF(0); // Ajustamos el BF del nodo actual
                right.setBF(0); // Ajustamos el BF del hijo derecho
                node = rotateSL(node); // Realizamos rotación simple a la izquierda
                break;
            case 0:
                node.setBF(1); // Ajustamos el BF del nodo actual
                right.setBF(-1); // Ajustamos el BF del hijo derecho
                node = rotateSL(node); // Realizamos rotación simple a la izquierda
                break;
            case -1:
                NodeAVL rightLeft = (NodeAVL) right.getLeft(); // Obtenemos el hijo izquierdo del hijo derecho
                switch (rightLeft.getBF()) {
                    case 1:
                        node.setBF(-1); // Ajustamos el BF del nodo actual
                        right.setBF(0); // Ajustamos el BF del hijo derecho
                        break;
                    case 0:
                        node.setBF(0); // Ambos nodos quedan balanceados
                        right.setBF(0);
                        break;
                    case -1:
                        node.setBF(0); // Ajustamos el BF del nodo actual
                        right.setBF(1); // Ajustamos el BF del hijo derecho
                        break;
                }
                rightLeft.setBF(0); // Ajustamos el BF del nieto
                node.setRight(rotateSR(right)); // Realizamos rotación simple a la derecha en el hijo derecho
                node = rotateSL(node); // Realizamos rotación simple a la izquierda
                break;
        }
        return node; // Retornamos el nodo actualizado
    }

    public NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL left = (NodeAVL) node.getLeft(); // Obtenemos el hijo izquierdo
        switch (left.getBF()) {
            case -1:
                node.setBF(0); // Ajustamos el BF del nodo actual
                left.setBF(0); // Ajustamos el BF del hijo izquierdo
                node = rotateSR(node); // Realizamos rotación simple a la derecha
                break;
            case 0:
                node.setBF(-1); // Ajustamos el BF del nodo actual
                left.setBF(1); // Ajustamos el BF del hijo izquierdo
                node = rotateSR(node); // Realizamos rotación simple a la derecha
                break;
            case 1:
                NodeAVL leftRight = (NodeAVL) left.getRight(); // Obtenemos el hijo derecho del hijo izquierdo
                switch (leftRight.getBF()) {
                    case -1:
                        node.setBF(1); // Ajustamos el BF del nodo actual
                        left.setBF(0); // Ajustamos el BF del hijo izquierdo
                        break;
                    case 0:
                        node.setBF(0); // Ambos nodos quedan balanceados
                        left.setBF(0);
                        break;
                    case 1:
                        node.setBF(0); // Ajustamos el BF del nodo actual
                        left.setBF(-1); // Ajustamos el BF del hijo izquierdo
                        break;
                }
                leftRight.setBF(0); // Ajustamos el BF del nieto
                node.setLeft(rotateSL(left)); // Realizamos rotación simple a la izquierda en el hijo izquierdo
                node = rotateSR(node); // Realizamos rotación simple a la derecha
                break;
        }
        return node; // Retornamos el nodo actualizado
    }

    private NodeAVL balanceToRightThenLeft(NodeAVL node) {
        node.setRight(balanceToRight((NodeAVL) node.getRight())); // Balanceamos el subárbol derecho
        return balanceToLeft(node); // Luego balanceamos el nodo actual
    }

    private NodeAVL balanceToLeftThenRight(NodeAVL node) {
        node.setLeft(balanceToLeft((NodeAVL) node.getLeft())); // Balanceamos el subárbol izquierdo
        return balanceToRight(node); // Luego balanceamos el nodo actual
    }

    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.getRight(); // Obtenemos el hijo derecho
        node.setRight(p.getLeft()); // Ajustamos el subárbol derecho
        p.setLeft(node); // Realizamos la rotación
        return p; // Retornamos el nuevo nodo raíz
    }

    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.getLeft(); // Obtenemos el hijo izquierdo
        node.setLeft(p.getRight()); // Ajustamos el subárbol izquierdo
        p.setRight(node); // Realizamos la rotación
        return p; // Retornamos el nuevo nodo raíz
    }

    public Node minSearch(Node node) {
        if (node.getLeft() == null)
            return node; // Retornamos el nodo si no tiene hijo izquierdo
        return minSearch(node.getLeft()); // Buscamos el mínimo en el subárbol izquierdo
    }
}
