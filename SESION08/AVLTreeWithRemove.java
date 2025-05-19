package SESION08;

import SESION07.Actividad01.Node;
import SESION08.ItemDuplicated;
import SESION07.Ejercicio02.ArbolEjercicio2;

public class AVLTreeWithRemove<E extends Comparable<E>> extends ArbolAVL<E> {

    @Override
    public E remove(E x) throws ItemDuplicated {
        this.root = remove((NodeAVL) this.root, x);
        return x;
    }

    protected Node remove(NodeAVL node, E x) throws ItemDuplicated {
        if (node == null) {
            throw new ItemDuplicated(x + " no se encuentra en el árbol");
        }

        int cmp = x.compareTo((E) node.getElem());

        if (cmp < 0) {
            node.setLeft(remove((NodeAVL) node.getLeft(), x));
        } else if (cmp > 0) {
            node.setRight(remove((NodeAVL) node.getRight(), x));
        } else {
            // Nodo encontrado: proceder a eliminar
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            } else {
                // Nodo con dos hijos
                NodeAVL temp = (NodeAVL) minSearch(node.getRight());
                node.setElem(temp.getElem());
                node.setRight(minOfMaxRemove((NodeAVL) node.getRight()));
            }
        }

        // Actualizar factor de balance y rebalancear si es necesario
        return balanceAfterDeletion(node);
    }

    private NodeAVL balanceAfterDeletion(NodeAVL node) {
        // Implementar lógica de rebalanceo similar a la inserción
        // (Reutilizar métodos balanceToLeft y balanceToRight)
        updateBF(node);

        if (node.getBF() == 2) {
            return balanceToLeft(node);
        } else if (node.getBF() == -2) {
            return balanceToRight(node);
        }

        return node;
    }

    private void updateBF(NodeAVL node) {
        // Actualizar factor de balance basado en las alturas de los hijos
        int leftHeight = height(node.getLeft());
        int rightHeight = height(node.getRight());
        node.setBF(rightHeight - leftHeight);
    }

    private NodeAVL minOfMaxRemove(NodeAVL node) {
        if (node.getLeft() == null) {
            return (NodeAVL) node.getRight();
        }
        node.setLeft(minOfMaxRemove((NodeAVL) node.getLeft()));
        return balanceAfterDeletion(node);
    }
}