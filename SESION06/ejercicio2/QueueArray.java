package ejercicio2;

import ActividadCola.ExceptionIsEmpty;
import ActividadCola.Queue;

public class QueueArray<E> implements Queue<E> {
    private E[] array;
    private int first; // Índice del primer elemento
    private int last; // Índice del último elemento
    private int size; // Número actual de elementos
    private final int capacity; // Capacidad máxima del arreglo

    @SuppressWarnings("unchecked")
    public QueueArray(int capacity) {
        this.capacity = capacity;
        this.array = (E[]) new Object[capacity];
        this.first = 0;
        this.last = -1;
        this.size = 0;
    }

    @Override
    public void enqueue(E x) {
        if (isFull()) {
            throw new IllegalStateException("La cola está llena");
        }
        last = (last + 1) % capacity; // Aritmética modular para circular
        array[last] = x;
        size++;
    }

    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) {
            throw new ExceptionIsEmpty("La cola está vacía");
        }
        E element = array[first];
        array[first] = null; // Limpiar referencia
        first = (first + 1) % capacity; // Aritmética modular para circular
        size--;
        return element;
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

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "Cola vacía";
        }

        StringBuilder sb = new StringBuilder();
        int current = first;
        for (int i = 0; i < size; i++) {
            sb.append(array[current]);
            if (i < size - 1) {
                sb.append(" <- ");
            }
            current = (current + 1) % capacity;
        }
        return sb.toString();
    }
}