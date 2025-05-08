package SESION07.Actividad01;

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
    public Node<E> insert(Node<E> actual, E x) throws ExceptionDuplicate{
        Node<E> anterior = actual;
        if(root == null) {
            return new Node<E>(x);
        } else {
            int comp = actual.getElem().compareTo(x);
            if(comp == 1) {
                actual.setLeft(insert(actual.getLeft(), x));
            }
            if(comp == -1) {
                actual.setRight(insert(actual.getRigth(), x));
            } else {
                throw new ExceptionDuplicate("No se aceptan valores duplicados.");
            }
        }
        return anterior;
    }
    public E remove(E x) throws ExceptionDuplicate{
        return (remove(this.root, x)).getElem();
    }
    public Node<E> remove(Node<E> actual, E x) throws ExceptionDuplicate{
        if(actual == null) {
            throw new ExceptionDuplicate("No se encontró el elemento.");
        } else {
            int comp = actual.getElem().compareTo(x);
            if(comp == 1) {
                actual.setLeft(remove(actual.getLeft(), x));
            }
            if(comp == -1) {
                actual.setRight(remove(actual.getRigth(), x));
            }
            if(comp == 0) {
                if(actual.getLeft() == null) {
                    return actual.getRigth();
                } else {
                    if(actual.getRigth() == null) {
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
        if(otherRoot.getRigth() == null) {
            return otherRoot;
        } else {
            return maxOfMinRemove(otherRoot.getRigth());
        }
    }
    public boolean search(E x) {
        if (search(this.root, x) == null){
            return false;
        }
        return true;
    }
    public Node<E> search(Node<E> actual, E x) {
        if(actual == null) {
            return null;
        } else {
            int comp = actual.getElem().compareTo(x);
            if(comp == 1) {
                return search(actual.getLeft(), x);
            }
            if(comp == -1) {
                return search(actual.getRigth(), x);
            } else {
                return actual;
            }
        }
    }
}
