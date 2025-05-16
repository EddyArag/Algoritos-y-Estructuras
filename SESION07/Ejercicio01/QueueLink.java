package SESION07.Ejercicio01;

public class QueueLink<E extends Comparable<E>> {
    private Nodoejer1<E> front;
    private Nodoejer1<E> rear;

    public QueueLink() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(E item) {
        Nodoejer1<E> newNode = new Nodoejer1<>(item);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setRight(newNode);
            rear = newNode;
        }
    }

    public E dequeue() throws ExceptionIsEmptyejer1 {
        if (isEmpty())
            throw new ExceptionIsEmptyejer1("Cola vacía");
        E temp = front.getElem();
        front = front.getRight();
        if (front == null)
            rear = null;
        return temp;
    }

    public boolean isEmpty() {
        return front == null;
    }
}