package Sesion9.src.LISTA;

public class LinkedList<T extends Comparable<T>> {
    private Node<T> first;

    public LinkedList() {
        this.first = null;
    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public Node<T> getFirst() {
        return first;
    }

    public boolean isEmptyList() {
        return first == null;
    }

    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmptyList()) {
            first = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }
    }

    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmptyList()) {
            first = newNode;
        } else {
            Node<T> current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }

    public void removeNode(T data) throws ExceptionEmptyLinkedList {
        if (isEmptyList()) {
            throw new ExceptionEmptyLinkedList("La lista está vacía.");
        }
        if (first.getData().compareTo(data) == 0) {
            first = first.getNext();
        } else {
            Node<T> current = first;
            while (current.getNext() != null) {
                if (current.getNext().getData().compareTo(data) == 0) {
                    current.setNext(current.getNext().getNext());
                    break;
                }
                current = current.getNext();
            }
        }
    }

    public int search(T data) throws ExceptionEmptyLinkedList {
        if (isEmptyList()) {
            throw new ExceptionEmptyLinkedList("La lista está vacía.");
        }
        Node<T> current = first;
        int index = 0;
        while (current != null) {
            if (current.getData().compareTo(data) == 0) {
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
        Node<T> current = first;
        int count = 0;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public T get(int index) throws ExceptionEmptyLinkedList {
        if (isEmptyList()) {
            throw new ExceptionEmptyLinkedList("La lista está vacía.");
        }
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public void set(int index, T data) throws ExceptionEmptyLinkedList {
        if (isEmptyList()) {
            throw new ExceptionEmptyLinkedList("La lista está vacía.");
        }
        if (index < 0 || index >= length()) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Node<T> current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        current.setData(data);
    }

    public void destroyList() {
        first = null;
    }

    public String toString() {
        if (isEmptyList()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = first;
        while (current != null) {
            sb.append(current.getData());
            if (current.getNext() != null) {
                sb.append(", ");
            }
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }
}