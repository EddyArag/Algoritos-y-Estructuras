package SESION07.Ejercicio02;

public class QueueLink<E> {
    private NodeQueue<E> front, rear;
    private int size;

    public QueueLink() {
        front = rear = null;
        size = 0;
    }

    public void enqueue(E item) {
        NodeQueue<E> newNode = new NodeQueue<>(item);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public E dequeue() throws ExceptionIsEmptyejer2 {
        if (isEmpty())
            throw new ExceptionIsEmptyejer2("Cola vacía");
        E temp = front.elem;
        front = front.next;
        if (front == null)
            rear = null;
        size--;
        return temp;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }
}
