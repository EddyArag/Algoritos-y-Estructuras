package SESION11.LinkedList;

public class LinkedList<E extends Comparable<E>> {
    private Node<E> first;

    public LinkedList() {
        this.first = null;
    }

    public void setFirst(Node<E> first) {
        this.first = first;
    }

    public Node<E> getFirst() {
        return first;
    }

    public boolean isEmptyList() {
        return first == null;
    }

    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmptyList()) {
            first = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }
    }

    public void addLast(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmptyList()) {
            first = newNode;
        } else {
            Node<E> current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void removeNode(E data) throws ExceptionIsEmpty {
        if (isEmptyList()) {
            throw new ExceptionIsEmpty("La lista está vacía.");
        }
        if (first.getElemento().compareTo(data) == 0) {
            first = first.getNext();
        } else {
            Node<E> current = first;
            while (current.getNext() != null) {
                if (current.getNext().getElemento().compareTo(data) == 0) {
                    current.setNext(current.getNext().getNext());
                    break;
                }
                current = current.getNext();
            }
        }
    }

    public int search(E data) throws ExceptionIsEmpty {
        if (isEmptyList()) {
            throw new ExceptionIsEmpty("La lista está vacía.");
        }
        Node<E> current = first;
        int index = 0;
        while (current != null) {
            if (current.getElemento().compareTo(data) == 0) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    public int length() {
        if (isEmptyList()) {
            return 0;
        }
        Node<E> current = first;
        int count = 0;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public E get(int index) throws ExceptionIsEmpty {
        if (isEmptyList()) {
            throw new ExceptionIsEmpty("La lista está vacía.");
        }
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getElemento();
    }

    public void set(int index, E data) throws ExceptionIsEmpty {
        if (isEmptyList()) {
            throw new ExceptionIsEmpty("La lista está vacía.");
        }
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Node<E> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setElemento(data);
    }

    public void destroyList() {
        first = null;
    }

    public String toString() {
        if (isEmptyList()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = first;
        while (current != null) {
            sb.append(current.getElemento());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}