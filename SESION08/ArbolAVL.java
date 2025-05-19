package SESION08;

import SESION07.Actividad01.*;
import SESION07.Ejercicio02.*;
import SESION07.Ejercicio01.*;

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

    private boolean height;

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

}