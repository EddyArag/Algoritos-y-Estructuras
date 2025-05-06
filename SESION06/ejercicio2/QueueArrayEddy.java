package ejercicio2;

import ActividadCola.Queue;
import ActividadCola.ExceptionIsEmpty;

public class QueueArrayEddy<E> implements Queue<E> {
    private E[] array;
    private int first; // Índice del primer elemento
    private int last; // Índice del último elemento
    private int size; // Número de elementos actuales
    private int capacity; // Tamaño máximo del arreglo

    @SuppressWarnings("unchecked")
    public QueueArrayEddy(int capacity) {
        this.capacity = capacity;
        this.array = (E[]) new Object[capacity];
        this.first = 0;
        this.last = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(E x) {
        if (size == capacity) {
            throw new IllegalStateException("La cola está llena");
        }
        last = (last + 1) % capacity; // Aritmética modular
        array[last] = x;
        size++;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        E data = array[first];
        first = (first + 1) % capacity; // Aritmética modular
        size--;
        return data;
    }

    @Override
    public E front() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        return array[first];
    }

    @Override
    public E back() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        return array[last];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (isEmpty())
            return "[]";
        StringBuilder sb = new StringBuilder("[");
        int current = first;
        for (int i = 0; i < size; i++) {
            sb.append(array[current]);
            if (i < size - 1)
                sb.append(", ");
            current = (current + 1) % capacity;
        }
        sb.append("]");
        return sb.toString();
    }
}
