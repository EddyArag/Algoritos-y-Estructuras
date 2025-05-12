package SESION07.Ejercicio03;

public class Arbol<E extends Comparable<E>> {
    private Node<E> root;

    public Arbol() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void destroy() {
        root = null;
    }

    public void insert(E x) throws ExceptionDuplicate {
        this.root = insert(this.root, x);
    }

    public Node<E> insert(Node<E> actual, E x) throws ExceptionDuplicate {
        Node<E> anterior = actual;
        if (actual == null) {
            return new Node<E>(x);
        } else {
            int comp = actual.getElem().compareTo(x);
            if (comp > 0) {
                actual.setLeft(insert(actual.getLeft(), x));
            } else if (comp < 0) {
                actual.setRight(insert(actual.getRight(), x));
            } else {
                throw new ExceptionDuplicate("No se aceptan valores duplicados.");
            }
        }
        return anterior;
    }

    public E remove(E x) throws ExceptionDuplicate {
        return (remove(this.root, x)).getElem();
    }

    public Node<E> remove(Node<E> actual, E x) throws ExceptionDuplicate {
        if (actual == null) {
            throw new ExceptionDuplicate("No se encontró el elemento.");
        } else {
            int comp = actual.getElem().compareTo(x);
            if (comp == 1) {
                actual.setLeft(remove(actual.getLeft(), x));
            } else if (comp == -1) {
                actual.setRight(remove(actual.getRight(), x));
            } else if (comp == 0) {
                if (actual.getLeft() == null) {
                    return actual.getRight();
                } else {
                    if (actual.getRight() == null) {
                        return actual.getLeft();
                    } else {
                        Node<E> antecesor = maxOfMinRemove(actual.getLeft());
                        actual.setElem(antecesor.getElem());
                        actual.setLeft(antecesor);
                    }
                }
            }
        }
        return actual;
    }

    public Node<E> maxOfMinRemove(Node<E> otherRoot) {
        if (otherRoot.getRight() == null) {
            return otherRoot;
        } else {
            return maxOfMinRemove(otherRoot.getRight());
        }
    }

    public boolean search(E x) {
        if (search(this.root, x) == null) {
            return false;
        }
        return true;
    }

    public Node<E> search(Node<E> actual, E x) {
        if (actual == null) {
            return null;
        } else {
            int comp = actual.getElem().compareTo(x);
            if (comp == 1) {
                return search(actual.getLeft(), x);
            }
            if (comp == -1) {
                return search(actual.getRight(), x);
            } else {
                return actual;
            }
        }
    }

    public String inOrder() {
        return inOrder(this.root);
    }

    public String inOrder(Node<E> actual) {
        if (actual == null) {
            return "";
        } else {
            return inOrder(actual.getLeft()).toString()
                    + actual.toString()
                    + inOrder(actual.getRight()).toString();
        }
    }

    public String preOrder() {
        return preOrder(this.root);
    }

    public String preOrder(Node<E> actual) {
        if (actual == null) {
            return "";
        } else {
            return actual.toString()
                    + preOrder(actual.getLeft()).toString()
                    + preOrder(actual.getRight()).toString();
        }
    }

    public String postOrder() {
        return postOrder(this.root);
    }

    public String postOrder(Node<E> actual) {
        if (actual == null) {
            return "";
        } else {
            return postOrder(actual.getLeft()).toString()
                    + postOrder(actual.getRight()).toString()
                    + actual.toString();
        }
    }

    public E minSearch() {
        return minSearch(this.root).getElem();
    }

    public Node<E> minSearch(Node<E> actual) {
        if (actual.getLeft() != null) {
            return minSearch(actual.getLeft());
        }
        return actual;
    }

    public E maxSearch() {
        return maxSearch(this.root).getElem();
    }

    public Node<E> maxSearch(Node<E> actual) {
        if (actual.getRight() != null) {
            return maxSearch(actual.getRight());
        }
        return actual;
    }

    public void parenthesize() {
        parenthesizeR(this.root, 0);
    }

    private void parenthesizeR(Node<E> node, int nivel) {
        if (node == null)
            return;

        // Imprime la sangría
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        // Imprime el nodo
        System.out.print(node.getElem());

        // Verifica si el nodo tiene hijos
        if (node.getLeft() != null || node.getRight() != null) {
            System.out.println(" (");
            // Llamadas recursivas para los hijos, respetando la jerarquía
            if (node.getLeft() != null) {
                parenthesizeR(node.getLeft(), nivel + 1);
            }
            if (node.getRight() != null) {
                parenthesizeR(node.getRight(), nivel + 1);
            }
            // Cierra el paréntesis con sangría
            for (int i = 0; i < nivel; i++) {
                System.out.print("    ");
            }
            System.out.println(")");
        } else {
            // Si no tiene hijos, solo imprime un salto de línea
            System.out.println();
        }
    }

}
