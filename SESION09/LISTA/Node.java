package SESION09.LISTA;

public class Node<T extends Comparable<T>> implements Comparable<T>{
    private T data;
    private Node<T> next;
    //Constructor de la clase Node
    public Node(T data) {
        this.data = data;
        this.next = null;
    }

    //Setters y Getters de elemento y next
    public void setData(T data) { this.data = data; }
    public T getData() { return data; }
    public void setNext(Node<T> next){ this.next = next; }
    public Node<T> getNext(){ return next; }
    
    //Método comparteTo para comparar tipos genéricos
    public int compareTo(T otherData){
        return data.compareTo(otherData);
    }
}
