package SESION08;

import SESION07.Actividad01.*;
import SESION07.Ejercicio02.*;

public class ArbolAVL<E extends Comparable<E>> extends Arbol<E> {
    class NodeAVL extends Node<E> {
        protected int bf;

        public NodeAVL(E elem) {
            super(elem);
            this.bf = 0;
        }

        public void setBF(int bf) {
            this.bf = bf;
        }

        public int getBF() {
            return this.bf;
        }

        public String toString() {
            return "Dato: " + getElem().toString() + "BD: " + bf;
        }
    }

    protected boolean height;

    public ArbolAVL() {
        super();
        this.height = false;
    }

    public void insertAVL(E x) throws ItemDuplicated {
        this.height = false;
        this.root = insertAVL(x, (NodeAVL) this.root);
    }

    protected Node insertAVL(E x, NodeAVL node) throws ItemDuplicated {
        NodeAVL fat = node;
        if (node == null) {
            this.height = true;
            fat = new NodeAVL(x);
        } else {
            int resC = node.getElem().compareTo(x);
            if (resC == 0) {
                throw new ItemDuplicated(x + " ya se encuentra en el AVL");
            }
            if (resC < 0) {
                fat.setRight(insertAVL(x, (NodeAVL) node.getRight()));
                if (this.height) {
                    switch (fat.getBF()) {
                        case -1: {
                            fat.setBF(0);
                            ;
                            this.height = false;
                            break;
                        }
                        case 0: {
                            fat.setBF(1);
                            this.height = true;
                            break;
                        }
                        case 1: {
                            fat = balanceToLeft(fat);
                            this.height = false;
                            break;
                        }
                    }
                }
            } else {
                fat.setLeft(insertAVL(x, (NodeAVL) node.getLeft()));
                if (this.height) {
                    switch (fat.getBF()) {
                        case 1: {
                            fat.setBF(0);
                            this.height = false;
                            break;
                        }
                        case 0: {
                            fat.setBF(-1);
                            this.height = true;
                            break;
                        }
                        case -1: {
                            fat = balanceToRight(fat);
                            this.height = false;
                            break;
                        }
                    }
                }
            }
        }
        return fat;
    }

    protected NodeAVL balanceToLeft(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.getRight();
        switch (hijo.getBF()) {
            case 1: {
                node.setBF(0);
                hijo.setBF(0);
                node = rotateSL(node);
                break;
            }
            case -1: {
                NodeAVL nieto = (NodeAVL) hijo.getLeft();
                switch (nieto.getBF()) {
                    case -1: {
                        node.setBF(0);
                        hijo.setBF(1);
                        break;
                    }
                    case 0: {
                        node.setBF(0);
                        hijo.setBF(0);
                        break;
                    }
                    case 1: {
                        node.setBF(1);
                        hijo.setBF(0);
                        break;
                    }
                }
                nieto.setBF(0);
                node.setRight(rotateSR(hijo));
                node = rotateSL(node);
            }
        }
        return node;
    }

    protected NodeAVL balanceToRight(NodeAVL node) {
        NodeAVL hijo = (NodeAVL) node.getLeft();
        switch (hijo.getBF()) {
            case -1: {
                node.setBF(0);
                hijo.setBF(0);
                node = rotateSR(node);
                break;
            }
            case 1: {
                NodeAVL nieto = (NodeAVL) hijo.getRight();
                switch (nieto.getBF()) {
                    case 1: {
                        node.setBF(1);
                        hijo.setBF(0);
                        break;
                    }
                    case 0: {
                        node.setBF(0);
                        hijo.setBF(0);
                        break;
                    }
                    case -1: {
                        node.setBF(0);
                        hijo.setBF(1);
                        break;
                    }
                }
                nieto.setBF(0);
                node.setLeft(rotateSL(hijo));
                node = rotateSR(node);
            }
        }
        return node;
    }

    private NodeAVL rotateSL(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.getRight();
        node.setRight(p.getLeft());
        p.setLeft(node);
        node = p;
        return node;
    }

    private NodeAVL rotateSR(NodeAVL node) {
        NodeAVL p = (NodeAVL) node.getLeft();
        node.setLeft(p.getRight());
        p.setRight(node);
        node = p;
        return node;
    }

    public int height() {
        return heightSubtree((NodeAVL) this.root);
    }

    private int heightSubtree(NodeAVL node) {
        if (node == null)
            return -1;
        int leftHeight = heightSubtree((NodeAVL) node.getLeft());
        int rightHeight = heightSubtree((NodeAVL) node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    @Override
    public boolean search(E x) {
        return search((NodeAVL) this.root, x);
    }

    private boolean search(NodeAVL actual, E x) {
        if (actual == null) {
            return false; // Si el nodo actual es nulo, el elemento no se encuentra
        }
        int comp = actual.getElem().compareTo(x);
        if (comp > 0) {
            return search((NodeAVL) actual.getLeft(), x); // Buscar en el subárbol izquierdo
        } else if (comp < 0) {
            return search((NodeAVL) actual.getRight(), x); // Buscar en el subárbol derecho
        } else {
            return true; // Se encontró el elemento
        }
    }

    private int getTreeHeight(Node<E> node) {
        if (node == null)
            return 0;
        int leftHeight = getTreeHeight(node.getLeft());
        int rightHeight = getTreeHeight(node.getRight());
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public void drawAVL() throws ExceptionIsEmptyejer2 {
        if (this.root == null) {
            System.out.println("Árbol AVL vacío");
            return;
        }

        int height = getTreeHeight(this.root);
        QueueLink<Node<E>> queue = new QueueLink<>();
        queue.enqueue(this.root);

        for (int level = 1; level <= height; level++) {
            int nodesInLevel = (int) Math.pow(2, level - 1);
            int spacesBefore = (int) Math.pow(2, height - level) - 1;
            int spacesBetween = (int) Math.pow(2, height - level + 1) - 1;

            // Asegurar espacios no negativos
            spacesBefore = Math.max(0, spacesBefore);
            spacesBetween = Math.max(0, spacesBetween);

            StringBuilder nodeLine = new StringBuilder();
            StringBuilder branchLine = new StringBuilder();
            Node<E>[] currentNodes = new Node[nodesInLevel];

            for (int i = 0; i < nodesInLevel; i++) {
                Node<E> node = queue.dequeue();
                currentNodes[i] = node;

                nodeLine.append(" ".repeat(spacesBefore));
                if (node != null) {
                    nodeLine.append(String.format("%2s", node.getElem()));
                    queue.enqueue(node.getLeft());
                    queue.enqueue(node.getRight());
                } else {
                    nodeLine.append("  ");
                    queue.enqueue(null);
                    queue.enqueue(null);
                }
                nodeLine.append(" ".repeat(spacesBetween));
            }
            System.out.println(nodeLine.toString());

            if (level < height) {
                for (int i = 0; i < nodesInLevel; i++) {
                    Node<E> node = currentNodes[i];
                    branchLine.append(" ".repeat(spacesBefore > 0 ? spacesBefore - 1 : 0));
                    if (node != null) {
                        branchLine.append(node.getLeft() != null ? "/" : " ");
                        branchLine.append(" ".repeat(spacesBetween));
                        branchLine.append(node.getRight() != null ? "\\" : " ");
                    } else {
                        branchLine.append("   ").append(" ".repeat(spacesBetween)).append(" ");
                    }
                    branchLine.append(" ".repeat(spacesBefore > 0 ? spacesBefore - 1 : 0));
                }
                System.out.println(branchLine.toString());
            }
        }
    }
}