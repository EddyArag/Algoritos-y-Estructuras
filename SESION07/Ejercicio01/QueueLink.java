package SESION07.Ejercicio01;

public class QueueLink<E> {
    private Nodoejer1<E> front;
    private Nodoejer1<E> rear;

    public QueueLink() {
        this.front = null;
        this.rear = null;
    }

    public void enqueue(Nodoejer1<E> item) {
        Nodoejer1<E> newNode = new Nodoejer1<>(item.getElem());
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.setRight(newNode);
            rear = newNode;
        }
    }

    public Nodoejer1<E> dequeue() throws ExceptionIsEmptyejer1 {
        if (isEmpty()) throw new ExceptionIsEmptyejer1("Cola vacía");
        Nodoejer1<E> temp = front;
        front = front.getRight();
        if (front == null) rear = null;
        return temp;
    }

    public boolean isEmpty() {
        return front == null;
    }
}