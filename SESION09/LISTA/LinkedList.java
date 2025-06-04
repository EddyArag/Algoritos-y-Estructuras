package SESION09.LISTADELISTAS;

public class LinkedList<T extends Comparable<T>> {
    private Node<T> first;

    // Constructor de la clase Lista
    public LinkedList() { this.first = null; }

    // Setters y getters de first
    public void setFirst(Node<T> first) { this.first = first; }
    public Node<T> getFirst() { return first; }

    // Método para verificar si la lista está vacía
    public boolean isEmptyList() {
        return first == null;
    }

    // Método para agregar un elemento al inicio de la lista
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if(isEmptyList()) {
            first = newNode;
        }else {
            newNode.setNext(first);
            first = newNode;
        }
    }
    // Método para agregar un elemento al final de la lista
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if(isEmptyList()) {
            first = newNode;
        }else {
            Node<T> current = first;
            while(current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
    }
    // Método para eliminar un elemento de la lista
    public void removeNode(T data) throws ExceptionEmptyLinkedList {
        if(isEmptyList()) {
            throw new ExceptionEmptyLinkedList("La lista está vacía.");
        }
        if(first.getData().compareTo(data) == 0) {
            first = first.getNext();
        }else {
            Node<T> current = first;
            while(current.getNext() != null) {
                if(current.getNext().getData().compareTo(data) == 0) {
                    current.setNext(current.getNext().getNext());
                    break;
                }
                current = current.getNext();
            }
        }    
    }
    // Método para obtener la posición de un elemento en la lista
    public int search(T data) throws ExceptionEmptyLinkedList {
        if(isEmptyList()) {
            throw new ExceptionEmptyLinkedList("La lista está vacía.");
        }
        Node<T> current = first;
        int index = 0;
        while(current != null) {
            if(current.getData().compareTo(data) == 0) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }
    // Método para obtener el tamaño de la lista
    public int length(){
        if(isEmptyList()) {
            return 0;
        }
        Node<T> current = first;
        int count = 0;
        while(current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }
    // Método para destruir la lista
    public void destroyList() {
        first = null;
    }
}